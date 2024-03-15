package com.ilumusecase.game.pokergametypes.holdem6;

import com.ilumusecase.game.pokergametypes.PokerCheckStartMethod;
import com.ilumusecase.game.pokergametypes.PokerGameApplyMoveMethod;
import com.ilumusecase.game.pokergametypes.PokerGameCompareHandsMethod;
import com.ilumusecase.game.pokergametypes.PokerGameGetCurrentPlayerMethod;
import com.ilumusecase.game.pokergametypes.PokerGameIsFinishedMethod;
import com.ilumusecase.game.pokergametypes.PokerGameType;
import com.ilumusecase.game.pokergametypes.PokerStartMethod;

public class Holdem6 implements PokerGameType{

    @Override
    public PokerStartMethod getPokerStartMethod() {
        return new Holdem6StartMethod();
    }

    @Override
    public PokerCheckStartMethod getCheckStartMethod() {
       return new Holdem6CheckStartMethod();
    }

    @Override
    public PokerGameIsFinishedMethod getPokerGameIsFinishedMethod() {
        return new Holdem6GetIsFinishedMethod();
    }

    @Override
    public PokerGameGetCurrentPlayerMethod getPokerGameGetCurrentPlayerMethod() {
        return new Holdem6GetCurrentPlayerMethod();
    }

    @Override
    public PokerGameApplyMoveMethod getPokerGameApplyMoveMethod() {
        return new Holdem6ApplyMoveMethod();
    }

    public PokerGameCompareHandsMethod getCompareHandsMethod(){
        return new Holdem6CompareHandsMethod();
    }
}
