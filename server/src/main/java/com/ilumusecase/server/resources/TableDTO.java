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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
public class TableDTO {

    //unchangable data
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private Category category;
    private Integer blindSize;
    @ManyToOne
    private Client admin;


    //changable data
    // @OneToMany(cascade = CascadeType.ALL)
    @OneToMany
    private Map<Integer, PlayerDTO> players = new HashMap<>();
    
    @OneToOne
    private RoundDTO currentRound;
    private Integer currentPlayerPosition;
    private Boolean isPaused;
    private Integer currentSmallBlind;
    @OneToOne
    private ActionDTO newAction;

    @OneToMany
    private List<RoundDTO> playedRounds = new ArrayList<>();

    
}
