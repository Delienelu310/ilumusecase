package com.ilumusecase.server.repositories.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Category;


@Component
public class CategoriesDatabaseInitializer implements CommandLineRunner{

    @Autowired
    private DatabaseInterface databaseInterface;

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
        category.setCategory("Holdem6");
        category.setClassFullName("com.ilumusecase.game.pokergametypes.holdem6.Holdem6");
        category.setMaxPlayers(6);
    
        databaseInterface.getCategoriesDatabase().createCategory(category);
    }
    
}
