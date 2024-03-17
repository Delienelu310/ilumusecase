package com.ilumusecase.server.resources;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category {

    @Id
    String category;
    
    String classFullName;
    Integer maxPlayers;
}
