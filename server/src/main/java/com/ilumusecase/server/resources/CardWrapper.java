package com.ilumusecase.server.resources;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CardWrapper {
    @Id
    @GeneratedValue
    private Integer id;
    private String value;
}
