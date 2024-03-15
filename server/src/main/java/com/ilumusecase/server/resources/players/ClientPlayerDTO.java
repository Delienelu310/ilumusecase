package com.ilumusecase.server.resources.players;

import com.ilumusecase.game.Player;
import com.ilumusecase.server.resources.PlayerDTO;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ClientPlayerDTO implements PlayerDTO{
    
    
    
    
    @Override
    public Player convertToPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPlayer'");
    }
    
}
