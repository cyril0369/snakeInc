package org.snakeinc.snake.controller;

import jakarta.validation.Valid;
import org.snakeinc.snake.exceptions.PlayerNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/players")
public class PlayersController {

    private final AtomicLong idCounter = new AtomicLong(1);

    private record PlayerResponse(Long id, String name, Integer age, String category, String createdAt) {}

    private record BodyParam(
            @NotNull(message = "Le nom ne peut pas être null") String name,
            @Min(value = 14, message = "L'âge doit être supérieur à 13") Integer age) {}

    private final Map<Long, PlayerResponse> players = new HashMap<>();

    @PostMapping
    public PlayerResponse createPlayer(@Valid @RequestBody BodyParam body){

        String category = body.age() > 18 ? "SENIOR" : "JUNIOR";

        String createdAt = LocalDate.now().toString();
        long id = idCounter.getAndIncrement();
        PlayerResponse player = new PlayerResponse(id, body.name(), body.age(), category, createdAt);
        players.put(id, player);
        return player;
    }

    @GetMapping("/{id}")
    public PlayerResponse getPlayerById(@PathVariable Long id){
        PlayerResponse player = players.get(id);
        if (player == null){
            throw new PlayerNotFoundException(id);
        }
        return player;
    }

}

