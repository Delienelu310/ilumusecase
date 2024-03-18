package com.ilumusecase.server.repositories.jpa.jpa_repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ilumusecase.server.resources.BotStrategy;

public interface StrategiesJpaRepository extends JpaRepository<BotStrategy, String>{
    
    @Query("SELECT b FROM BotStrategy b WHERE b.strategy LIKE ':query%' AND b.category IN :categories AND b.author IN :authors")
    public List<BotStrategy> retrieveStrategies(
        @Param("query") String query,
        @Param("categories") List<String> categories,
        @Param("authors") List<String> authors,
        Pageable pageable
    );

    @Query("SELECT COUNT(b) FROM BotStrategy b WHERE b.strategy LIKE ':query%' AND b.category IN :categories AND b.author IN :authors")
    public Long retrieveStrategiesCount(
        @Param("query") String query,
        @Param("categories") List<String> categories,
        @Param("authors") List<String> authors
    );
}
