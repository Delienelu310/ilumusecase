package com.ilumusecase.server.resources.players;

import java.util.ArrayList;
import java.util.List;

import com.ilumusecase.bot.PlayerBot;
import com.ilumusecase.bot.PlayerStrategy;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.server.resources.BotStrategy;
import com.ilumusecase.server.resources.PlayerDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BotPlayerDTO extends PlayerDTO{

    @ManyToOne
    private BotStrategy botStrategy;

    @Override
    public Player convertToPlayer(){
        PlayerBot playerBot = new PlayerBot();
        playerBot.setBankroll(this.getBankroll());
        playerBot.setCurrentBet(this.getCurrentBet());

        List<Card> hand = new ArrayList<>();
        hand.add(new Card(
            Integer.parseInt(this.getHand().get(0).split("_")[0]), 
            Integer.parseInt(this.getHand().get(0).split("_")[1])
        ));
        hand.add(new Card(
            Integer.parseInt(this.getHand().get(1).split("_")[0]), 
            Integer.parseInt(this.getHand().get(1).split("_")[1])
        ));
        playerBot.setPokerHand(hand);

        try{
            Class<? extends PlayerStrategy> clazz = (Class<? extends PlayerStrategy>)Class.forName(this.getBotStrategy().getStrategy());
            playerBot.setPlayerStrategy(clazz.getDeclaredConstructor().newInstance()); 
            
        }catch(Exception exception){
            RuntimeException runtimeException = new RuntimeException(exception);
            throw runtimeException;
        }
        

        return playerBot;
    }
    
}
