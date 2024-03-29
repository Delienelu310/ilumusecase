package com.ilumusecase;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.ilumusecase.game.Card;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.holdem6.Holdem6CalculateRelativePower;

public class App 
{
    public static void main( String[] args )
    {

        Holdem6CalculateRelativePower calculateRelativePower = new Holdem6CalculateRelativePower();


        Round round = new Round();
        List<Card> tableCards = new ArrayList<>();
        Collections.addAll(tableCards, 
            new Card(11, 2),
            new Card(10, 2),
            new Card(2, 1)
        );
        round.setTableCards(tableCards);

        List<Card> hand1 = new ArrayList<>();
        Collections.addAll(hand1, 
            new Card(11, 3),
            new Card(2, 0)
        );

        List<Card> hand2 = new ArrayList<>();
        Collections.addAll(hand2, 
            new Card(8, 2),
            new Card(9, 2)
        );


        System.out.println(Math.round(calculateRelativePower.calculateRelativePotentialPower(round, hand1) * 1000));
        System.out.println((calculateRelativePower.calculateRelativePower(round, hand1) * 1000));
        System.out.println(Math.round(calculateRelativePower.calculateRelativePotentialPower(round, hand2) * 1000) );
        System.out.println((calculateRelativePower.calculateRelativePower(round, hand2) * 1000) );

    }
}
