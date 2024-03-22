package com.ilumusecase.server.resources.players;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;

import com.ilumusecase.server.customGameLogic.ClientPlayer;
import com.ilumusecase.server.resources.Client;
import com.ilumusecase.server.resources.PlayerDTO;
import com.ilumusecase.server.resources.TableDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ClientPlayerDTO extends PlayerDTO{

    @ManyToOne
    private Client client;

    @JsonIgnore
    @ManyToOne
    private TableDTO tableDTO;
 
    @Override
    public Player convertToPlayer() {
        ClientPlayer clientPlayer = new ClientPlayer();
        clientPlayer.setBankroll(this.getBankroll());
        clientPlayer.setCurrentBet(this.getCurrentBet());

        List<Card> hand = new ArrayList<>();

        for(String cardRecord : this.getHand()){
            hand.add(new Card(
                Integer.parseInt(cardRecord.split("_")[0]),
                Integer.parseInt(cardRecord.split("_")[1])
            ));
        }
        clientPlayer.setPokerHand(hand);

        clientPlayer.setClient(client);

        clientPlayer.setTableId(tableDTO.getId());

        return clientPlayer;
    }
    
}
