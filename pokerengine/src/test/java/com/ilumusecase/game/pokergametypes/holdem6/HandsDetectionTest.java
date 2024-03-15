package com.ilumusecase.game.pokergametypes.holdem6;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ilumusecase.game.Card;

public class HandsDetectionTest {

    Holdem6CompareHandsMethod compareHandsMethod = new Holdem6CompareHandsMethod();

    @Test
    public void testStraightFlush(){
        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(2, 1),
            new Card(3, 1),
            new Card(4, 1)    
        );
        assert(compareHandsMethod.isFlushStraight(hand1));

        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(2, 1),
            new Card(3, 2),
            new Card(4, 1)    
        );
        assertFalse(compareHandsMethod.isFlushStraight(hand2));

        List<Card> hand3 = new ArrayList<>();
        Collections.addAll(hand3, 
            new Card(5, 1), 
            new Card(14, 1), 
            new Card(2, 1),
            new Card(6, 1),
            new Card(4, 1)    
        );
        assertFalse(compareHandsMethod.isFlushStraight(hand3));
    }

    @Test
    public void testStraight(){

        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(5, 2), 
            new Card(14, 3), 
            new Card(2, 1),
            new Card(3, 2),
            new Card(4, 1)    
        );
        assert(compareHandsMethod.isStraight(hand1));
        

        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(5, 2), 
            new Card(14, 3), 
            new Card(3, 1),
            new Card(3, 2),
            new Card(4, 1)    
        );
        assertFalse(compareHandsMethod.isStraight(hand2));


        List<Card> hand3 = new ArrayList<>();
        Collections.addAll(hand3, 
            new Card(12, 2), 
            new Card(14, 3), 
            new Card(13, 1),
            new Card(10, 2),
            new Card(11, 1)    
        );
        assert(compareHandsMethod.isStraight(hand3));

        List<Card> hand4 = new ArrayList<>();
        Collections.addAll(hand4, 
            new Card(8, 2), 
            new Card(7, 3), 
            new Card(5, 1),
            new Card(4, 2),
            new Card(3, 1)    
        );
        assertFalse(compareHandsMethod.isStraight(hand4));

    }

    @Test 
    public void testFlush(){
        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(5, 1),
            new Card(7, 2),
            new Card(5, 1)    
        );
        assertFalse(compareHandsMethod.isFlush(hand1));

        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(3, 1), 
            new Card(4, 1), 
            new Card(5, 1),
            new Card(9, 1),
            new Card(13, 1)    
        );
        assert(compareHandsMethod.isFlush(hand2));

        List<Card> hand3 = new ArrayList<>();
        Collections.addAll(hand3, 
            new Card(3, 1), 
            new Card(4, 1), 
            new Card(5, 1),
            new Card(9, 2),
            new Card(13, 1)    
        );
        assertFalse(compareHandsMethod.isFlush(hand3));
    }

    
    @Test
    public void testQuads(){
        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(5, 1),
            new Card(7, 2),
            new Card(5, 1)    
        );
        assert(compareHandsMethod.isQuads(hand1));

        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(7, 1),
            new Card(7, 2),
            new Card(7, 1)    
        );
        assertFalse(compareHandsMethod.isQuads(hand2));
    }

    @Test 
    public void testSets(){
        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(5, 1),
            new Card(7, 2),
            new Card(12, 1)    
        );
        assert(compareHandsMethod.isSet(hand1));

        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(7, 1),
            new Card(7, 2),
            new Card(12, 1)    
        );
        assertFalse(compareHandsMethod.isSet(hand2));

        List<Card> hand3 = new ArrayList<>();
        Collections.addAll(hand3, 
            new Card(8, 2), 
            new Card(5, 3), 
            new Card(8, 1),
            new Card(7, 2),
            new Card(8, 1)    
        );
        assert(compareHandsMethod.isSet(hand3));
    } 

    @Test 
    public void testFullHouse(){
    
        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(7, 1),
            new Card(7, 2),
            new Card(12, 1)    
        );
        
        assertFalse(compareHandsMethod.isFullHouse(hand1));

        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(5, 2), 
            new Card(7, 3), 
            new Card(7, 1),
            new Card(7, 2),
            new Card(5, 1)    
        );
        
        assert(compareHandsMethod.isFullHouse(hand2));
    }

    @Test 
    public void testTwoPairs(){
        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(7, 1),
            new Card(7, 2),
            new Card(12, 1)    
        );
        
        assert(compareHandsMethod.isTwoPairs(hand1));


        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(5, 1),
            new Card(8, 2),
            new Card(12, 1)    
        );

        assertFalse(compareHandsMethod.isTwoPairs(hand2));

        List<Card> hand3 = new ArrayList<>();
        Collections.addAll(hand3, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(5, 1),
            new Card(5, 2),
            new Card(12, 1)    
        );

        assertFalse(compareHandsMethod.isTwoPairs(hand3));


        List<Card> hand4 = new ArrayList<>();
        Collections.addAll(hand4, 
            new Card(5, 2), 
            new Card(2, 3), 
            new Card(3, 1),
            new Card(1, 2),
            new Card(12, 1)    
        );

        assertFalse(compareHandsMethod.isTwoPairs(hand4));

    }

    @Test 
    public void testPair(){

        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(5, 2), 
            new Card(5, 3), 
            new Card(7, 1),
            new Card(8, 2),
            new Card(12, 1)    
        );
        
        assert(compareHandsMethod.isPair(hand1));


        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(1, 2), 
            new Card(5, 3), 
            new Card(7, 1),
            new Card(8, 2),
            new Card(12, 1)    
        );

        assertFalse(compareHandsMethod.isPair(hand2));

    }


}
