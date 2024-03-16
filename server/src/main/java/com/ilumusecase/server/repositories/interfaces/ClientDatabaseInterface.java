package com.ilumusecase.server.repositories.interfaces;

import java.util.Optional;

import com.ilumusecase.server.resources.Client;

public interface ClientDatabaseInterface {

    public Optional<Client> findById(String username);
    public Client addClient(Client client);
}
