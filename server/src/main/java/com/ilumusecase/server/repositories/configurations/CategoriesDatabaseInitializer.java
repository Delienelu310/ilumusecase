package com.ilumusecase.server.repositories.configurations;

import org.springframework.boot.CommandLineRunner;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Category;

import lombok.Data;

@Data
public class CategoriesDatabaseInitializer implements CommandLineRunner{

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
