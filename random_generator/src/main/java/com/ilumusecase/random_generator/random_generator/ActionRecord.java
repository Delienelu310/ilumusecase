package com.ilumusecase.random_generator.random_generator;

import com.ilumusecase.game.ActionType;
import com.ilumusecase.game.Card;

import lombok.Data;

@Data
public class ActionRecord {

    private Long roundId;
    private Integer actionNumber;
    private Integer position;
    
    private Card[] tableCards = new Card[5];
    private Integer bigBlind = 100;
    private Integer maxPreviousBetSize;
    private Integer playerPreviousBetSize;
    private Integer bank;

    private ActionType actionType;
    private Integer actionSize;

    private Integer bankroll;
    private Card[] hand = new Card[2];
}
