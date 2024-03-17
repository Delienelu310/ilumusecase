package com.ilumusecase.server.repositories.interfaces;

public interface DatabaseInterface {
    
    public ClientDatabaseInterface getClientDatabase();
    public TableDatabaseInterface getTableDatabase();
    public RoundDatabaseInterface getRoundDatabase();
    public CategoriesDatabaseInterface getCategoriesDatabase();

}
