package com.ilumusecase.server.repositories.interfaces;

import com.ilumusecase.server.resources.Client;

public interface ClientDatabaseInterface {

    public Client findById(String username);
    public Client addClient(Client client);
}
