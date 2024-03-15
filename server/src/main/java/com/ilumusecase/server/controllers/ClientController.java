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
    
    @GetMapping("/clients")
    public List<Client> retreiveClients(){
        return database.getClientDatabase().retrieveAll();
    } 

    @PostMapping("/register")
    public Client register(Client client){
        
        return database.getClientDatabase().save(client);
    }

    @GetMapping
    public void login(Client client){

    }
}
