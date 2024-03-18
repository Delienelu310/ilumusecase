package com.ilumusecase.server.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ilumusecase.game.Game;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.PokerGameType;
import com.ilumusecase.server.resources.Table;

@Component
public class TableThread {
    
    @Async
    public void launchGame(Table table){
        Game game = new Game();
        String tableCategory = table.getCategory().getClassFullName();
        PokerGameType pokerGameType = null;
        try{
            pokerGameType = (PokerGameType)Class.forName(tableCategory).getDeclaredConstructor().newInstance();
        
        }catch(Exception exception){
            throw new RuntimeException(exception);
        }
        
        Round round = table.getCurrentRound().convertToRound();
        game.setPokerGameType(pokerGameType);
        pokerGameType.getCheckStartMethod();
        pokerGameType.getPokerStartMethod().run(table.getCurrentRound().convertToRound());
        
        while( !game.getPokerGameType().getPokerGameIsFinishedMethod().run(round) ){
            game.addAction(round);
        }
    }

}
