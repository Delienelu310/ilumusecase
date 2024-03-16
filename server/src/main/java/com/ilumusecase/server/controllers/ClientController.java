package com.ilumusecase.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Client;

import lombok.Data;

@RestController
@Data
public class ClientController {
    
    private DatabaseInterface database;

    @PostMapping("/register")
    public Client register(Client client){
        // if(database.getClientDatabase())
        return database.getClientDatabase().save(client);
    }

    @GetMapping("/login")
    public void login(Client client){

    }
}
