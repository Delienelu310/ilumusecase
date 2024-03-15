package com.ilumusecase.server.controllers;

import org.springframework.web.bind.annotation.PostMapping;

import com.ilumusecase.server.resources.Client;

public class ClientController {
    
    @PostMapping("/register")
    public Client register(Client Client){
        return null;
    }
}
