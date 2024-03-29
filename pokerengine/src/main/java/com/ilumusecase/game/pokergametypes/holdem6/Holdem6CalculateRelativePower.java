package com.ilumusecase.game.pokergametypes.holdem6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.ilumusecase.game.Card;
import com.ilumusecase.game.Deck;
import com.ilumusecase.game.Round;

public class Holdem6CalculateRelativePower {
    
    private Holdem6CompareHandsMethod compareHandsMethod = new Holdem6CompareHandsMethod();

    private Map<String, Double> preflopRelativePowers = new HashMap<>();



    public void calculateAllRelativePreflopPower(){

        preflopRelativePowers = new HashMap<>();

        Deck deck = new Deck();
        deck.refreshDeck();

        List<Card> tableCards = new ArrayList<>();

        calculateRelativePreflopPowerRecursive( deck, tableCards, -1);

        double sum = 0;
        for(Double val : preflopRelativePowers.values()){
            sum += val;
        }

        for(String key : preflopRelativePowers.keySet()){
            preflopRelativePowers.put(key, preflopRelativePowers.get(key) / sum);
        }

    }


    private void calculateRelativePreflopPowerRecursive(Deck deck, List<Card> cards, int tableCardIndex){
        
        System.out.println(cards.stream().map(card -> deck.getStack().indexOf(card)).toList());

        if(cards.size() == 4){
            for(int i1 = 0; i1 < deck.getStack().size() - 3; i1++){
                for(int i2 = i1 + 1; i2 < deck.getStack().size(); i2++ ){
                    for(int j1 = i1 + 1; j1 < deck.getStack().size() - 1; j1++){
                        if(j1 == i2) continue;
                        for(int j2 = j1 + 1; j2 < deck.getStack().size(); j2++){
                            if(j2 == i2) continue;

                            List<Card> hand1 = new ArrayList<>();
                            hand1.add( deck.getStack().get(i1));
                            hand1.add( deck.getStack().get(i2));
                            List<Card> hand2 = new ArrayList<>();
                            hand2.add( deck.getStack().get(j1));
                            hand2.add( deck.getStack().get(j2));

                            Round round = new Round();
                            round.setTableCards(cards);
                            Integer comparisonResult = compareHandsMethod.run(round, hand1, hand2);

                            String
                                hand1String = hand1.stream().map(card1 -> 
                                    card1.getRank() + "_" + card1.getSuit()).reduce((a,b) -> a + b).get(),
                                hand2String = hand2.stream().map(card1 -> 
                                    card1.getRank() + "_" + card1.getSuit()).reduce((a,b) -> a + b).get();

                            if(!preflopRelativePowers.containsKey(hand1String)) preflopRelativePowers.put(hand1String, 0.0);
                            if(!preflopRelativePowers.containsKey(hand2String)) preflopRelativePowers.put(hand2String, 0.0);

                            if(comparisonResult > 0){
                                preflopRelativePowers.put(hand1String, preflopRelativePowers.get(hand1String) + 1.0);
                            }else if(comparisonResult < 0){
                                preflopRelativePowers.put(hand2String, preflopRelativePowers.get(hand2String) + 1.0);
                            }else{
                                preflopRelativePowers.put(hand1String, preflopRelativePowers.get(hand1String) + 0.5);
                                preflopRelativePowers.put(hand2String, preflopRelativePowers.get(hand2String) + 0.5);
                            }

                        }
                    }
                }
            }
            return;
        }

        
        for(int i = tableCardIndex + 1; i < deck.getStack().size(); i++){
            cards.add(deck.getStack().get(i));
            calculateRelativePreflopPowerRecursive(deck, cards, i);
            cards.remove(cards.size() - 1);
        }
        

    }

    public Double calculateRelativePower(Round round, List<Card> hand){
        if(hand.size() != 2) throw new RuntimeException();
        if(round.getTableCards().size() < 3 || round.getTableCards().size() > 5) throw new RuntimeException();


        Deck deck = new Deck();
        deck.refreshDeck();
        deck.getStack().removeIf(card -> round.getTableCards().stream().anyMatch(c -> 
            c.getRank().equals(card.getRank()) && c.getSuit().equals(card.getSuit()) 
        ));
        deck.getStack().removeIf(card -> hand.stream().anyMatch(c -> 
            c.getRank().equals(card.getRank()) && c.getSuit().equals(card.getSuit()) 
        ));

        double all = 0;
        double weaker = 0;

    
        for(int i = 0; i < deck.getStack().size(); i++){

            Card card1 = deck.getStack().get(i);

            for(int j = i + 1; j < deck.getStack().size(); j++){

                Card card2 = deck.getStack().get(j);
                
                List<Card> hand2 = new ArrayList<>();
                hand2.add(card1);
                hand2.add(card2);

                all++;
                Integer isStronger = compareHandsMethod.run(round, hand, hand2);
                if(isStronger > 0) weaker += 1;
                else if(isStronger.equals(0)) weaker += 0.5;

            }
        }

        return weaker / all;
    }


    public Double calculateRelativePotentialPower(Round round, List<Card> hand){
        if(round.getTableCards().size() < 3 || round.getTableCards().size() > 4) throw new RuntimeException();
        if(hand.size() != 2) throw new RuntimeException();

        Deck deck = new Deck();
        deck.refreshDeck();

        deck.getStack().removeIf(card -> round.getTableCards().stream().anyMatch(c -> 
            c.getRank().equals(card.getRank()) && c.getSuit().equals(card.getSuit()) 
        ));
        deck.getStack().removeIf(card -> hand.stream().anyMatch(c -> 
            c.getRank().equals(card.getRank()) && c.getSuit().equals(card.getSuit()) 
        ));


        double relativePowerSum = 0;
        int casesCount = 0;

        for(int x = 0; x < deck.getStack().size(); x++){
            Card tableCard = deck.getStack().get(x);

            round.getTableCards().add(tableCard);

            double weaker = 0;
            double all = 0;

            for(int i = 0; i < deck.getStack().size(); i++){
                if(i == x) continue;
                for(int j = i + 1; j < deck.getStack().size(); j++){
                    if(j == x) continue;

                    List<Card> hand2 = new ArrayList<>();
                    hand2.add(deck.getStack().get(i));
                    hand2.add(deck.getStack().get(j));

                    all++;
                    Integer isStronger = compareHandsMethod.run(round, hand, hand2);
                    if(isStronger > 0) weaker += 1;
                    else if(isStronger.equals(isStronger)) weaker += 0.5;

                }
            }

            double relativePower = weaker / all;

            casesCount++;
            relativePowerSum += relativePower;

            round.getTableCards().remove(tableCard);
        }   

        return relativePowerSum / casesCount;
    }

}
