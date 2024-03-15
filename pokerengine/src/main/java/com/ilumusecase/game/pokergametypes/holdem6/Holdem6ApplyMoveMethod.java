package com.ilumusecase.game.pokergametypes.holdem6;

import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.PokerGameApplyMoveMethod;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.ActionType;
import com.ilumusecase.game.Player;

public class Holdem6ApplyMoveMethod implements PokerGameApplyMoveMethod{

    private Holdem6GetCurrentPlayerMethod holdem6GetCurrentPlayerMethod = new Holdem6GetCurrentPlayerMethod();

    @Override
    public void run(Round round, Action action) {


        //validation:

        Player currentPlayer = holdem6GetCurrentPlayerMethod.run(round);

        if(currentPlayer != action.getPlayer()) throw new RuntimeException();

        if(action.getActionType().equals(ActionType.NEXTSTAGE)) throw new RuntimeException();


        //performing the action:
        switch(action.getActionType()){
            case FOLD:
                handleFold(round, action);
                break;
            case CHECK:
                handleCheck(round, action);
                break;
            case BET:
                handleBet(round, action);
                break;
            case CALL:
                handleCall(round, action);
                break;
            case RAISE:
                handleRaise(round, action);
                break;
            default:
                throw new RuntimeException();
            
        }

        round.getActions().add(action);

        //here i try to check whether the stage is finished and, if it is, i will go onto to the next stage
        tryToFinishStage(round);

    
    }   
    
    private void tryToFinishStage(Round round){
        //1. check whether all the players did take an action
        //2. check whether the players went all-in or made the stake equal to the max
        //3. do the thing

        //1.
        Set<Player> players = new HashSet<>();
        for(Player player : round.getPlayersLeft()){
            if(player.getBankroll().equals(0)) continue;
            players.add(player);
        }

        for(Integer i = round.getActions().size() - 1; i >= 0; i--){
            Action currentAction = round.getActions().get(i);
            if(currentAction.getActionType().equals(ActionType.NEXTSTAGE)) break;

            if(players.contains(currentAction.getPlayer())) players.remove(currentAction.getPlayer());
        }

        if(players.size() > 0) return;


        //2. 

        Integer maxBet = 0;
        for(Player player : round.getPlayersLeft()){
            if(player.getCurrentBet() > maxBet) maxBet = player.getCurrentBet();
        }

        for(Player player : round.getPlayersLeft()){
            if(player.getBankroll() != 0 && player.getCurrentBet() >= maxBet) return;
        }

        //3. do the thing:

        Action finishStage = new Action();
        finishStage.setActionType(ActionType.NEXTSTAGE);
        round.getActions().add(finishStage);

        //gather the money:
        for(Player player : round.getPlayersLeft()){
            round.setBank(round.getBank() + player.getCurrentBet());
            player.setCurrentBet(0);
        }

        // add cards onto the table:
        
        Integer stageNumber = 0;
        for(Action action : round.getActions()){
            if(action.getActionType().equals(ActionType.NEXTSTAGE)) stageNumber++;
        }
        
        switch (stageNumber) {
            case 1:
                Collections.addAll(round.getTableCards(), round.getDeck().pop(), round.getDeck().pop(), round.getDeck().pop());
                break;
            case 2:
            case 3:
                round.getTableCards().add(round.getDeck().pop());
                break;
            default:
                throw new RuntimeException();
        }


    }

    private void handleRaise(Round round, Action action){

        Player currentPlayer = action.getPlayer();

        Integer currentMax = 0;
        for(Player player : round.getPlayersLeft()){
            if(currentMax < player.getCurrentBet()) currentMax = player.getCurrentBet();   
        }
        if(currentMax <= action.getPlayer().getCurrentBet()) throw new RuntimeException();
        
        

        if(action.getSize() + currentPlayer.getCurrentBet() < currentMax * 2 && currentPlayer.getBankroll() + currentPlayer.getCurrentBet() >= currentMax * 2)
            throw new RuntimeException();

        
        currentPlayer.setBankroll(currentPlayer.getBankroll() - action.getSize());
        currentPlayer.setCurrentBet(action.getSize() + currentPlayer.getCurrentBet());
    }

    private void handleFold(Round round, Action action){

        Player currentPlayer = action.getPlayer();

        round.getPlayersLeft().remove(currentPlayer);
        round.setBank(round.getBank() + currentPlayer.getCurrentBet());
        currentPlayer.setCurrentBet(0);

        return;
    }

    private void handleBet(Round round, Action action){

        Player currentPlayer = action.getPlayer();

        for(Player player : round.getPlayersLeft()){
            if(player.getCurrentBet() != 0) throw new RuntimeException();
        }

        if(action.getSize() > currentPlayer.getBankroll()){
            currentPlayer.setCurrentBet(currentPlayer.getBankroll());
            currentPlayer.setBankroll(0);
        }else{
            currentPlayer.setCurrentBet(action.getSize());
            currentPlayer.setBankroll(currentPlayer.getBankroll() - action.getSize());
        }
    }

    private void handleCall(Round round, Action action){

        Player currentPlayer = action.getPlayer();

        Integer toCall = 0;
        for(Player player : round.getPlayersLeft()){
            if(player.getCurrentBet() > toCall) toCall = player.getCurrentBet();
        }

        if(currentPlayer.getCurrentBet() >= toCall) throw new RuntimeException();

        if(currentPlayer.getBankroll() < toCall - currentPlayer.getCurrentBet()){
            
            action.setSize(currentPlayer.getBankroll());
            currentPlayer.setCurrentBet(currentPlayer.getBankroll() + currentPlayer.getCurrentBet());
            currentPlayer.setBankroll(0);
            
        }else{
            action.setSize(toCall - currentPlayer.getCurrentBet());
            currentPlayer.setBankroll(currentPlayer.getBankroll() - (toCall - currentPlayer.getCurrentBet()) );
            currentPlayer.setCurrentBet(toCall);
        }

        
    }

    private void handleCheck(Round round, Action action){
        for(Player player : round.getPlayersLeft()){
            if(player.getCurrentBet() != 0) throw new RuntimeException();
        }
    }
    

}
