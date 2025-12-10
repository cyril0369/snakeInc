package org.snakeinc.snake.service;

import jakarta.validation.Valid;
import org.snakeinc.snake.controller.PlayersController;
import org.snakeinc.snake.exceptions.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PlayerService {

    private final Map<Long, PlayersController.PlayerResponse> players = new HashMap<>();

    private final AtomicLong idCounter = new AtomicLong(1);


    public PlayersController.PlayerResponse create(PlayersController.@Valid BodyParam body) {
        String category = body.age() > 18 ? "SENIOR" : "JUNIOR";
        String createdAt = LocalDate.now().toString();
        long id = idCounter.getAndIncrement();
        PlayersController.PlayerResponse player = new PlayersController.PlayerResponse(id, body.name(), body.age(), category, createdAt);
        players.put(id, player);
        return player;
    }

    public PlayersController.PlayerResponse getPlayer(Long id) {
        PlayersController.PlayerResponse player = players.get(id);
        if (player == null){
            throw new PlayerNotFoundException(id);
        }
        return player;
    }
}
