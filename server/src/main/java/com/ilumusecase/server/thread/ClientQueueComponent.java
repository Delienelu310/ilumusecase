package com.ilumusecase.server.thread;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ilumusecase.server.customGameLogic.ClientPlayer;

@Component
public class ClientQueueComponent {
    private Map<Long, ClientPlayer> playersWaiting = new HashMap<>();

    public Map<Long, ClientPlayer> getPlayersWaiting() {
        return playersWaiting;
    }

    public void setPlayersWaiting(Map<Long, ClientPlayer> playersWaiting) {
        this.playersWaiting = playersWaiting;
    }
}
