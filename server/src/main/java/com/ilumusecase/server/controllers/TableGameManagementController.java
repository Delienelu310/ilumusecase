package com.ilumusecase.server.controllers;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Table;
import com.ilumusecase.game.Action;

import lombok.Data;

@RestController
@Data
public class TableGameManagementController {
    
    private DatabaseInterface database;

    @PutMapping("/tables/{talbe_id}/start")
    @SendTo("/tables/{table_id}")
    public Table startRound(@PathVariable("table_id") Long tableId){
        return null;
    }

    @PutMapping("/tables/{table_id}/pause")
    @SendTo("/tables/{table_id}")
    public Table pauseGame(@PathVariable("table_id") Long tableId){
        return null;
    }

    @PutMapping("/tables/{table_id}/continue")
    @SendTo("/tables/{table_id}")
    public Table continueGame(@PathVariable("table_id") Long tableId){
        return null;
    }

    @PutMapping("/tables/{table_id}/next")
    @SendTo("/tables/{table_id}")
    public Table nextMove(@PathVariable("table_id") Long tableId){
        return null;
    }

    @PutMapping("tables/{table_id}/add/action")
    @SendTo("/tables/{table_id}")
    public Table addAction(@PathVariable("table_id") Long tableId, @RequestBody Action action){
        return null;
    }
}
