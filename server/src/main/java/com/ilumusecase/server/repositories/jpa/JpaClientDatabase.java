package com.ilumusecase.server.repositories.jpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.ClientDatabaseInterface;
import com.ilumusecase.server.repositories.jpa.jpa_repositories.ClientJpaRepository;
import com.ilumusecase.server.resources.Client;

@Repository
public class JpaClientDatabase implements ClientDatabaseInterface{

    @Autowired
    private ClientJpaRepository clientJpaRepository;

    @Override
    public Client findById(String username) {
        return clientJpaRepository.findById(username).get();
    }

    @Override
    public Client addClient(Client client) {
        if(!clientJpaRepository.findById(client.getUsername()).isEmpty()) throw new RuntimeException("Username exists");
        return clientJpaRepository.save(client);
    }
    
}
