package org.snakelnc.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.snakeinc.snake.controller.PlayersController;
import org.snakeinc.snake.dto.PlayerResponse;
import org.snakeinc.snake.service.PlayerService;
import org.snakeinc.snake.model.Player;
import org.snakeinc.snake.repository.PlayerRepository;

public class ApiTest {

    @Mock
    PlayerRepository repository;

    @InjectMocks
    PlayerService playerService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        // stub save to return the player with an id and createdAt
        Mockito.when(repository.save(Mockito.any(Player.class))).thenAnswer(invocation -> {
            Player p = invocation.getArgument(0);
            p.setId(42L);
            if (p.getCreatedAt() == null) p.setCreatedAt(java.time.LocalDate.now().toString());
            return p;
        });
    }

    @Test
    public void playerOlderThanEighteenAreSeniors(){
        PlayersController.BodyParam bodyParam = new PlayersController.BodyParam("cyril",20);
        PlayerResponse playerResponse = playerService.create(bodyParam);
        Assertions.assertEquals(playerResponse.category(),"SENIOR");
    }
}
