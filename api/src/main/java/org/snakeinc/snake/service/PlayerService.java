package org.snakeinc.snake.service;

import jakarta.validation.Valid;
import org.snakeinc.snake.controller.PlayersController;
import org.snakeinc.snake.dto.PlayerResponse;
import org.snakeinc.snake.exceptions.PlayerNotFoundException;
import org.snakeinc.snake.model.Player;
import org.snakeinc.snake.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public PlayerResponse create(PlayersController.@Valid BodyParam body) {
        String category = body.age() > 18 ? "SENIOR" : "JUNIOR";
        String createdAt = LocalDate.now().toString();

        Player toSave = new Player();
        toSave.setName(body.name());
        toSave.setAge(body.age());
        toSave.setCategory(category);
        toSave.setCreatedAt(createdAt);

        Player saved = repository.save(toSave);

        return new PlayerResponse(saved.getId(), saved.getName(), saved.getAge(), saved.getCategory(), saved.getCreatedAt());
    }

    public PlayerResponse getPlayer(Long id) {
        Optional<Player> maybe = repository.findById(id);
        if (maybe.isEmpty()) {
            throw new PlayerNotFoundException(id);
        }
        Player p = maybe.get();
        return new PlayerResponse(p.getId(), p.getName(), p.getAge(), p.getCategory(), p.getCreatedAt());
    }
}
