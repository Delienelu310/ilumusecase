package com.ilumusecase.game.pokergametypes.holdem6;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.ActionType;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.PokerGameIsFinishedMethod;

public class Holdem6GetIsFinishedMethod implements PokerGameIsFinishedMethod{

    @Override
    public Boolean run(Round round) {
        int stagesChanged = 0;
        for(Action action : round.getActions()){
            if(action.getActionType().equals(ActionType.NEXTSTAGE)) stagesChanged++;
        }

        if(stagesChanged == 4) return true;
        else if(stagesChanged >= 0 && stagesChanged < 4) return false;
        else throw new RuntimeException();
    }
    
}
