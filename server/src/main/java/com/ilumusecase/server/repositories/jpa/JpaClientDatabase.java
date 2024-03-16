package com.ilumusecase.server.repositories.jpa;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.ClientDatabaseInterface;
import com.ilumusecase.server.resources.Client;


@Repository
public class JpaClientDatabase implements ClientDatabaseInterface{


    @Override
    public Optional<Client> findById(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Client save(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
