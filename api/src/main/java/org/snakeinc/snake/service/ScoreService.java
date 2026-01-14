package org.snakeinc.snake.service;

import jakarta.validation.Valid;
import org.snakeinc.snake.controller.ScoresController;
import org.snakeinc.snake.dto.ScoreResponse;
import org.snakeinc.snake.exceptions.PlayerNotFoundException;
import org.snakeinc.snake.model.Player;
import org.snakeinc.snake.model.Score;
import org.snakeinc.snake.repository.PlayerRepository;
import org.snakeinc.snake.repository.ScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final PlayerRepository playerRepository;

    public ScoreService(ScoreRepository scoreRepository, PlayerRepository playerRepository) {
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
    }

    public ScoreResponse create(@Valid ScoresController.BodyParam body) {
        // validate snake values
        String snake = body.snake();
        if (!("python".equals(snake) || "anaconda".equals(snake) || "boaConstrictor".equals(snake))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid snake name");
        }

        if (body.score() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Score cannot be negative");
        }

        // check player exists
        Optional<Player> maybe = playerRepository.findById(body.playerId());
        if (maybe.isEmpty()) {
            throw new PlayerNotFoundException(body.playerId());
        }
        Player player = maybe.get();

        Score s = new Score();
        s.setSnake(snake);
        s.setScore(body.score());
        s.setPlayedAt(body.playedAt());
        s.setPlayer(player);

        Score saved = scoreRepository.save(s);

        return new ScoreResponse(saved.getId(), saved.getSnake(), saved.getScore(), saved.getPlayedAt(), saved.getPlayer().getId());
    }
}
