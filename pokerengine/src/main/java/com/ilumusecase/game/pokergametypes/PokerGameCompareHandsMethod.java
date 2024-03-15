package com.ilumusecase.game.pokergametypes;

import com.ilumusecase.game.Card;
import com.ilumusecase.game.Round;

import java.util.List;

public interface PokerGameCompareHandsMethod {

    public Integer run(Round round, List<Card> hand1, List<Card> hand2);
    
}
