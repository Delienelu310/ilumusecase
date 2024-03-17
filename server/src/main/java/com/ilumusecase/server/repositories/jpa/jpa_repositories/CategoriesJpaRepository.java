package com.ilumusecase.server.repositories.jpa.jpa_repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilumusecase.server.resources.Category;

public interface CategoriesJpaRepository extends JpaRepository<Category, String>{
    
}
