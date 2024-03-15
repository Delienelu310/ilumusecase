package com.ilumusecase.game.pokergametypes.holdem6;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.ilumusecase.game.Card;
import com.ilumusecase.game.Round;

public class HandsEvaluationMainMethodsTest {

    Holdem6CompareHandsMethod compareHandsMethod = new Holdem6CompareHandsMethod();

    @Test
    public void testCompareHands(){
        List<Card> highCard = new ArrayList<>();
        Collections.addAll(highCard, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(2, 3),
            new Card(7, 1),
            new Card(4, 1)    
        );

        List<Card> pair = new ArrayList<>();
        Collections.addAll(pair, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(2, 3),
            new Card(2, 1),
            new Card(4, 1)    
        );

        assertEquals(compareHandsMethod.compareHands(highCard, pair), -1);


        List<Card> twoPairs1 = new ArrayList<>();
        Collections.addAll(twoPairs1, 
            new Card(2, 1), 
            new Card(14, 1), 
            new Card(2, 1),
            new Card(3, 1),
            new Card(14, 3)    
        );

        List<Card> twoPairs2 = new ArrayList<>();
        Collections.addAll(twoPairs2, 
            new Card(5, 1), 
            new Card(12, 1), 
            new Card(5, 1),
            new Card(3, 1),
            new Card(12, 3)    
        );

        assertEquals(compareHandsMethod.compareHands(twoPairs1, twoPairs2), +1);

        List<Card> straight1 = new ArrayList<>();
        Collections.addAll(straight1, 
            new Card(2, 1), 
            new Card(14, 1), 
            new Card(4, 1),
            new Card(3, 1),
            new Card(5, 3)    
        );

        List<Card> straight2 = new ArrayList<>();
        Collections.addAll(straight2, 
            new Card(5, 0), 
            new Card(14, 2), 
            new Card(4, 3),
            new Card(3, 0),
            new Card(2, 3)    
        );

        assertEquals(compareHandsMethod.compareHands(straight1, straight2), 0);
    }

    @Test 
    public void testFindBestHand(){

        Round round = new Round();

        List<Card> tableCards = new ArrayList<>();
        Collections.addAll(tableCards, 
            new Card(6, 1),
            new Card(8, 1),
            new Card(7, 4),
            new Card(12, 1),
            new Card(12, 3)
        );
        round.setTableCards(tableCards);

        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(10, 1),
            new Card(9 ,1)
        );

        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(12, 1),
            new Card(11 ,1)
        );


        assertArrayEquals(
            compareHandsMethod.findBestHand(round, hand1).stream()
                .sorted( (card1, card2) -> {
                    return card1.getRank().equals(card2.getRank()) ? 
                        card1.getSuit() - card2.getSuit()
                        :
                        card1.getRank() - card2.getRank();
                })
                .map(card -> card.getRank() + "_" + card.getSuit())
                .toArray()
        , new String[]{"6_1", "8_1", "9_1", "10_1", "12_1"});

        assertArrayEquals(
            compareHandsMethod.findBestHand(round, hand2).stream()
                .sorted( (card1, card2) -> {
                    return card1.getRank().equals(card2.getRank()) ? 
                        card1.getSuit() - card2.getSuit()
                        :
                        card1.getRank() - card2.getRank();
                })
                .map(card -> card.getRank() + "_" + card.getSuit())
                .toArray()
        , new String[]{"6_1", "8_1", "11_1", "12_1", "12_1"});

        assertArrayEquals(hand1.stream().map(card -> card.getRank() + "_" + card.getSuit()).toArray(), 
            new String[]{"10_1", "9_1"});
        assertArrayEquals(hand2.stream().map(card -> card.getRank() + "_" + card.getSuit()).toArray(), 
            new String[]{"12_1", "11_1"});

        
    }

    @Test
    public void testRun(){
        
        Round round = new Round();

        List<Card> tableCards = new ArrayList<>();
        Collections.addAll(tableCards, 
            new Card(6, 1),
            new Card(8, 1),
            new Card(7, 4),
            new Card(12, 1),
            new Card(12, 3)
        );
        round.setTableCards(tableCards);

        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(10, 1),
            new Card(9 ,1)
        );

        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(12, 1),
            new Card(11 ,1)
        );

        assertEquals(compareHandsMethod.run(round, hand1, hand2), -1);

    }

}
