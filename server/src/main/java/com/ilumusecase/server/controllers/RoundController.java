package com.ilumusecase.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilumusecase.game.Round;
import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;

import lombok.Data;

@RestController
@Data
public class RoundController {
    
    private DatabaseInterface database;

    @GetMapping("/rounds")
    public List<Round> retrieveRounds(){
        return null;
    }

}
