package com.ilumusecase.server.sockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.TableDTO;

@Controller
public class TableSocketController {

    @Autowired
    private DatabaseInterface database;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/{table_id}/refresh")
    @SendTo("/table/{table_id}")
    @Transactional
    public String refresh(@DestinationVariable("table_id") Long tableId){
        
        TableDTO table = database.getTableDatabase().findById(tableId);

        ObjectMapper objectMapper = new ObjectMapper();
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("ClientPlayer_table", SimpleBeanPropertyFilter.filterOutAllExcept("id"));
        objectMapper.setFilterProvider(filterProvider);
        
        try{
            return objectMapper.writeValueAsString(table);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public void sendToClient(Long tableId ){

        TableDTO table = database.getTableDatabase().findById(tableId);

        ObjectMapper objectMapper = new ObjectMapper();
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("ClientPlayer_table", SimpleBeanPropertyFilter.filterOutAllExcept("id"));
        objectMapper.setFilterProvider(filterProvider);
        try{
            messagingTemplate.convertAndSend("/table/" + tableId, objectMapper.writeValueAsString(table));
        }catch(Exception e){
            throw new RuntimeException();
        }
       
        
    }
}
