package com.ilumusecase.server.resources.players;

import java.util.List;
import java.util.ArrayList;

import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;

import com.ilumusecase.server.customGameLogic.ClientPlayer;
import com.ilumusecase.server.resources.Client;
import com.ilumusecase.server.resources.PlayerDTO;

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
 
    @Override
    public Player convertToPlayer() {
        ClientPlayer clientPlayer = new ClientPlayer();
        clientPlayer.setBankroll(this.getBankroll());
        clientPlayer.setCurrentBet(this.getCurrentBet());

        List<Card> hand = new ArrayList<>();
        hand.add(new Card(
            Integer.parseInt(this.getHand().get(0).split("_")[0]),
            Integer.parseInt(this.getHand().get(0).split("_")[1])
        ));
        hand.add(new Card(
            Integer.parseInt(this.getHand().get(1).split("_")[0]),
            Integer.parseInt(this.getHand().get(1).split("_")[1])
        ));
        clientPlayer.setPokerHand(hand);

        clientPlayer.setClient(client);

        return clientPlayer;
    }
    
}
