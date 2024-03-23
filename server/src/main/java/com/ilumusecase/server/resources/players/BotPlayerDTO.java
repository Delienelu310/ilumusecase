package com.ilumusecase.server.resources.players;

import com.ilumusecase.server.resources.BotStrategy;
import com.ilumusecase.server.resources.PlayerDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class BotPlayerDTO extends PlayerDTO{

    @ManyToOne
    private BotStrategy botStrategy;

    public BotPlayerDTO() {
    }

    public BotStrategy getBotStrategy() {
        return botStrategy;
    }

    public void setBotStrategy(BotStrategy botStrategy) {
        this.botStrategy = botStrategy;
    }
    
}
