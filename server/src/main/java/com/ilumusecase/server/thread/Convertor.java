package com.ilumusecase.server.thread;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Deck;
import com.ilumusecase.game.Round;
import com.ilumusecase.server.resources.ActionDTO;
import com.ilumusecase.server.resources.RoundDTO;

@Component
public class Convertor {

    @Autowired
    private PlayerConvertor playerConvertor;

    public Round dtoToRound(RoundDTO roundDTO){
        Round round = new Round();

        round.setBlindSize(roundDTO.getBlindSize());
        round.setBank(roundDTO.getBank());
        round.setPlayers(roundDTO.getPlayers().stream().map(playerDTO -> playerConvertor.dtoToPlayer(playerDTO)).toList());
        round.setPlayersLeft(roundDTO.getPlayersLeft().stream().map(playerDTO -> {
            return round.getPlayers().get(roundDTO.getPlayers().indexOf(playerDTO));
        }).toList());
        round.setTableCards(roundDTO.getTableCards().stream().map(card -> new Card(
            Integer.parseInt(card.split("_")[0]),
            Integer.parseInt(card.split("_")[1])
        )).toList());
        
        Stack<Card> cards = new Stack<>();
        for(String card : roundDTO.getDeck()){
            Integer 
                suit = Integer.parseInt(card.split("_")[0]),
                rank = Integer.parseInt(card.split("_")[1]);

            cards.push(new Card(suit, rank));
        }
        Deck roundDeck = new Deck();
        roundDeck.setStack(cards);
        round.setDeck(roundDeck);

        return round;
    }

     public Action dtoToAction(ActionDTO actionDTO, Round round){
        Action action = new Action();
        action.setActionType(actionDTO.getActionType());
        action.setSize(actionDTO.getSize());
        action.setPlayer(round.getPlayers().get(actionDTO.getPosition()));

        return action;
    }
    
}
