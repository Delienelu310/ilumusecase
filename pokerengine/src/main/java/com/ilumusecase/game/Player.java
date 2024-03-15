package com.ilumusecase.game;

import java.util.List;

public interface Player {

    public Action getAction(Round round);

    public void setPokerHand(List<Card> hand);
    public List<Card> getPokerHand();

    public Integer getBankroll();
    public void setBankroll(Integer bankroll);

    public Integer getCurrentBet();
    public void setCurrentBet(Integer bet);
}
