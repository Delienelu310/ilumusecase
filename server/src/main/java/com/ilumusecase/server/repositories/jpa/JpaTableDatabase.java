package com.ilumusecase.server.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.TableDatabaseInterface;
import com.ilumusecase.server.repositories.jpa.jpa_repositories.TableJpaRepository;
import com.ilumusecase.server.resources.Table;

import lombok.Data;

@Repository
@Data
public class JpaTableDatabase implements TableDatabaseInterface  {

    private TableJpaRepository tableJpaRepository;

    @Override
    public List<Table> retrieveTables(String query, List<String> authorUsernames, List<String> categories,
        Integer pageNumber, Integer pageSize
    ){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return tableJpaRepository.retrieveTables(query, authorUsernames, categories, pageable);
    }

    @Override
    public Optional<Table> findById(Long id) {
        return tableJpaRepository.findById(id);
    }

    @Override
    public Long retrieveTableCount(String query, List<String> authorUsernames, List<String> categories) {
        return tableJpaRepository.retrieveTablesNumber(query, authorUsernames, categories);
    }

    @Override
    public void deleteById(Long id) {
        tableJpaRepository.deleteById(id);
    }

    
    
}
