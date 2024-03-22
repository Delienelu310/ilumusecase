package com.ilumusecase.server.repositories.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.StrategiesDatabaseInterface;
import com.ilumusecase.server.repositories.jpa.jpa_repositories.StrategiesJpaRepository;
import com.ilumusecase.server.resources.BotStrategy;


@Repository
public class JpaStrategiesDatabase implements StrategiesDatabaseInterface{

    @Autowired
    private StrategiesJpaRepository strategiesJpaRepository;

    @Override
    public List<BotStrategy> retrieveStrategies(String query, List<String> category, List<String> authors,
        Integer pageNumber, Integer pageSize
    ) {
        query += "%";
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return strategiesJpaRepository.retrieveStrategies(query, category, authors, pageable);

    }

    @Override
    public Long retrieveStrategiesCount(String query, List<String> category, List<String> authors) {
        query += "%";
        return strategiesJpaRepository.retrieveStrategiesCount(query, category, authors);
    }

    @Override
    public BotStrategy retrieveStrategyById(String name) {
        return strategiesJpaRepository.findById(name).get();
    }

    @Override
    public BotStrategy createStrategy(BotStrategy strategy) {
        return strategiesJpaRepository.save(strategy);
    }

    @Override
    public void deleteStrategy(String name) {
        strategiesJpaRepository.deleteById(name);
    }

    @Override
    public BotStrategy updateStrategy(String name, BotStrategy strategy) {
        if( !strategiesJpaRepository.existsById(name) )
            throw new RuntimeException();
        strategy.setStrategy(name);
        return strategiesJpaRepository.save(strategy);
    }

}
