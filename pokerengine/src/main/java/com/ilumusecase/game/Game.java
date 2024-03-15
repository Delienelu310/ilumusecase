package com.ilumusecase.game;

import com.ilumusecase.game.pokergametypes.PokerGameType;

public class Game {

    private PokerGameType pokerGameType;

    public void startRound(Round round){
        pokerGameType.getCheckStartMethod().run(round);
        pokerGameType.getPokerStartMethod().run(round);
    }


    public void addAction(Round round){
        if(pokerGameType.getPokerGameIsFinishedMethod().run(round)) throw new RuntimeException();

        Player currentPlayer = pokerGameType.getPokerGameGetCurrentPlayerMethod().run(round);

        Action action = currentPlayer.getAction(round);

        pokerGameType.getPokerGameApplyMoveMethod().run(round, action);
    }


    public PokerGameType getPokerGameType() {
        return pokerGameType;
    }


    public void setPokerGameType(PokerGameType pokerGameType) {
        this.pokerGameType = pokerGameType;
    }

}
