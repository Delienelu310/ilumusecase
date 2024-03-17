package com.ilumusecase.server.repositories.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ilumusecase.server.repositories.interfaces.CategoriesDatabaseInterface;
import com.ilumusecase.server.repositories.jpa.jpa_repositories.CategoriesJpaRepository;
import com.ilumusecase.server.resources.Category;

import lombok.Data;

@Repository
@Data
public class JpaCategoriesDatabase implements CategoriesDatabaseInterface{

    private CategoriesJpaRepository categoriesJpaRepository;

    @Override
    public List<Category> retrieveAllCategories() {
        return categoriesJpaRepository.findAll();
    }

    @Override
    public Category retrieveCategoryById(String name) {
        return categoriesJpaRepository.findById(name).get();
    }

    @Override
    public Category createCategory(Category category) {
        return categoriesJpaRepository.save(category);
    }

    @Override
    public void deleteCategory(String name) {
        categoriesJpaRepository.deleteById(name);
    }

    @Override
    public Category updateCategory(String name, Category category) {
        if( !categoriesJpaRepository.existsById(name) ) throw new RuntimeException();

        category.setCategory(name);
        return categoriesJpaRepository.save(category);
    }
    
}
