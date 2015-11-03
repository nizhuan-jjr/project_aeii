package com.toyknight.aeii.record;

import com.toyknight.aeii.entity.GameCore;
import com.toyknight.aeii.manager.GameEvent;

import java.util.Queue;

/**
 * @author toyknight 9/22/2015.
 */
public class GameRecord {

    private final GameCore game;
    private final Queue<GameEvent> event_queue;

    public GameRecord(GameCore game, Queue<GameEvent> event_queue) {
        this.game = game;
        this.event_queue = event_queue;
    }

    public GameCore getGame() {
        return game;
    }

    public Queue<GameEvent> getEvents() {
        return event_queue;
    }

}
