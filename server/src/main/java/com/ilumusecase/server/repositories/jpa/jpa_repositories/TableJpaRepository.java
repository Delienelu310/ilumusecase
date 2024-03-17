package com.ilumusecase.server.repositories.jpa.jpa_repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import com.ilumusecase.server.resources.Table;

import java.util.List;

public interface TableJpaRepository extends JpaRepository<Table, Long> {
    
    @Query("SELECT t FROM Table t WHERE t.name=:query AND t.admin.username IN :authorUsernames AND t.category.category IN :categories")
    public List<Table> retrieveTables(
        @Param("query") String query, 
        @Param("authorUsernames") List<String> authorUsername, 
        @Param("categories") List<String> categories,
        Pageable pageable
    );

    @Query("SELECT COUNT(t) FROM Table t WHERE t.name=:query AND t.admin.username IN :authorUsernames AND t.category.category IN :categories")
    public Long retrieveTablesNumber(
        @Param("query") String query, 
        @Param("authorUsernames") List<String> authorUsername, 
        @Param("categories") List<String> categories
    );
}
