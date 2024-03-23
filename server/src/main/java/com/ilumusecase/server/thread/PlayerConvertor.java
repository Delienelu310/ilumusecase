package com.ilumusecase.server.thread;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ilumusecase.bot.PlayerBot;
import com.ilumusecase.bot.PlayerStrategy;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.server.customGameLogic.ClientPlayer;
import com.ilumusecase.server.resources.PlayerDTO;
import com.ilumusecase.server.resources.players.BotPlayerDTO;
import com.ilumusecase.server.resources.players.ClientPlayerDTO;

@Component
@Lazy
public class PlayerConvertor {

    @Autowired
    private ClientQueueComponent clientQueueComponent;



    public Player dtoToPlayer(PlayerDTO playerDTO){

        if(playerDTO instanceof ClientPlayerDTO){
            return dtoToClientPlayer((ClientPlayerDTO)playerDTO);
        }else if(playerDTO instanceof BotPlayerDTO){
            return dtoToPlayerBot((BotPlayerDTO)playerDTO);
        }else{
            throw new RuntimeException();
        }
    }

    public PlayerBot dtoToPlayerBot(BotPlayerDTO botPlayerDTO){
        PlayerBot playerBot = new PlayerBot();
        playerBot.setBankroll(botPlayerDTO.getBankroll());
        playerBot.setCurrentBet(botPlayerDTO.getCurrentBet());

        List<Card> hand = new ArrayList<>();
        hand.add(new Card(
            Integer.parseInt(botPlayerDTO.getHand().get(0).split("_")[0]), 
            Integer.parseInt(botPlayerDTO.getHand().get(0).split("_")[1])
        ));
        hand.add(new Card(
            Integer.parseInt(botPlayerDTO.getHand().get(1).split("_")[0]), 
            Integer.parseInt(botPlayerDTO.getHand().get(1).split("_")[1])
        ));
        playerBot.setPokerHand(hand);

        try{
            Class<? extends PlayerStrategy> clazz = (Class<? extends PlayerStrategy>)Class.forName(botPlayerDTO.getBotStrategy().getStrategy());
            playerBot.setPlayerStrategy(clazz.getDeclaredConstructor().newInstance()); 
            
        }catch(Exception exception){
            RuntimeException runtimeException = new RuntimeException(exception);
            throw runtimeException;
        }
        

        return playerBot;
    }

    public ClientPlayer dtoToClientPlayer(ClientPlayerDTO playerDTO){
        ClientPlayer clientPlayer = new ClientPlayer();
        clientPlayer.setBankroll(playerDTO.getBankroll());
        clientPlayer.setCurrentBet(playerDTO.getCurrentBet());

        List<Card> hand = new ArrayList<>();

        for(String cardRecord : playerDTO.getHand()){
            hand.add(new Card(
                Integer.parseInt(cardRecord.split("_")[0]),
                Integer.parseInt(cardRecord.split("_")[1])
            ));
        }
        clientPlayer.setPokerHand(hand);

        clientPlayer.setClient(playerDTO.getClient());

        clientPlayer.setTableId(playerDTO.getTableDTO().getId());
        clientPlayer.setClientQueueComponent(clientQueueComponent);

        return clientPlayer;
    }
}
