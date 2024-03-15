package com.ilumusecase.bot;

import com.ilumusecase.game.Round;
import com.ilumusecase.game.Action;
import com.ilumusecase.game.Player;

public interface PlayerStrategy {
    
    public Action getAction(Round round, Player player);
}
