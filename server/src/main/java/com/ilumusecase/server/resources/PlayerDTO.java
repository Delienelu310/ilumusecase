package com.ilumusecase.server.resources;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public abstract class PlayerDTO {

    @Id
    @GeneratedValue
    private Long id;
    private Integer bankroll;
    private Integer currentBet;

    @ElementCollection
    private List<String> hand = new ArrayList<>();

    
}
