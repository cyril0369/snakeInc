package org.snakelnc.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.snakeinc.snake.controller.PlayersController;
import org.snakeinc.snake.dto.PlayerResponse;
import org.snakeinc.snake.service.PlayerService;

public class ApiTest {

    @InjectMocks
    PlayerService playerService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void playerOlderThanEighteenAreSeniors(){
        PlayersController.BodyParam bodyParam = new PlayersController.BodyParam("cyril",20);
        PlayerResponse playerResponse = playerService.create(bodyParam);
        Assertions.assertEquals(playerResponse.category(),"SENIOR");
    }
}

