package com.ilumusecase.server.resources.players;

import com.ilumusecase.game.Player;
import com.ilumusecase.server.resources.PlayerDTO;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ClientPlayerDTO extends PlayerDTO{
 
    @Override
    public Player convertToPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPlayer'");
    }
    
}
