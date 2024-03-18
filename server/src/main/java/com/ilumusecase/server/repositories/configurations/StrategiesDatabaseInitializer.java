package com.ilumusecase.server.repositories.configurations;

import org.springframework.boot.CommandLineRunner;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.BotStrategy;

import lombok.Data;

@Data
public class StrategiesDatabaseInitializer implements CommandLineRunner{
    private DatabaseInterface databaseInterface;

    @Override
    public void run(String... args) throws Exception {
        BotStrategy botStrategy = new BotStrategy();
        botStrategy.setAuthor("admin");
        botStrategy.setCategory("Holdem6");
        botStrategy.setFullClassName("com.ilumusecase.bot.TightAgressiveStrategy");
        botStrategy.setStrategy("Holdem6: tight-aggressive-easy");
        
        databaseInterface.getStrategiesDatabaseInterface().createStrategy(botStrategy);
    }

    
}
