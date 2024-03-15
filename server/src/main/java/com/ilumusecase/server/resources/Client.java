package com.ilumusecase.server.resources;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Client {
    
    @Id
    private String username;
    private String password;
}
