package com.ilumusecase.server.repositories.interfaces;

import java.util.List;

import com.ilumusecase.server.resources.TableDTO;

public interface TableDatabaseInterface {

    public List<TableDTO> retrieveTables(String query, List<String> authorUsernames, List<String> categories, 
        Integer pageNumber, Integer pageSize);
    public Long retrieveTableCount(String query, List<String> authorUsernames, List<String> categories);
    public TableDTO findById(Long id);

    public TableDTO createTable(TableDTO table);
    public TableDTO updateTable(Long id, TableDTO table);

    public void deleteById(Long id);

    public void clearCacheById(Long id);
}
