package com.ilumusecase.game;

import java.util.ArrayList;
import java.util.List;

public class Round {

    private Deck deck; 
    private List<Player> players;
    private List<Player> playersLeft;
    private Integer blindSize;
    private Integer bank;

    private List<Action> actions = new ArrayList<>();

    private List<Card> tableCards = new ArrayList<>();
    
    
    public Round(){

    }


    public Deck getDeck() {
        return deck;
    }


    public void setDeck(Deck deck) {
        this.deck = deck;
    }


    public List<Player> getPlayers() {
        return players;
    }


    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    public List<Player> getPlayersLeft() {
        return playersLeft;
    }


    public void setPlayersLeft(List<Player> playersLeft) {
        this.playersLeft = playersLeft;
    }


    public Integer getBlindSize() {
        return blindSize;
    }


    public void setBlindSize(Integer blindSize) {
        this.blindSize = blindSize;
    }


    public Integer getBank() {
        return bank;
    }


    public void setBank(Integer bank) {
        this.bank = bank;
    }


    public List<Action> getActions() {
        return actions;
    }


    public void setActions(List<Action> actions) {
        this.actions = actions;
    }


    public List<Card> getTableCards() {
        return tableCards;
    }


    public void setTableCards(List<Card> tableCards) {
        this.tableCards = tableCards;
    }


}
