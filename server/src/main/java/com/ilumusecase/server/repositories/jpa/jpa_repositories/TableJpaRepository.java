package com.ilumusecase.server.repositories.jpa.jpa_repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ilumusecase.server.resources.Table;

import java.util.List;

public interface TableJpaRepository extends JpaRepository<Table, Long> {
    
    @Query("SELECT t FROM Table t WHERE ")
    public List<Table> retrieveTables();
}
