package com.ilumusecase.server.customGameLogic;

import java.util.List;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;
import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Client;
import com.ilumusecase.server.resources.TableDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientPlayer implements Player{

    private DatabaseInterface databaseInterface;

    private Integer bankroll;
    private Integer currentBet;
    private List<Card> pokerHand;

    private Client client;
    private Long tableId;


    @Override
    public Action getAction(Round round) {
        TableDTO table = null;
        while(table != null && table.getNewAction() != null){
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
