package com.ilumusecase.server.resources;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.ilumusecase.game.Player;
import com.ilumusecase.game.Round;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Data
public class Table {

    @Id
    @GeneratedValue
    private Long id;
    
    private List<Player> players = new ArrayList<>();
    private Round currentRound;
    private List<Round> playedRound = new ArrayList<>();
}
