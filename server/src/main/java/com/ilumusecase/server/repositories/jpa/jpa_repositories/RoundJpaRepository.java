package com.ilumusecase.server.repositories.jpa.jpa_repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilumusecase.server.resources.RoundDTO;

public interface RoundJpaRepository extends JpaRepository<RoundDTO, Long>{
    
}
