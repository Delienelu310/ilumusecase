package com.ilumusecase.server.resources;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BotStrategy {

    @Id
    private String strategy;

    private String fullClassName;
    private String initializationParameters;
    
    private String category;
    private String author;
}
