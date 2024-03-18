package com.ilumusecase.server.controllers;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.ActionDTO;
import com.ilumusecase.server.resources.Table;
import com.ilumusecase.server.thread.TableThread;

import lombok.Data;

@RestController
@Data
public class TableGameManagementController {
    
    private DatabaseInterface database;
    private TableThread tableThread;

    @PutMapping("/tables/{talbe_id}/start")
    @SendTo("/tables/{table_id}")
    public void startRound(@PathVariable("table_id") Long tableId){

        Table table = database.getTableDatabase().findById(tableId);

        tableThread.launchGame(table);
    }

    @PutMapping("/tables/{table_id}/pause")
    @SendTo("/tables/{table_id}")
    public Table pauseGame(@PathVariable("table_id") Long tableId){
        Table table = database.getTableDatabase().findById(tableId);
        
        table.setIsPaused(true);
        database.getTableDatabase().updateTable(tableId, table);
        
        return table;
    }

    @PutMapping("/tables/{table_id}/continue")
    @SendTo("/tables/{table_id}")
    public Table continueGame(@PathVariable("table_id") Long tableId){
        Table table = database.getTableDatabase().findById(tableId);
        
        table.setIsPaused(false);
        database.getTableDatabase().updateTable(tableId, table);

        return table;
    }

    @PutMapping("tables/{table_id}/add/action")
    @SendTo("/tables/{table_id}")
    public void addAction(@PathVariable("table_id") Long tableId, @RequestBody ActionDTO action){
        
        Table table = database.getTableDatabase().findById(tableId);
        table.setNewAction(action);
        database.getTableDatabase().updateTable(tableId, table);

    }
}
