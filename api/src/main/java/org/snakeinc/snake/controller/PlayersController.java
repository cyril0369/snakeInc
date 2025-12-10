package org.snakeinc.snake.controller;

import jakarta.validation.Valid;
import org.snakeinc.snake.dto.PlayerResponse;
import org.snakeinc.snake.exceptions.PlayerNotFoundException;
import org.snakeinc.snake.service.PlayerService;
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

    private final PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }


    public record BodyParam(
            @NotNull(message = "Le nom ne peut pas être null") String name,
            @Min(value = 14, message = "L'âge doit être supérieur à 13") Integer age) {}


    @PostMapping
    public PlayerResponse createPlayer(@Valid @RequestBody BodyParam body){
        PlayerResponse player = playerService.create(body);
        return player;
    }

    @GetMapping("/{id}")
    public PlayerResponse getPlayerById(@PathVariable Long id){
        PlayerResponse player = playerService.getPlayer(id);
        return player;
    }

}

