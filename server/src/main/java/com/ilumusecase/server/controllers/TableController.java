package com.ilumusecase.server.controllers;

import java.util.List;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Table;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {

    DatabaseInterface databaseInterface;
    
    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }

    @GetMapping("/tables")
    public List<Table> retrieveTables(){
        return null;
    }

    @GetMapping("/tables/{tableId}")
    public Table retrieveTableById(@PathVariable("tableId") Long tableId){
        return null;
    }

    @PostMapping("/tables")
    public Table createTable(@RequestBody Table table){
        return null;
    }

    @DeleteMapping("/tables/{tableId}")
    public void deleteTable(@PathVariable("tableId") Long tableId){

    }

    @PutMapping("/tables/{tableId}/enter")
    public Table enterTable(@PathVariable("tableId") Long tableId){
        return null;
    }

    @PutMapping("/tables/{tableId}/leave")
    public Table leaveTable(@PathVariable("tableId") Long tableId){
        return null;
    }

    

}
