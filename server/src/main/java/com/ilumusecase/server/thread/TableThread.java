package com.ilumusecase.server.thread;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ilumusecase.game.Player;
import com.ilumusecase.game.Action;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Deck;
import com.ilumusecase.game.Game;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.PokerGameType;
import com.ilumusecase.server.ServerApplication;
import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.ActionDTO;
import com.ilumusecase.server.resources.CardWrapper;
import com.ilumusecase.server.resources.PlayerDTO;
import com.ilumusecase.server.resources.RoundDTO;
import com.ilumusecase.server.resources.TableDTO;
import com.ilumusecase.server.sockets.TableSocketController;




@Component
public class TableThread {

    @Autowired
    private DatabaseInterface database;
    @Autowired
    private TableSocketController tableSocketController;
    @Autowired
    private Convertor convertor;



    private Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    private void recordRound(RoundDTO roundDTO, Round round){
        roundDTO.setBank(round.getBank());
        
        Deck deck = round.getDeck();
        List<CardWrapper> deckDTO = new ArrayList<>();
        for(Card card : deck.getStack()){
            CardWrapper cardDTO = new CardWrapper();
            cardDTO.setValue(card.getRank() + "_" + card.getSuit());
            deckDTO.add(cardDTO);
        }
        roundDTO.setDeck(deckDTO);

        List<CardWrapper> tableCards = new ArrayList<>();
        for(Card card : round.getTableCards()){
            CardWrapper cardDTO = new CardWrapper();
            cardDTO.setValue(card.getRank() + "_" + card.getSuit());
            tableCards.add(cardDTO);
        }
        roundDTO.setTableCards(tableCards);

        logger.info("4");
        for(Integer i = 0; i < round.getPlayers().size(); i++){
            logger.info("4.1");
            PlayerDTO playerDTO = roundDTO.getPlayers().get(i);
            Player player = round.getPlayers().get(i);
            
            recordPlayer(playerDTO, player);

            if(!round.getPlayersLeft().contains(player)){
                roundDTO.setPlayersLeft(roundDTO.getPlayersLeft().stream().filter(pl -> pl.getId() != playerDTO.getId()).toList());
            }
        }

        logger.info("4.2");

        List<ActionDTO> newActions = new ArrayList<>();
        for(Integer i = roundDTO.getActions().size(); i < round.getActions().size(); i++){
            Action action = round.getActions().get(i);
            ActionDTO actionDTO = new ActionDTO();
            actionDTO.setActionType(action.getActionType());
            actionDTO.setSize(action.getSize());
            actionDTO.setPosition(round.getPlayers().indexOf(action.getPlayer()));

            newActions.add(actionDTO);
            
            
        }
        List<ActionDTO> actions = new ArrayList<>();
        actions.addAll(roundDTO.getActions());
        actions.addAll(newActions);

        roundDTO.setActions(actions);
    }

    private void recordPlayer(PlayerDTO playerDTO, Player player){
        playerDTO.setBankroll(player.getBankroll());
        playerDTO.setCurrentBet(player.getCurrentBet());
        playerDTO.setHand(player.getPokerHand().stream().map(card -> card.getRank() + "_" + card.getSuit()).toList());
    }

    
    public void launchGame(TableDTO table){

        // plan
        //1. start the game
        //  a. Create Game object
        //  b. Create object of game category
        //  c. Create round object 
        //  d. start the game
        //  e. Table initial config
        //2. launch the loop
        //  a. if game is paused, skip
        //  b. 
        //3. finish the game
        // 

        //1.a
        Game game = new Game();

        //1.b
        String tableCategory = table.getCategory().getClassFullName();
        PokerGameType pokerGameType = null;
        try{
            pokerGameType = (PokerGameType)Class.forName(tableCategory).getDeclaredConstructor().newInstance();
        
        }catch(Exception exception){
            throw new RuntimeException(exception);
        }
        game.setPokerGameType(pokerGameType);

        //1.c
        if(table.getCurrentSmallBlind() == null){
            table.setCurrentSmallBlind(table.getPlayers().keySet().stream().findAny().get());
        }
        

        RoundDTO roundDTO = new RoundDTO();
        roundDTO.setBank(0);
        roundDTO.setBlindSize(table.getBlindSize());
        roundDTO.setTableCards(new ArrayList<>());
        roundDTO.setActions(new ArrayList<>());
        
        Deck deck = new Deck();
        deck.refreshDeck();

        List<CardWrapper> deckDTO = new ArrayList<>();
        while(!deck.getStack().empty()){
            Card card = deck.pop();
            CardWrapper cardDTO = new CardWrapper();
            cardDTO.setValue(card.getRank() + "_" + card.getSuit());
            deckDTO.add(cardDTO);
        }
        roundDTO.setDeck(deckDTO);


        List<PlayerDTO> playerDTOs = table.getPlayers().entrySet().stream()
            .sorted((pl1, pl2) -> {
                Integer key1 = pl1.getKey();
                Integer key2 = pl2.getKey();
                if(key1 < table.getCurrentSmallBlind()) key1 += table.getCategory().getMaxPlayers();
                if(key2 < table.getCurrentSmallBlind()) key2 += table.getCategory().getMaxPlayers();
                return key1 - key2;
            })
            .map(pl -> pl.getValue())
            .toList();
        playerDTOs.forEach(pl -> {
            pl.setCurrentBet(0);
            pl.setHand(new ArrayList<>());
            pl.setBankroll(table.getBlindSize() * 100);
        });

        roundDTO.setPlayers(playerDTOs);
        roundDTO.setPlayersLeft(playerDTOs);
        table.setCurrentRound(roundDTO);


        Round round = convertor.dtoToRound(table.getCurrentRound());


        //1.d
        pokerGameType.getCheckStartMethod();
        pokerGameType.getPokerStartMethod().run(round);

        recordRound(roundDTO, round);
        database.getRoundDatabase().createRound(roundDTO);

        logger.info("5");

        //1.e
        table.setCurrentPlayerPosition(round.getPlayers().indexOf(pokerGameType.getPokerGameGetCurrentPlayerMethod().run(round)));
        table.setIsPaused(false);
        table.setCurrentRound(roundDTO);

        tableSocketController.sendToClient(table.getId());

        logger.info("6");

        while( !pokerGameType.getPokerGameIsFinishedMethod().run(round) ){

            if(table.getIsPaused()) continue;
            table.setCurrentPlayerPosition(round.getPlayers().indexOf(pokerGameType.getPokerGameGetCurrentPlayerMethod().run(round)));
            

            logger.info(Integer.toString(round.getDeck().getStack().size()));
            
            game.addAction(round);

            recordRound(roundDTO, round);
            logger.info("Thread loop 2");

            logger.info(roundDTO.toString());

            table.setCurrentRound(roundDTO);
            database.getTableDatabase().updateTable(table.getId(), table);
            // database.getRoundDatabase().updateRound(roundDTO.getId(), roundDTO);

            logger.info("Thread loop 3");
            tableSocketController.sendToClient(table.getId());
            logger.info("Thread loop 4");
        }

        pokerGameType.getPokerGameFinalizeMethod().run(round);
        recordRound(roundDTO, round);

        table.getPlayedRounds().add(table.getCurrentRound());
        table.setCurrentRound(null);
        table.setIsPaused(true);
        table.setCurrentSmallBlind( table.getPlayers().keySet().stream()
            .sorted((pl1, pl2) -> {
                Integer key1 = pl1;
                Integer key2 = pl2;
                if(key1 <= table.getCurrentSmallBlind()) key1 += table.getCategory().getMaxPlayers();
                if(key2 <= table.getCurrentSmallBlind()) key2 += table.getCategory().getMaxPlayers();

                return key1 - key2;
            }).findFirst().get()
        );
        database.getTableDatabase().updateTable(table.getId(), table);

        tableSocketController.sendToClient(table.getId());
    }

}
