package com.ilumusecase.server.repositories.jpa;

import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.CategoriesDatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.ClientDatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.TableDatabaseInterface;
import com.ilumusecase.server.repositories.interfaces.RoundDatabaseInterface;
import lombok.Data;

@Data
@Repository
public class JpaDatabase implements DatabaseInterface{

    private JpaClientDatabase jpaClientDatabase;
    private JpaTableDatabase jpaTableDatabase;
    private JpaRoundDatabase jpaRoundDatabase;  
    private JpaCategoriesDatabase jpaCategoriesDatabase;  

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
    
}
