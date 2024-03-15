package com.ilumusecase.server.repositories.jpa;

import com.ilumusecase.server.repositories.ClientDatabaseInterface;
import com.ilumusecase.server.repositories.DatabaseInterface;
import com.ilumusecase.server.repositories.TableDatabaseInterface;

public class JpaDatabase implements DatabaseInterface{

    @Override
    public ClientDatabaseInterface getClientDatabase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientDatabase'");
    }

    @Override
    public TableDatabaseInterface getTableDatabase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTableDatabase'");
    }
    


}
