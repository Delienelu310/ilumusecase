package com.ilumusecase.server.repositories.interfaces;

import java.util.List;

import com.ilumusecase.server.resources.Table;

public interface TableDatabaseInterface {

    public List<Table> retrieveTables(String query, List<String> authorUsernames, List<String> categories, 
        Integer pageNumber, Integer pageSize);
    public Long retrieveTableCount(String query, List<String> authorUsernames, List<String> categories);
    public Table findById(Long id);

    public Table createTable(Table table);

    public void deleteById(Long id);
}
