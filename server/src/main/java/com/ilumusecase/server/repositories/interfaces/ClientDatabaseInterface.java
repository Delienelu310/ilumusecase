package com.ilumusecase.server.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.ilumusecase.server.resources.Client;

public interface ClientDatabaseInterface {

    public List<Client> findAll();
    public Optional<Client> findById(String username);
    public Client save(Client client);
}
