package com.ilumusecase.server.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Category;

import lombok.Data;

@RestController
@Data
public class CategoriesController {

    private DatabaseInterface database;
    
    @GetMapping("/categories")
    public List<Category> retrieveAllCategories(){
        return database.getCategoriesDatabase().retrieveAllCategories();
    }

    @GetMapping("/categories/{id}")
    public Category retrieveCategoryById(@PathVariable("id") String name){
        return database.getCategoriesDatabase().retrieveCategoryById(name);
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category){
        return database.getCategoriesDatabase().createCategory(category);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable("id") String name, @RequestBody Category category){
        category.setCategory(name);
        return database.getCategoriesDatabase().updateCategory(name, category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable("id") String name){
        database.getCategoriesDatabase().deleteCategory(name);
    }
}
