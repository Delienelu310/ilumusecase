package com.ilumusecase.game.pokergametypes.holdem6;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.ilumusecase.game.Card;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.PokerGameCompareHandsMethod;

public class Holdem6CompareHandsMethod implements PokerGameCompareHandsMethod{

    @Override
    public Integer run(Round round, List<Card> hand1, List<Card> hand2) {

        List<Card> bestHand1 = findBestHand(round, hand1);
        List<Card> bestHand2 = findBestHand(round, hand2);

        return compareHands(bestHand1, bestHand2);

    }


    public List<Card> findBestHand(Round round, List<Card> hand){

        return findBestHandRecursive(round, new ArrayList<>(hand), 0);

    }

    private List<Card> findBestHandRecursive(Round round, List<Card> hand, Integer tableCardsPos){

        if(hand.size() == 5) return hand;
        

        List<Card> bestHand = null;
        
        for(int i = tableCardsPos; i < 5; i++ ){

            hand.add(round.getTableCards().get(i));
            List<Card> currentHand = findBestHandRecursive(round, hand, i + 1);

            if(currentHand != null && (bestHand == null ||  compareHands(bestHand, currentHand) < 0)) 
                bestHand = new ArrayList<>(currentHand);

            hand.remove(hand.size() - 1);
        }

        return bestHand;
    }

    public List<Integer> getRanks(List<Card> hand){

        Map<Integer, Integer> map = mapTheHand(hand);
        

        return hand.stream().map( card -> card.getRank()).sorted((a, b) -> {
            if(map.get(a).equals(map.get(b))) return b - a; 
            else return map.get(b) - map.get(a);
        }).toList();
    }
    
    public Integer compareHands(List<Card> hand1, List<Card> hand2){
        Integer 
            hand1Power = getHandPower(hand1),
            hand2Power = getHandPower(hand2);

        if(hand1Power.equals(hand2Power)) return compareRanks(getRanks(hand1), getRanks(hand2));
        else if(hand1Power > hand2Power) return 1;
        else return -1;
    }

    public Integer getHandPower(List<Card> hand){

        if(isFlushStraight(hand)) return 8;
        if(isQuads(hand)) return 7;
        if(isFullHouse(hand)) return 6;
        if(isFlush(hand)) return 5;
        if(isStraight(hand)) return 4;
        if(isSet(hand)) return 3;
        if(isTwoPairs(hand)) return 2;
        if(isPair(hand))return 1;

        return 0;
    }

    public Integer compareRanks(List<Integer> ranks1, List<Integer> ranks2){
        for(Integer i = 0; i < 5; i++){
            if(ranks1.get(i) < ranks2.get(i)) return -1;
            else if(ranks1.get(i) > ranks2.get(i)) return 1; 
        }

        return 0;
    }

    

    public Boolean isFlushStraight(List<Card> hand){
        return isFlush(hand) && isStraight(hand);
    }

    public Boolean isQuads(List<Card> hand){
        Map<Integer, Integer> mapped = mapTheHand(hand);

        for(Integer count : mapped.values()){
            if(count.equals(4)) return true;
        }

        return false;
    }

    public Boolean isFullHouse(List<Card> hand){
        Map<Integer, Integer> mapped = mapTheHand(hand);

        Integer sets = 0, pairs = 0;

        for(Integer count : mapped.values()){
            if(count.equals(2)) pairs++;
            if(count.equals(3)) sets++;
            if(sets.equals(1) && pairs.equals(1)) return true;
        }
        return false;
    }   

    public Boolean isFlush(List<Card> hand){

        Integer suit = hand.get(0).getSuit();
        for(Card card : hand){
            if( !card.getSuit().equals(suit)) return false;
        }
        return true;
    }

    public Boolean isStraight(List<Card> hand){

        hand.sort( (a,b) -> a.getRank() - b.getRank());

        //check whether there is only one card of each 
        Map<Integer, Integer> map = mapTheHand(hand);
        for(Integer count : map.values()){
            if(count > 1) return false;
        }
        
        //special case: ace
        if(hand.get(4).getRank().equals(14)){
            if(hand.get(0).getRank().equals(2)){
                if(hand.get(3).getRank().equals(5)) return true;
            }else if(hand.get(0).getRank().equals(10)) return true;
            else return false;
        } 
        
        if(hand.get(4).getRank() - hand.get(0).getRank() == 4) return true;
        return false;
    }
 

    public Boolean isSet(List<Card> hand){
        Map<Integer, Integer> mapped = mapTheHand(hand);

        for(Integer count : mapped.values()){
            if(count.equals(3)) return true;
        }

        return false;


    }
    
    
    public Boolean isTwoPairs(List<Card> hand){
        Integer pairs = 0;
        Map<Integer, Integer> mapped = mapTheHand(hand);

        for(Integer count : mapped.values()){
            if(count.equals(2)) pairs++;
            if(pairs.equals(2)) return true;
        }

        return false;
    }
 
    public Boolean isPair(List<Card> hand){

        Map<Integer, Integer> mapped = mapTheHand(hand);

        for(Integer count : mapped.values()){
            if(count == 2) return true;
        }

        return false;
    }

    public Map<Integer, Integer> mapTheHand(List<Card> hand){
        
        Map<Integer, Integer> cards = new HashMap<>();

        for(Card card : hand){
            if( !cards.containsKey(card.getRank()) ) cards.put(card.getRank(), 1);
            else cards.put(card.getRank(), cards.get(card.getRank()) + 1);
        }

        return cards;
    }


}
