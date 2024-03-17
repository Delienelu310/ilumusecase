package com.ilumusecase.server.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Client;

import lombok.Data;

@RestController
@Data
public class ClientController {
    
    private DatabaseInterface database;

    @PostMapping("/register")
    public Client register(@RequestBody Client client){
        if(!database.getClientDatabase().findById(client.getUsername()).isEmpty()) 
            throw new RuntimeException();
            
        return database.getClientDatabase().addClient(client);
    }

    @PostMapping("/login")
    public void login(@RequestBody Client client){
        if(database.getClientDatabase().findById(client.getUsername()).isEmpty()) 
            throw new RuntimeException();
    }
}
