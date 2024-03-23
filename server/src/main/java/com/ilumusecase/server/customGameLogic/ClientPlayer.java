package com.ilumusecase.server.customGameLogic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;
import com.ilumusecase.server.resources.ActionDTO;
import com.ilumusecase.server.resources.Client;
import com.ilumusecase.server.thread.ClientQueueComponent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientPlayer implements Player{

    private Logger logger = LoggerFactory.getLogger(SpringApplication.class);

    private Integer bankroll;
    private Integer currentBet;
    private List<Card> pokerHand;

    private Client client;
    private Long tableId;

    private ActionDTO currentAction = null;
    private ClientQueueComponent clientQueueComponent = null;


    @Override
    public Action getAction(Round round) {

        clientQueueComponent.getPlayersWaiting().put(tableId, this);


        logger.info("Inside");
        do{

            logger.info("Action is null");
            logger.info("inside of: " + client.getUsername() + " with " + pokerHand.get(0).getRank());
  

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }while(currentAction == null);

        

        Action action =  this.currentAction.convertToAction(round);
        this.currentAction = null;
        return action;

    }    

}
