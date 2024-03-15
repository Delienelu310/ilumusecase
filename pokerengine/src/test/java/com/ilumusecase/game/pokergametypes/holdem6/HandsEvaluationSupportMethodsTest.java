package com.ilumusecase.game.pokergametypes.holdem6;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ilumusecase.game.Card;

public class HandsEvaluationSupportMethodsTest {

    Holdem6CompareHandsMethod compareHandsMethod = new Holdem6CompareHandsMethod();
    
    @Test
    public void testPowerEvaluation(){
        List<Card> highCard = new ArrayList<>();
        Collections.addAll(highCard, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(2, 3),
            new Card(7, 1),
            new Card(4, 1)    
        );
        assertEquals(compareHandsMethod.getHandPower(highCard), 0);

        List<Card> pair = new ArrayList<>();
        Collections.addAll(pair, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(2, 3),
            new Card(2, 1),
            new Card(4, 1)    
        );
        assertEquals(compareHandsMethod.getHandPower(pair), 1);

        List<Card> twoPairs = new ArrayList<>();
        Collections.addAll(twoPairs, 
            new Card(2, 1), 
            new Card(14, 1), 
            new Card(2, 1),
            new Card(3, 1),
            new Card(14, 3)    
        );
        assertEquals(compareHandsMethod.getHandPower(twoPairs), 2);


        List<Card> set = new ArrayList<>();
        Collections.addAll(set, 
            new Card(5, 1), 
            new Card(14, 7), 
            new Card(3, 5),
            new Card(3, 1),
            new Card(3, 1)    
        );
        assertEquals(compareHandsMethod.getHandPower(set), 3);

        List<Card> straight = new ArrayList<>();
        Collections.addAll(straight, 
            new Card(5, 1), 
            new Card(14, 7), 
            new Card(3, 5),
            new Card(2, 1),
            new Card(4, 1)    
        );
        assertEquals(compareHandsMethod.getHandPower(straight), 4);

        List<Card> flush = new ArrayList<>();
        Collections.addAll(flush, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(3, 1),
            new Card(3, 1),
            new Card(3, 1)    
        );
        assertEquals(compareHandsMethod.getHandPower(flush), 5);

        List<Card> fullHouse = new ArrayList<>();
        Collections.addAll(fullHouse, 
            new Card(5, 1), 
            new Card(5, 1), 
            new Card(3, 1),
            new Card(3, 1),
            new Card(3, 1)    
        );
        assertEquals(compareHandsMethod.getHandPower(fullHouse), 6);

        List<Card>  quads = new ArrayList<>();
        Collections.addAll(quads, 
            new Card(5, 1), 
            new Card(5, 1), 
            new Card(5, 1),
            new Card(5, 1),
            new Card(3, 1)    
        );
        assertEquals(compareHandsMethod.getHandPower(quads), 7);

        List<Card> straightFlush = new ArrayList<>();
        Collections.addAll(straightFlush, 
            new Card(6, 1), 
            new Card(7, 1), 
            new Card(10, 1),
            new Card(9, 1),
            new Card(8, 1)    
        );
        assertEquals(compareHandsMethod.getHandPower(straightFlush), 8);

    }

    @Test
    public void testRankEvaluation(){
        List<Card> straightFlush = new ArrayList<>();
        Collections.addAll(straightFlush, 
            new Card(6, 1), 
            new Card(7, 1), 
            new Card(10, 1),
            new Card(9, 1),
            new Card(8, 1)    
        );
        assertArrayEquals(compareHandsMethod.getRanks(straightFlush).toArray(), new Integer[]{10,9,8,7,6});

        List<Card> fullHouse = new ArrayList<>();
        Collections.addAll(fullHouse, 
            new Card(5, 1), 
            new Card(5, 1), 
            new Card(3, 1),
            new Card(3, 1),
            new Card(3, 1)    
        );
        assertArrayEquals(compareHandsMethod.getRanks(fullHouse).toArray(), new Integer[]{3,3,3,5,5});

        List<Card> highCard = new ArrayList<>();
        Collections.addAll(highCard, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(2, 3),
            new Card(7, 1),
            new Card(4, 1)    
        );
        assertArrayEquals(compareHandsMethod.getRanks(highCard).toArray(), new Integer[]{14, 7, 5, 4, 2});


        List<Card> twoPairs = new ArrayList<>();
        Collections.addAll(twoPairs, 
            new Card(2, 1), 
            new Card(14, 1), 
            new Card(2, 1),
            new Card(3, 1),
            new Card(14, 3)    
        );
        assertArrayEquals(compareHandsMethod.getRanks(twoPairs).toArray(), new Integer[]{14, 14, 2, 2, 3});


    }

    @Test
    public void testRankComparison(){

        List<Card> highCard1 = new ArrayList<>();
        Collections.addAll(highCard1, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(2, 3),
            new Card(7, 1),
            new Card(4, 1)    
        );
        List<Card> highCard2 = new ArrayList<>();
        Collections.addAll(highCard2, 
            new Card(5, 1), 
            new Card(13, 1), 
            new Card(2, 3),
            new Card(7, 1),
            new Card(4, 1)    
        );

        assertEquals(compareHandsMethod.compareRanks(
            compareHandsMethod.getRanks(highCard1),
            compareHandsMethod.getRanks(highCard2)), 1);


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
            new Card(10, 1), 
            new Card(5, 1),
            new Card(3, 1),
            new Card(10, 3)    
        );

        assertEquals(compareHandsMethod.compareRanks(
            compareHandsMethod.getRanks(twoPairs1),
            compareHandsMethod.getRanks(twoPairs2)), 1);

    }

}
