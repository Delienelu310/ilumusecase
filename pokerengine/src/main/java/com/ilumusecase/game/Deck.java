package com.ilumusecase.game;

import java.util.Stack;
import java.util.Collections;

public class Deck {

    private Stack<Card> stack;

    public Deck(){

    }

    public void refreshDeck(){
        this.stack = new Stack<>();

        for(Integer i = 0; i < 4; i++){
            for(Integer j = 2; j < 15; j++){
                Card card = new Card();
                card.setRank(j);
                card.setSuit(i);

                this.stack.push(card);
            }
        }

        Collections.shuffle(stack);
    }


    public Card pop(){
        return stack.pop();
    }

    public Stack<Card> getStack() {
        return stack;
    }

    public void setStack(Stack<Card> stack) {
        this.stack = stack;
    }
}
