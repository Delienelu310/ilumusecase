package com.ilumusecase.server.sockets;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ilumusecase.server.repositories.interfaces.DatabaseInterface;
import com.ilumusecase.server.resources.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
@Data
@NoArgsConstructor
public class TableSocketController {

    private DatabaseInterface database;

    @MessageMapping("/{table_id}/refresh")
    @SendTo("/table/{table_id}")
    public Table refresh(@DestinationVariable("table_id") Long tableId){
        
        Table table = database.getTableDatabase().findById(tableId);

        return table;
    }
}
