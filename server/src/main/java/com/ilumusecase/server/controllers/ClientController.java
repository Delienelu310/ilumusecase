package com.ilumusecase.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Client;


@RestController
public class ClientController {
    
    @Autowired
    private DatabaseInterface database;

    @PostMapping("/register")
    public Client register(@RequestBody Client client){
            
        return database.getClientDatabase().addClient(client);
    }

    @PostMapping("/login")
    public void login(@RequestBody Client client){
        database.getClientDatabase().findById(client.getUsername());
    }
}
