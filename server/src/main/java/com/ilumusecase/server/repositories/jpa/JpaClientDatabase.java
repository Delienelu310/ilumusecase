package com.ilumusecase.server.repositories.jpa;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.ClientDatabaseInterface;
import com.ilumusecase.server.repositories.jpa.jpa_repositories.ClientJpaRepository;
import com.ilumusecase.server.resources.Client;

import lombok.Data;


@Data
@Repository
public class JpaClientDatabase implements ClientDatabaseInterface{


    private ClientJpaRepository clientJpaRepository;

    @Override
    public Optional<Client> findById(String username) {
        return clientJpaRepository.findById(username);
    }

    @Override
    public Client addClient(Client client) {
        if(!clientJpaRepository.findById(client.getUsername()).isEmpty()) throw new RuntimeException("Username exists");
        return clientJpaRepository.save(client);
    }
    
}
