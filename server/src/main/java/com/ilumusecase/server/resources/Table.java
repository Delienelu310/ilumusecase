package com.ilumusecase.server.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.CascadeType;
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
    
    @OneToMany(cascade = CascadeType.ALL)
    private Map<Integer, PlayerDTO> players = new HashMap<>();
    
    @OneToOne
    private RoundDTO currentRound;
    
    @OneToMany
    private List<RoundDTO> playedRounds = new ArrayList<>();

    @ManyToOne
    private Client admin;
    
    @ManyToMany
    private List<Client> viewers = new ArrayList<>();
}
