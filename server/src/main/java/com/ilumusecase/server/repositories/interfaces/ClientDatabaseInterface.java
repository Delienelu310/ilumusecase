package com.ilumusecase.server.repositories.interfaces;

import java.util.List;

import com.ilumusecase.server.resources.Client;

public interface ClientDatabaseInterface {

    public List<Client> retrieveAll();
    public Client retrieveById(String username);
    public Client save(Client client);
}
