package com.ilumusecase.server.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilumusecase.server.repositories.interfaces.RoundDatabaseInterface;
import com.ilumusecase.server.resources.RoundDTO;

public interface RoundJpaRepository extends JpaRepository<RoundDTO, Long>, RoundDatabaseInterface{
    
}
