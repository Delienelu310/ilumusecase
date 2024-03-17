package com.ilumusecase.server.repositories.interfaces;

import java.util.List;


import com.ilumusecase.server.resources.BotStrategy;

public interface StrategiesDatabaseInterface {
    
    public List<BotStrategy> retrieveStrategies(String query, List<String> category, List<String> authors, 
        Integer pageNumber, Integer pageSize);
    public Long retrieveStrategiesCount(String query, List<String> category, List<String> authors);

    public BotStrategy retrieveStrategyById(String name);

    public BotStrategy createStrategy(BotStrategy strategy);
    public void deleteStrategy(String name);
    public BotStrategy updateStrategy(String name, BotStrategy strategy);
}
