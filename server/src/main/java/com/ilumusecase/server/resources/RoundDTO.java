package com.ilumusecase.server.resources;

import java.util.ArrayList;
import java.util.List;
import com.ilumusecase.game.Round;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class RoundDTO {

    @Id
    @GeneratedValue
    private Long id;

    private Integer blindSize;
    private Integer bank;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PlayerDTO> players = new ArrayList<>();
    @OneToMany
    private List<PlayerDTO> playersLeft = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<ActionDTO> actions = new ArrayList<>();

    @ElementCollection
    private List<String> deck = new ArrayList<>();

    @ElementCollection
    private List<String> tableCards = new ArrayList<>();

    public RoundDTO(Round round){

    }    
    
}
