package com.ilumusecase.server.repositories.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ilumusecase.server.ServerApplication;
import com.ilumusecase.server.repositories.interfaces.TableDatabaseInterface;
import com.ilumusecase.server.repositories.jpa.jpa_repositories.TableJpaRepository;
import com.ilumusecase.server.resources.TableDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class JpaTableDatabase implements TableDatabaseInterface  {

    Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    @Autowired
    private TableJpaRepository tableJpaRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TableDTO> retrieveTables(String query, List<String> authorUsernames, List<String> categories,
        Integer pageNumber, Integer pageSize
    ){

        query += "%";
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return tableJpaRepository.retrieveTables(query, authorUsernames, categories, pageable);
    }

    @Override
    public TableDTO findById(Long id) {
        return tableJpaRepository.findById(id).get();
    }

    @Override
    public Long retrieveTableCount(String query, List<String> authorUsernames, List<String> categories) {
        query += "%";
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

    @Override
    @Transactional
    public void clearCacheById(Long id) {
        entityManager.getEntityManagerFactory().getCache().evict(TableDTO.class, id);
    }

    
    
}
