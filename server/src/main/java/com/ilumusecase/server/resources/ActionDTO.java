package com.ilumusecase.server.resources;

import com.ilumusecase.game.Action;
import com.ilumusecase.game.ActionType;
import com.ilumusecase.game.Round;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    
    private Integer position;

    public Action convertToAction(Round round){
        Action action = new Action();
        action.setActionType(actionType);
        action.setSize(size);
        action.setPlayer(round.getPlayers().get(position));

        return action;
    }

}
