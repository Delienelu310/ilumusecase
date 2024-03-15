package com.ilumusecase.server.resources;

import java.util.List;
import java.util.Stack;

import com.ilumusecase.game.Card;
import com.ilumusecase.game.Deck;
import com.ilumusecase.game.Round;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class RoundDTO {

    @Id
    @GeneratedValue
    private Long id;

    private Integer blindSize;
    private Integer bank;

    @ManyToMany
    private List<PlayerDTO> players;
    @ManyToMany
    private List<PlayerDTO> playersLeft;

    @ElementCollection
    private List<String> deck;

    @ElementCollection
    private List<String> tableCards;

    public RoundDTO(Round round){

    }

    public Round convertToRound(){
        Round round = new Round();

        round.setBlindSize(blindSize);
        round.setBank(bank);
        round.setPlayers(players.stream().map(playerDTO -> playerDTO.convertToPlayer()).toList());
        round.setPlayersLeft(playersLeft.stream().map(playerDTO -> playerDTO.convertToPlayer()).toList());
        round.setTableCards(tableCards.stream().map(card -> new Card(
            Integer.parseInt(card.split("_")[0]),
            Integer.parseInt(card.split("_")[1])
        )).toList());
        
        Stack<Card> cards = new Stack<>();
        for(String card : deck){
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

    
    
}
