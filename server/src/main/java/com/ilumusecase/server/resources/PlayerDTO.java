package com.ilumusecase.server.resources;

import com.ilumusecase.game.Player;

import jakarta.persistence.Entity;


@Entity
public interface PlayerDTO {

    public Player convertToPlayer();
    
}
