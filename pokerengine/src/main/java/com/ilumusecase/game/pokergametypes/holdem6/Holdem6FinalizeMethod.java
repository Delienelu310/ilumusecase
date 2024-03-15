package com.ilumusecase.game.pokergametypes.holdem6;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.PokerGameFinalizeMethod;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;


public class Holdem6FinalizeMethod implements PokerGameFinalizeMethod {

    Holdem6GetIsFinishedMethod isFinishedMethod = new Holdem6GetIsFinishedMethod();
    Holdem6CompareHandsMethod compareHandsMethod = new Holdem6CompareHandsMethod();

    private class PlayerData{
        Player player;
        List<Card> hand;
        List<Integer> ranks;
        Integer power;
        public Player getPlayer() {
            return player;
        }
        public void setPlayer(Player player) {
            this.player = player;
        }
        public List<Card> getHand() {
            return hand;
        }
        public void setHand(List<Card> hand) {
            this.hand = hand;
        }
        public List<Integer> getRanks() {
            return ranks;
        }
        public void setRanks(List<Integer> ranks) {
            this.ranks = ranks;
        }
        public Integer getPower() {
            return power;
        }
        public void setPower(Integer power) {
            this.power = power;
        }
    }

    @Override
    public void run(Round round) {

        if( !isFinishedMethod.run(round) ) throw new RuntimeException();

        //1. sort the players according to the power of their hand
        //2. make the map: player - moneyGivenInBank
        //3. go from stronger hand to the weakest while substracting the money from the map, until the bank is 0


        //organise information in a good way:


        List<PlayerData> playerDatas = round.getPlayersLeft().stream().map(pl -> {
            PlayerData playerData = new PlayerData();
            playerData.setPlayer(pl);
            playerData.setHand(compareHandsMethod.findBestHand(round, pl.getPokerHand()));
            playerData.setPower(compareHandsMethod.getHandPower(playerData.getHand()));
            playerData.setRanks(compareHandsMethod.getRanks(playerData.getHand()));
            return playerData;
        }).toList();

        Map<Player, Integer> moneyGivenToBank = new HashMap<>();

        for(Player player : round.getPlayers()){
            moneyGivenToBank.put(player, 0);
        }

        for(Action action : round.getActions()){
            switch(action.getActionType()){
                case BET:
                case RAISE:
                case CALL:
                    moneyGivenToBank.put(action.getPlayer(), moneyGivenToBank.get(action.getPlayer()) + action.getSize());
                    break;
                default:
                    break;
            }
        }
        

        //group players by the power of their hands
        List<List<PlayerData>> playersDataGroupped = new ArrayList<>();
        out: for(PlayerData playerData : playerDatas){
            for(List<PlayerData> playerDataCompared : playersDataGroupped){
                if(
                    compareHandsMethod.compareRanks(playerData.getRanks(), playerDataCompared.get(0).getRanks()) == 0 && 
                    playerData.getPower().equals(playerDataCompared.get(0).getPower())
                ){
                    playerDataCompared.add(playerData);
                    continue out;
                }
            }
            List<PlayerData> newList = new ArrayList<>();
            newList.add(playerData);
            playersDataGroupped.add(newList);
        }

        //sort the objects in the groups in ascending order based on the money, that player gave to bank
        for(List<PlayerData> playerData : playersDataGroupped){
            playerData.sort((playerData1, playerData2) -> {
                return moneyGivenToBank.get(playerData1.getPlayer()) - moneyGivenToBank.get(playerData2.getPlayer());
            });
        }


        //sort the groups by the power in descending order
        playersDataGroupped.sort((group1, group2) -> {
            PlayerData playerData1 = group1.get(0);
            PlayerData playerData2 = group2.get(1);

            if(playerData1.getPower().equals(playerData2.getPower())){
                return compareHandsMethod.compareRanks(playerData1.getRanks(), playerData2.getRanks());
            }else{
                if(playerData1.getPower() > playerData2.getPower()) return 1;
                else return -1;
            }
        });


        //do the thing:
        for(List<PlayerData> group : playersDataGroupped){
            Integer i = 0;
            for(PlayerData playerData : group){
                Integer moneyGave = moneyGivenToBank.get(playerData.getPlayer());
                if(moneyGave.equals(0)) continue;

                //gather money from each player, that are left
                for(Player player : round.getPlayers()){

                    Integer sum = moneyGivenToBank.get(player) > moneyGave ? 
                        moneyGave  : 
                        moneyGivenToBank.get(player);
                    
                    moneyGivenToBank.put(player, moneyGivenToBank.get(player) - sum);
                    
                    //give the money to all the players in the group
                    for(PlayerData pd : group){
                        Player currentPlayer = pd.getPlayer();
                        currentPlayer.setBankroll(currentPlayer.getBankroll() + sum / (group.size() - i));
                    }
                }
                i++;

            }
        }
        

    
    }

}

