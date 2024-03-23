package com.ilumusecase.server.resources.players;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ilumusecase.game.Card;
import com.ilumusecase.game.Player;

import com.ilumusecase.server.customGameLogic.ClientPlayer;
import com.ilumusecase.server.resources.Client;
import com.ilumusecase.server.resources.PlayerDTO;
import com.ilumusecase.server.resources.TableDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ClientPlayerDTO extends PlayerDTO{

    @ManyToOne
    private Client client;

    @JsonIgnore
    @ManyToOne
    @JsonFilter("ClietnPlayer_table")
    private TableDTO tableDTO;
 
    public ClientPlayerDTO() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TableDTO getTableDTO() {
        return tableDTO;
    }

    public void setTableDTO(TableDTO tableDTO) {
        this.tableDTO = tableDTO;
    }
    
}
