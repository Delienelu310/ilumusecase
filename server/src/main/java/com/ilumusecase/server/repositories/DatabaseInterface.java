package com.ilumusecase.server.repositories;

public interface DatabaseInterface {
    
    public ClientDatabaseInterface getClientDatabase();
    public TableDatabaseInterface getTableDatabase();

}
