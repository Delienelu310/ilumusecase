package com.ilumusecase.server.repositories.interfaces;

import java.util.List;
import java.util.Optional;

import com.ilumusecase.server.resources.Table;

public interface TableDatabaseInterface {

    public List<Table> retrieveTables(String query, List<String> authorUsernames, List<String> categories, 
        Integer pageNumber, Integer pageSize);
    public Long retrieveTableCount(String query, List<String> authorUsernames, List<String> categories);
    public Optional<Table> findById(Long id);
}
