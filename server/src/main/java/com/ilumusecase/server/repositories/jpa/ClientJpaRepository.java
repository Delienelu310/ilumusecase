package com.ilumusecase.server.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilumusecase.server.repositories.interfaces.ClientDatabaseInterface;
import com.ilumusecase.server.resources.Client;

public interface ClientJpaRepository extends JpaRepository<String, Client>, ClientDatabaseInterface{


} 