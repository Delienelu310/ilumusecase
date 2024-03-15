package com.ilumusecase.game.pokergametypes.holdem6;

import java.util.List;

import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.PokerCheckStartMethod;

public class Holdem6CheckStartMethod implements PokerCheckStartMethod{

    @Override
    public void run(Round round) {
        List<Player> players = round.getPlayers();
        if(players.size() > 6 || players.size() < 2) throw new RuntimeException("Invalid number of players");

        for(Player player : players){
            if(player == null) throw new RuntimeException();
            if(player.getBankroll() < round.getBlindSize()) throw new RuntimeException();
        }
    }
    
}
