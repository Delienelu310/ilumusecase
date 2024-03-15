package com.ilumusecase.game.pokergametypes;

import com.ilumusecase.game.Round;
import com.ilumusecase.game.Action;

public interface PokerGameApplyMoveMethod {
    
    public void run(Round round, Action action);

}
