package com.ilumusecase.server.resources;

import com.ilumusecase.game.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public abstract class PlayerDTO {

    @Id
    @GeneratedValue
    private Long id;

    public abstract Player convertToPlayer();
    
}
