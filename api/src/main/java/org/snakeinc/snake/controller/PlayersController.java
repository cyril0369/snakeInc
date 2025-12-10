package org.snakeinc.snake.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long id) {
        super("Player with id " + id + " not found");
    }
}

@RestController
@RequestMapping("api/v1/players")
public class PlayersController {

    private final AtomicLong idCounter = new AtomicLong(1);
    private record PlayerResponse(Long id, String name, Integer age, String category, String createdAt) {}
    private record BodyParam(String name, Integer age) {}
    private final Map<Long, PlayerResponse> players = new HashMap<>();

    @PostMapping
    public PlayerResponse createPlayer(@RequestBody BodyParam body){

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
//test

