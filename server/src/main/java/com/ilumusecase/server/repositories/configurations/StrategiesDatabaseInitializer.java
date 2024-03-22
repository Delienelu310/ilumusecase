package com.ilumusecase.server.repositories.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.BotStrategy;


@Component
public class StrategiesDatabaseInitializer implements CommandLineRunner{

    @Autowired
    private DatabaseInterface databaseInterface;

    @Override
    public void run(String... args) throws Exception {
        BotStrategy botStrategy = new BotStrategy();
        botStrategy.setAuthor("admin");
        botStrategy.setCategory("Holdem6");
        botStrategy.setFullClassName("com.ilumusecase.bot.TightAgressiveStrategy");
        botStrategy.setStrategy("Holdem6-tight-aggressive-easy");
        
        databaseInterface.getStrategiesDatabaseInterface().createStrategy(botStrategy);
    }

    
}
