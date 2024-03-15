package com.ilumusecase.game;


public class Card {

    private Integer rank; 
    private Integer suit;

    public Card(){
    
    }

    public Card(Integer rank, Integer suit){
        this.rank = rank;
        this.suit = suit;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getSuit() {
        return suit;
    }

    public void setSuit(Integer suit) {
        this.suit = suit;
    }
    
}
