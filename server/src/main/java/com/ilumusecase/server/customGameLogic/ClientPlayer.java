package com.ilumusecase.server.customGameLogic;

import java.util.List;

import com.ilumusecase.bot.PlayerStrategy;
import com.ilumusecase.game.Action;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;
import com.ilumusecase.server.resources.Client;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientPlayer implements Player{

    private Integer bankroll;
    private Integer currentBet;
    private List<Card> pokerHand;

    private Client client;

    @Override
    public Action getAction(Round round) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAction'");
    }    

}
