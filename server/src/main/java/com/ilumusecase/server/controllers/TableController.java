package com.ilumusecase.server.controllers;

import java.util.List;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.BotStrategy;
import com.ilumusecase.server.resources.Client;
import com.ilumusecase.server.resources.TableDTO;
import com.ilumusecase.server.resources.TableDetails;
import com.ilumusecase.server.resources.players.BotPlayerDTO;
import com.ilumusecase.server.resources.players.ClientPlayerDTO;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    DatabaseInterface databaseInterface;
    

    @GetMapping("/tables")
    public List<TableDTO> retrieveTables(
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
    public TableDTO retrieveTableById(@PathVariable("tableId") Long tableId){
        return databaseInterface.getTableDatabase().findById(tableId);
    }

    @PostMapping("/tables")
    public TableDTO createTable(@RequestBody TableDetails tableDetails){
        TableDTO table = new TableDTO();
        table.setBlindSize(tableDetails.getBlindSize());
        table.setCategory(databaseInterface.getCategoriesDatabase().retrieveCategoryById(tableDetails.getCategory()));
        table.setAdmin(databaseInterface.getClientDatabase().findById(tableDetails.getAdminUsername()));
        table.setName(tableDetails.getName());

        return databaseInterface.getTableDatabase().createTable(table);
    }

    @DeleteMapping("/tables/{tableId}")
    public void deleteTable(@PathVariable("tableId") Long tableId){
        databaseInterface.getTableDatabase().deleteById(tableId);
    }

    @PutMapping("/tables/{tableId}/add/player/{pos}/client/{username}")
    public TableDTO addClient(@PathVariable("tableId") Long tableId, @PathVariable("pos") Integer pos, @PathVariable("username") String username){
        TableDTO table = databaseInterface.getTableDatabase().findById(tableId);
        if(pos >= table.getCategory().getMaxPlayers()) throw new RuntimeException();
        if(table.getPlayers().containsKey(pos)) throw new RuntimeException();
        
        Client client = databaseInterface.getClientDatabase().findById(username);
        ClientPlayerDTO clientPlayerDTO = new ClientPlayerDTO();
        clientPlayerDTO.setClient(client);

        table.getPlayers().put(pos, clientPlayerDTO);
        
        return databaseInterface.getTableDatabase().updateTable(tableId, table);
          
    }

    @PutMapping("/tables/{tableId}/remove/player/{pos}")
    public TableDTO removeClient(@PathVariable("tableId") Long tableId, @PathVariable("pos") Integer pos){
        TableDTO table = databaseInterface.getTableDatabase().findById(tableId);
        if(pos >= table.getCategory().getMaxPlayers()) throw new RuntimeException();
        if(!table.getPlayers().containsKey(pos)) throw new RuntimeException();

        table.getPlayers().remove(pos);
        
        return databaseInterface.getTableDatabase().updateTable(tableId, table);  
    }

    @PutMapping("/tables/{tableId}/add/player/{pos}/bot/{strategy}")
    public TableDTO addBot(@PathVariable("tableId") Long tableId, @PathVariable("pos") Integer pos, @PathVariable("strategy") String strategy){
        TableDTO table = databaseInterface.getTableDatabase().findById(tableId);
        if(pos >= table.getCategory().getMaxPlayers()) throw new RuntimeException();
        if(table.getPlayers().containsKey(pos)) throw new RuntimeException();

        BotStrategy botStrategy = databaseInterface.getStrategiesDatabaseInterface().retrieveStrategyById(strategy);
        BotPlayerDTO botPlayerDTO = new BotPlayerDTO();
        botPlayerDTO.setBotStrategy(botStrategy);

        table.getPlayers().put(pos, botPlayerDTO);
        
        return databaseInterface.getTableDatabase().updateTable(tableId, table);
    }


}
