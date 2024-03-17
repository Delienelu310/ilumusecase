package com.ilumusecase.server.resources;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
public class Table {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Category category;

    private Integer blindSize;
    
    @OneToMany
    private List<PlayerDTO> players = new ArrayList<>();
    
    @OneToOne
    private RoundDTO currentRound;
    
    @OneToMany
    private List<RoundDTO> playedRounds = new ArrayList<>();

    @ManyToOne
    private Client admin;
    
    @ManyToMany
    private List<Client> viewers = new ArrayList<>();
}
