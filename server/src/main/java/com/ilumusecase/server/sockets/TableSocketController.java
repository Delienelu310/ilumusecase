package com.ilumusecase.server.sockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.TableDTO;

@Controller
public class TableSocketController {

    @Autowired
    private DatabaseInterface database;

    @MessageMapping("/{table_id}/refresh")
    @SendTo("/table/{table_id}")
    public String refresh(@DestinationVariable("table_id") Long tableId){
        
        TableDTO table = database.getTableDatabase().findById(tableId);

        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.setFilterProvider(filterProvider);
        
        try{
            return objectMapper.writeValueAsString(table);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
