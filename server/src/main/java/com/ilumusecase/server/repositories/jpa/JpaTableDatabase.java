package com.ilumusecase.server.repositories.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.TableDatabaseInterface;
import com.ilumusecase.server.repositories.jpa.jpa_repositories.TableJpaRepository;
import com.ilumusecase.server.resources.TableDTO;


@Repository
public class JpaTableDatabase implements TableDatabaseInterface  {

    @Autowired
    private TableJpaRepository tableJpaRepository;

    @Override
    public List<TableDTO> retrieveTables(String query, List<String> authorUsernames, List<String> categories,
        Integer pageNumber, Integer pageSize
    ){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return tableJpaRepository.retrieveTables(query, authorUsernames, categories, pageable);
    }

    @Override
    public TableDTO findById(Long id) {
        return tableJpaRepository.findById(id).get();
    }

    @Override
    public Long retrieveTableCount(String query, List<String> authorUsernames, List<String> categories) {
        return tableJpaRepository.retrieveTablesNumber(query, authorUsernames, categories);
    }

    @Override
    public void deleteById(Long id) {
        tableJpaRepository.deleteById(id);
    }

    @Override
    public TableDTO createTable(TableDTO table) {
        return tableJpaRepository.save(table);
    }

    @Override
    public TableDTO updateTable(Long id, TableDTO table) {
        if( tableJpaRepository.findById(id).isEmpty()) 
            throw new RuntimeException();
        table.setId(id);
        return tableJpaRepository.save(table);
    }

    
    
}
