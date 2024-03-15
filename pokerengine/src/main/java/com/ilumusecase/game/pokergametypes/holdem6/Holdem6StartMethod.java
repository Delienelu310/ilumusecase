package com.ilumusecase.game.pokergametypes.holdem6;

import java.util.ArrayList;
import java.util.List;

import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.PokerStartMethod;

public class Holdem6StartMethod implements PokerStartMethod{

    @Override
    public void run(Round round) {
        List<Player> players = round.getPlayers();
        
        //Set blinds:

        Player smallBlindPlayer = players.get(0);
        smallBlindPlayer.setBankroll(smallBlindPlayer.getBankroll() - round.getBlindSize() / 2);
        smallBlindPlayer.setCurrentBet(round.getBlindSize() / 2);

        Player bigBlindPlayer = players.get(1);
        bigBlindPlayer.setBankroll(bigBlindPlayer.getBankroll() - round.getBlindSize());
        bigBlindPlayer.setCurrentBet(round.getBlindSize());


        //Set hands: 
        
        for(Player player : players){
            List<Card> hand = new ArrayList<>();
            hand.add(round.getDeck().pop());
            hand.add(round.getDeck().pop());
            player.setPokerHand(hand);
        }
    }
    
}
