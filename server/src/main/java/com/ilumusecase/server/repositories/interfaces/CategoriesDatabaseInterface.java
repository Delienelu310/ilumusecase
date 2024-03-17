package com.ilumusecase.server.repositories.interfaces;

import java.util.List;

import com.ilumusecase.server.resources.Category;

public interface CategoriesDatabaseInterface {
    
    public List<Category> retrieveAllCategories();
    public Category retrieveCategoryById(String name);
    public Category createCategory(Category category);
    public Category updateCategory(String name, Category category);
    public void deleteCategory(String name);
}
