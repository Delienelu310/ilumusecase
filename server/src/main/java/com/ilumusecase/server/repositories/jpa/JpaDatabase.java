package com.ilumusecase.server.repositories.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.CategoriesDatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.ClientDatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.TableDatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.RoundDatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.StrategiesDatabaseInterface;


@Repository
public class JpaDatabase implements DatabaseInterface{

    @Autowired
    private JpaClientDatabase jpaClientDatabase;
    @Autowired
    private JpaTableDatabase jpaTableDatabase;
    @Autowired
    private JpaRoundDatabase jpaRoundDatabase;  
    @Autowired
    private JpaCategoriesDatabase jpaCategoriesDatabase;
    @Autowired
    private JpaStrategiesDatabase jpaStrategiesDatabase;  

    @Override
    public ClientDatabaseInterface getClientDatabase() {
        return jpaClientDatabase;    
    }

    @Override
    public TableDatabaseInterface getTableDatabase() {
        return jpaTableDatabase;    
    }

    @Override
    public RoundDatabaseInterface getRoundDatabase(){
        return jpaRoundDatabase;
    }

    @Override
    public CategoriesDatabaseInterface getCategoriesDatabase() {
        return jpaCategoriesDatabase;
    }

    @Override
    public StrategiesDatabaseInterface getStrategiesDatabaseInterface() {
        return jpaStrategiesDatabase;
    }
    
}
