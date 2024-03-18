package com.ilumusecase.game.pokergametypes;


public interface PokerGameType {
    
    public PokerCheckStartMethod getCheckStartMethod();
    public PokerStartMethod getPokerStartMethod();
    public PokerGameIsFinishedMethod getPokerGameIsFinishedMethod();
    public PokerGameGetCurrentPlayerMethod getPokerGameGetCurrentPlayerMethod();
    public PokerGameApplyMoveMethod getPokerGameApplyMoveMethod();
    public PokerGameFinalizeMethod getPokerGameFinalizeMethod();
    public PokerGameCompareHandsMethod getPokerGameCompareHandsMethod();

}
