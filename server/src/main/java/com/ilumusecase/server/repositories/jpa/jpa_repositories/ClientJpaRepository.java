package com.ilumusecase.server.repositories.jpa.jpa_repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilumusecase.server.resources.Client;

public interface ClientJpaRepository extends JpaRepository<Client, String>{
    
} 