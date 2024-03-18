package com.ilumusecase.server.repositories.jpa;

import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.RoundDatabaseInterface;
import com.ilumusecase.server.repositories.jpa.jpa_repositories.RoundJpaRepository;
import com.ilumusecase.server.resources.RoundDTO;

import lombok.Data;

@Repository
@Data
public class JpaRoundDatabase implements RoundDatabaseInterface{

    private RoundJpaRepository roundJpaRepository;

    @Override
    public RoundDTO createRound(RoundDTO roundDTO) {
        roundDTO.setId(null);
        return roundJpaRepository.save(roundDTO);
    }

    @Override
    public RoundDTO updateRound(Long id, RoundDTO roundDTO) {
        if(!roundJpaRepository.existsById(id)) throw new RuntimeException();
        roundDTO.setId(id);
        return roundJpaRepository.save(roundDTO);
    }
    
}
