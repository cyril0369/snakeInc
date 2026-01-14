package org.snakeinc.snake.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import org.snakeinc.snake.dto.ScoreResponse;
import org.snakeinc.snake.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/scores")
public class ScoresController {

    private final ScoreService scoreService;

    public ScoresController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    public record BodyParam(
            @NotNull(message = "snake is required") String snake,
            @NotNull(message = "score is required") @Min(value = 0, message = "score cannot be negative") Integer score,
            @NotNull(message = "playedAt is required") LocalDateTime playedAt,
            @NotNull(message = "playerId is required") Long playerId) {}

    @PostMapping
    public ScoreResponse createScore(@Valid @RequestBody BodyParam body) {
        return scoreService.create(body);
    }
}

