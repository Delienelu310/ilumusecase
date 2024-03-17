package com.ilumusecase.bot;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.ActionType;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.holdem6.Holdem6CompareHandsMethod;

public class TightAgressiveStrategy implements PlayerStrategy {

    private Holdem6CompareHandsMethod compareHandsMethod = new Holdem6CompareHandsMethod();

    @Override
    public Action getAction(Round round, Player player) {

        Integer stageNumber = getStageNumber(round);
        if(stageNumber.equals(0)) return preflopStrategy(round, player);


        List<Card> bestHand = compareHandsMethod.findBestHand(round, player.getPokerHand());

        Integer handPower = compareHandsMethod.getHandPower(bestHand);
        List<Integer> ranks = compareHandsMethod.getRanks(bestHand);

        return null;
    }

    private Action preflopStrategy(Round round, Player player){
        /*
            If no bet and if (ranks number + pos number > 26) then bet, or if bet then call
            Else if pair/ the same suit + close ranks then call (bb or bet)
            10% - 3bet with pair or samesuit+closeranks
            if ranks number > 25 then call
            if pair and rank > 10 then 4bet and all in  
        */
        Integer maxBet = 0;
        for(Player pl : round.getPlayersLeft()){
            if(pl.getCurrentBet() > maxBet) maxBet = pl.getCurrentBet();
        }

        Integer ranksSum = player.getPokerHand().get(0).getRank() + player.getPokerHand().get(1).getRank();
        Integer posNumber = round.getPlayers().indexOf(player);
        Boolean isPair = player.getPokerHand().get(0).getRank().equals(player.getPokerHand().get(1).getRank());
        Integer pairRank = player.getPokerHand().get(0).getRank();
        Boolean isDrawingHand = player.getPokerHand().get(0).getSuit().equals(player.getPokerHand().get(1).getSuit())
            && Math.abs(player.getPokerHand().get(0).getRank() - player.getPokerHand().get(1).getRank()) < 2;

        Random random = new Random();

        if(maxBet * 1.0 / round.getBlindSize() < 2.5){
            if(ranksSum + posNumber > 26 || isPair && pairRank >= 10){
                Action action =  new Action();
                action.setSize(maxBet * 2 - player.getCurrentBet());
                action.setActionType(ActionType.RAISE);
                action.setPlayer(player);
                return action;
            }else if(isPair || isDrawingHand){
                Action action =  new Action();
                action.setSize(maxBet - player.getCurrentBet());
                action.setActionType(ActionType.CALL);
                action.setPlayer(player);
                return action;
            }else{
                Action action = new Action();
                action.setActionType(ActionType.FOLD);
                action.setPlayer(player);
                return action;
            }
        }else if(maxBet * 1.0 / round.getBlindSize() > 2.5 & maxBet * 1.0 / round.getBlindSize() < 5.5){
            if(ranksSum > 25 || isPair && pairRank >= 10 || random.nextDouble() < 0.1 && isDrawingHand){
                Action action =  new Action();
                action.setSize(maxBet * 2 - player.getCurrentBet());
                action.setActionType(ActionType.RAISE);
                action.setPlayer(player);
                return action;
            }else if(isPair || isDrawingHand){
                Action action =  new Action();
                action.setSize(maxBet - player.getCurrentBet());
                action.setActionType(ActionType.CALL);
                action.setPlayer(player);
                return action;
            }else{
                Action action = new Action();
                action.setActionType(ActionType.FOLD);
                action.setPlayer(player);
                return action;
            }
        }else{
            if(isPair && pairRank >= 12 && player.getCurrentBet() + player.getBankroll() > maxBet){
                Action action = new Action();
                action.setSize(player.getBankroll() - player.getCurrentBet());
                action.setActionType(ActionType.RAISE);
                action.setPlayer(player);
                return action;
            }else if(ranksSum >= 26){
                Action action = new Action();
                action.setSize(maxBet - player.getCurrentBet());
                action.setActionType(ActionType.CALL);
                action.setPlayer(player);
                return action;
            }else{
                Action action = new Action();
                action.setActionType(ActionType.FOLD);
                action.setPlayer(player);
                return action;
            }
        }
    }

    private Integer getStageNumber(Round round){
        Integer result = 0;
        for(Action action : round.getActions()){
            if(action.getActionType().equals(ActionType.NEXTSTAGE)) result++;
        }

        return result;
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
