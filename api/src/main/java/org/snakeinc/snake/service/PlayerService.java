package org.snakeinc.snake.service;

import jakarta.validation.Valid;
import org.snakeinc.snake.controller.PlayersController;
import org.snakeinc.snake.dto.PlayerResponse;
import org.snakeinc.snake.exceptions.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PlayerService {

    private final Map<Long, PlayerResponse> players = new HashMap<>();

    private final AtomicLong idCounter = new AtomicLong(1);


    public PlayerResponse create(PlayersController.@Valid BodyParam body) {
        String category = body.age() > 18 ? "SENIOR" : "JUNIOR";
        String createdAt = LocalDate.now().toString();
        long id = idCounter.getAndIncrement();
        PlayerResponse player = new PlayerResponse(id, body.name(), body.age(), category, createdAt);
        players.put(id, player);
        return player;
    }

    public PlayerResponse getPlayer(Long id) {
        PlayerResponse player = players.get(id);
        if (player == null){
            throw new PlayerNotFoundException(id);
        }
        return player;
    }
}
