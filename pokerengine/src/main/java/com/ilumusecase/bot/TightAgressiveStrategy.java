package com.ilumusecase.bot;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.holdem6.Holdem6CompareHandsMethod;

public class TightAgressiveStrategy implements PlayerStrategy {

    private Holdem6CompareHandsMethod compareHandsMethod = new Holdem6CompareHandsMethod();

    @Override
    public Action getAction(Round round, Player player) {
        
        List<Card> bestHand = compareHandsMethod.findBestHand(round, player.getPokerHand());

        return null;


    }

    private Integer getBetFillingMoney(Round round, Player player){
        Integer maxBet = 0;
        for(Player pl : round.getPlayers()){
            if(pl.getCurrentBet() > maxBet) maxBet = pl.getCurrentBet();
        }
        return maxBet - player.getCurrentBet();
    }

    private Integer getTotalBankMoney(Round round){
        Integer sum = 0;

        sum += round.getBank();
        for(Player player : round.getPlayersLeft()){
            sum += player.getCurrentBet();
        }

        return sum;
    }

    private Integer calculateDrawFactor(Round round){
        return 
            checkFlushDraw(round) +
            checkStraightDraw(round)
        ;
    }
    
    private Integer calculatePowerFactor(Round round){
        return 
            checkFlushTable(round) +
            checkStraightTable(round)
        ;
    }

    private Integer getBoardPairsNumber(Round round){
        Map<Integer, Integer> mapped = compareHandsMethod.mapTheHand(round.getTableCards());
        Integer result = 0;
        for(Integer val : mapped.values()){
            if(val >= 2) result++;
        }
        return result;
    }

    private Integer checkFlushTable(Round round){
        Map<Integer, Integer> suitsCounter = new HashMap<>();
        for(Card card : round.getTableCards()){
            if( !suitsCounter.containsKey(card.getSuit()) ) suitsCounter.put(card.getSuit(), 0);
            suitsCounter.put(card.getSuit(), suitsCounter.get(card.getSuit()) + 1);
        }

        for(Integer count : suitsCounter.values()){
            if(count >= 4) return 9;
            else if(count.equals(3)) return 3;
        }
        return 0;
    }

    private Integer checkFlushDraw(Round round){
        Map<Integer, Integer> suitsCounter = new HashMap<>();
        for(Card card : round.getTableCards()){
            if( !suitsCounter.containsKey(card.getSuit()) ) suitsCounter.put(card.getSuit(), 0);
            suitsCounter.put(card.getSuit(), suitsCounter.get(card.getSuit()) + 1);
        }

        Integer result = 0;
        for(Integer count : suitsCounter.values()){
            if(count >= 2) result += 3;
        }
        return result;
    }

    private Integer checkStraightTable(Round round){

        return checkStraightTableRecursive(round, new ArrayList<>());
    }

    private Integer checkStraightTableRecursive(Round round, List<Card> cards){
        
        if(cards.size() == 3) return countStraightsOptions(cards);

        Integer result = 0;

        Integer i = cards.size();
        for(; i <= round.getTableCards().size(); i++){
            cards.add(round.getTableCards().get(0));
        
            result += checkStraightTableRecursive(round, cards);

            cards.remove(cards.size() - 1);
        }

        return result;
    }

    private Integer countStraightsOptions(List<Card> cards){
        
        Integer result = 0;

        for(Integer i = 2; i < 14; i++){
            for(Integer j = i + 1;  j - i < 5; j++){
                cards.add(new Card(i, 5));
                cards.add(new Card(j, 5));

                if(compareHandsMethod.isStraight(cards)) result++;

                cards.remove(cards.size() - 1);
                cards.remove(cards.size() - 1);
            }

            if(i < 5){
                cards.add(new Card(i, 5));
                cards.add(new Card(14, 5));

                if(compareHandsMethod.isStraight(cards)) result++;

                cards.remove(cards.size() - 1);
                cards.remove(cards.size() - 1);
            }
        }

        return result;

    }

    private Integer checkStraightDraw(Round round){
        Integer result = 0;
        for(Integer i = 0; i < round.getTableCards().size(); i++){
            for(Integer j = 0; j < round.getTableCards().size(); j++){
                Integer 
                    a = round.getTableCards().get(i).getRank(),
                    b = round.getTableCards().get(j).getRank();
                
                if(Math.max(a, b) == 14 && Math.min(a,b) <= 5) result += 2;
                if(Math.abs(a - b )< 5) result += 2; 
            }
        }

        return result;
    }

    
    
    
    
}
