package com.ilumusecase.server.customGameLogic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ilumusecase.game.Action;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;
import com.ilumusecase.server.ServerApplication;
import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Client;
import com.ilumusecase.server.resources.TableDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientPlayer implements Player{

    @JsonIgnore
    Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    @Autowired
    private DatabaseInterface databaseInterface;

    private Integer bankroll;
    private Integer currentBet;
    private List<Card> pokerHand;

    private Client client;
    private Long tableId;


    @Override
    public Action getAction(Round round) {
        TableDTO table = databaseInterface.getTableDatabase().findById(tableId);

        while(table != null && table.getNewAction() != null){
            logger.info("Client loop");
            table = databaseInterface.getTableDatabase().findById(tableId);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return table.getNewAction().convertToAction(round);
    }    

}
