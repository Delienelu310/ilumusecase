package com.ilumusecase.server.resources;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.ActionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ActionDTO {

    @Id
    @GeneratedValue
    private Long id;

    private ActionType actionType;
    private Integer size;
    
    @ManyToOne
    private PlayerDTO player;

    public ActionDTO(Action action){

    }   

    public Action convertToAction(){
        Action action = new Action();
        action.setActionType(actionType);
        action.setSize(size);
        action.setPlayer(player.convertToPlayer());

        return action;
    }

}
