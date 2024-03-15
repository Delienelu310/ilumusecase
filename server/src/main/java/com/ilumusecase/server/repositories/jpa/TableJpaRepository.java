package com.ilumusecase.server.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilumusecase.server.repositories.interfaces.TableDatabaseInterface;
import com.ilumusecase.server.resources.Table;

public interface TableJpaRepository extends JpaRepository<Long, Table>, TableDatabaseInterface {
    
}
