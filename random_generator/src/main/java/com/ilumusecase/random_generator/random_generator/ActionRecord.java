package com.ilumusecase.random_generator.random_generator;

import com.ilumusecase.game.ActionType;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import java.util.List;
import java.util.ArrayList;

import lombok.Data;

@Data
public class ActionRecord {

    @CsvBindByName
    private Long roundId;
    @CsvBindByName
    private Integer actionNumber;
    @CsvBindByName
    private Integer position;
    @CsvBindByName
    private String category;
    
    
    @CsvBindAndSplitByName(elementType = List.class, writeDelimiter = ",")
    private List<String> tableCards = new ArrayList<>();
    @CsvBindByName
    private Integer bigBlind = 100;
    @CsvBindByName
    private Integer maxPreviousBetSize;
    @CsvBindByName
    private Integer playerPreviousBetSize;
    @CsvBindByName
    private Integer bank;

    @CsvBindByName
    private ActionType actionType;
    @CsvBindByName
    private Integer actionSize;
    @CsvBindByName
    private Integer bankroll;
    @CsvBindAndSplitByName(elementType = List.class, writeDelimiter = ",")
    private List<String> hand = new ArrayList<>();
}
