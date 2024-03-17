package com.ilumusecase.bot;

import java.util.ArrayList;
import java.util.List;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;


public class PlayerBot implements Player{

    private Integer bankroll = 0;
    private List<Card> hand = new ArrayList<>();
    private Integer currentBet = 0;

    private PlayerStrategy playerStrategy;

    @Override
    public Action getAction(Round round) {
        return null;
    }

    @Override
    public void setPokerHand(List<Card> hand) {
        this.hand = hand;
    }

    @Override
    public List<Card> getPokerHand() {
        return this.hand;
    }

    @Override
    public Integer getBankroll() {
        return this.bankroll;
    }

    @Override
    public void setBankroll(Integer bankroll) {
        this.bankroll = bankroll;
    }

    @Override
    public Integer getCurrentBet() {
        return this.currentBet;
    }

    @Override
    public void setCurrentBet(Integer bet) {
        this.currentBet = bet;
    }


    public PlayerStrategy getPlayerStrategy() {
        return playerStrategy;
    }

    public void setPlayerStrategy(PlayerStrategy playerStrategy) {
        this.playerStrategy = playerStrategy;
    }
    
}
