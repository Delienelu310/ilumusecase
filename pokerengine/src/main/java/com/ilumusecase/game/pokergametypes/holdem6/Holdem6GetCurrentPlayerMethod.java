package com.ilumusecase.game.pokergametypes.holdem6;


import com.ilumusecase.game.Action;
import com.ilumusecase.game.ActionType;
import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.PokerGameGetCurrentPlayerMethod;

public class Holdem6GetCurrentPlayerMethod implements PokerGameGetCurrentPlayerMethod{

    @Override
    public Player run(Round round) {


        //if no actions, then 3rd players moves:
        if(round.getActions().size() == 0) return round.getPlayers().get(2 % round.getPlayers().size());

        //A: if the stage was finished, we start from the first players
        //B: otherwise we go to next players after the last moved

        Action lastAction = round.getActions().get(round.getActions().size() - 1);
        //A:
        if(lastAction.getActionType().equals(ActionType.NEXTSTAGE)){
            return round.getPlayersLeft().get(0);
        }

        //B:
        return round.getPlayersLeft().get(
            (round.getPlayersLeft().indexOf(lastAction.getPlayer()) + 1) % round.getPlayers().size() 
        );

    }
    
}
