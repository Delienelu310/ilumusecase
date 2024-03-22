package com.ilumusecase.server.controllers;

import java.util.List;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.BotStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StrategiesController {

    @Autowired
    private DatabaseInterface database;
    
    @GetMapping("/strategies")
    public List<BotStrategy> retrieveStrategies(
        @RequestParam(name="query", required=false, defaultValue = "%") String query,
        @RequestParam(name="authors", required=false, defaultValue = "%") List<String> authors,
        @RequestParam(name="categories", required=false, defaultValue = "%") List<String> categories,
        @RequestParam(name="pageSize", required = false, defaultValue = "10") Integer pageSize, 
        @RequestParam(name="pageNumber", required= false, defaultValue = "0" ) Integer pageNumber
    ){
        return database.getStrategiesDatabaseInterface().retrieveStrategies(query, categories, authors, pageNumber, pageSize);
    }

    @GetMapping("/strategies/count")
    public Long retrieveStrategiesCount(
        @RequestParam(name="query", required=false, defaultValue = "%") String query,
        @RequestParam(name="authors", required=false, defaultValue = "%") List<String> authors,
        @RequestParam(name="categories", required=false, defaultValue = "%") List<String> categories
    ){
        return database.getStrategiesDatabaseInterface().retrieveStrategiesCount(query, categories, authors);
    }

    @GetMapping("/strategies/{name}")
    public BotStrategy retrieveStrategyById(@PathVariable("name") String name){
        return database.getStrategiesDatabaseInterface().retrieveStrategyById(name);
    }

    @DeleteMapping("/strategies/{bame}")
    public void deleteStrategyById(@PathVariable("name") String name){
        database.getStrategiesDatabaseInterface().deleteStrategy(name);
    }

    @PostMapping("/strategies")
    public BotStrategy createStrategy(@RequestBody BotStrategy botStrategy){
        return database.getStrategiesDatabaseInterface().createStrategy(botStrategy);
    }

    @PutMapping("/strategies/{name}")
    public BotStrategy updateStrategy(@RequestBody BotStrategy botStrategy, @PathVariable("name") String name){
        return database.getStrategiesDatabaseInterface().updateStrategy(name, botStrategy);
    }
}
