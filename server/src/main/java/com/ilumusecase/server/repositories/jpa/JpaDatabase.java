package com.ilumusecase.server.repositories.jpa;

import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.ClientDatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.TableDatabaseInterface;

import lombok.Data;

@Data
@Repository
public class JpaDatabase implements DatabaseInterface{

    private ClientJpaRepository clientJpaRepository;
    private TableJpaRepository tableJpaRepository;
    

    @Override
    public ClientDatabaseInterface getClientDatabase() {
        return clientJpaRepository;    
    }

    @Override
    public TableDatabaseInterface getTableDatabase() {
        return tableJpaRepository;    
    }
    


}
