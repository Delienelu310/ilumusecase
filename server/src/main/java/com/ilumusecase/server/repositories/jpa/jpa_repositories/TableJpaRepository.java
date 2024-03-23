package com.ilumusecase.server.repositories.jpa.jpa_repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import com.ilumusecase.server.resources.TableDTO;

import java.util.List;

public interface TableJpaRepository extends JpaRepository<TableDTO, Long> {


    final String selectQuery = """
        SELECT t FROM TableDTO t WHERE t.name LIKE :query  
            AND  ('%' IN :authorUsernames  OR t.admin.username IN (:authorUsernames))
            AND  ('%' IN :categories  OR t.category.category IN (:categories))
    """;

    final String countQuery = """
        SELECT COUNT(t) FROM TableDTO t WHERE t.name LIKE :query 
            AND  ('%' IN :authorUsernames  OR t.admin.username IN (:authorUsernames))
            AND  ('%' IN :categories  OR t.category.category IN (:categories))
    """;

    @Query(selectQuery)
    public List<TableDTO> retrieveTables(
        @Param("query") String query, 
        @Param("authorUsernames") List<String> authorUsername, 
        @Param("categories") List<String> categories,
        Pageable pageable
    );

    @Query(countQuery)
    public Long retrieveTablesNumber(
        @Param("query") String query, 
        @Param("authorUsernames") List<String> authorUsername, 
        @Param("categories") List<String> categories
    );
}
