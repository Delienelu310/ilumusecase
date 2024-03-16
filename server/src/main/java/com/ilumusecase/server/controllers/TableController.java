package com.ilumusecase.server.controllers;

import java.util.List;
import java.util.Optional;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Table;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TableController {

    DatabaseInterface databaseInterface;
    

    @GetMapping("/tables")
    public List<Table> retrieveTables(
        @RequestParam(name="query", required=false, defaultValue = "%") String query,
        @RequestParam(name="authorUsernames", required=false, defaultValue = "%") List<String> authorUsernames,
        @RequestParam(name="categories", required=false, defaultValue = "%") List<String> categories,
        @RequestParam(name="pageSize", required = false, defaultValue = "10") Integer pageSize, 
        @RequestParam(name="pageNumber", required= false, defaultValue = "0" ) Integer pageNumber
    ){
        return databaseInterface.getTableDatabase().retrieveTables(query, authorUsernames, categories, pageNumber, pageSize);
    }

    @GetMapping("/tables/count")
    public Long retrieveTableCount(
        @RequestParam(name="query", required=false, defaultValue = "%") String query,
        @RequestParam(name="authorUsernames", required=false, defaultValue = "%") List<String> authorUsernames,
        @RequestParam(name="categories", required=false, defaultValue = "%") List<String> categories
    ){
        return databaseInterface.getTableDatabase().retrieveTableCount(query, authorUsernames, categories);
    }


    @GetMapping("/tables/{tableId}")
    public Table retrieveTableById(@PathVariable("tableId") Long tableId){
        Optional<Table> table = databaseInterface.getTableDatabase().findById(tableId);
        if(table.isEmpty())
            throw new RuntimeException();
        return table.get();
    }

    @PostMapping("/tables")
    public Table createTable(@RequestBody Table table){
        return null;
    }

    @DeleteMapping("/tables/{tableId}")
    public void deleteTable(@PathVariable("tableId") Long tableId){
        databaseInterface.getTableDatabase().deleteById(tableId);
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
