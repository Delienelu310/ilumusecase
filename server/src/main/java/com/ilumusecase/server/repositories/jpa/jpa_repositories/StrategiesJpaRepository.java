package com.ilumusecase.server.repositories.jpa.jpa_repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ilumusecase.server.resources.BotStrategy;

public interface StrategiesJpaRepository extends JpaRepository<BotStrategy, String>{

    final String selectQuery = """
        SELECT b FROM BotStrategy b WHERE b.strategy LIKE :query 
            AND ('%' IN :categories OR b.category IN :categories)
            AND ('%' IN :authors OR b.author IN :authors)
    """;

    final String countQuery = """
        SELECT COUNT(b) FROM BotStrategy b WHERE b.strategy LIKE :query
            AND ('%' IN :categories OR b.category IN :categories)
            AND ('%' IN :authors OR b.author IN :authors)   
    """;
    
    @Query(selectQuery)
    public List<BotStrategy> retrieveStrategies(
        @Param("query") String query,
        @Param("categories") List<String> categories,
        @Param("authors") List<String> authors,
        Pageable pageable
    );

    @Query(countQuery)
    public Long retrieveStrategiesCount(
        @Param("query") String query,
        @Param("categories") List<String> categories,
        @Param("authors") List<String> authors
    );
}
