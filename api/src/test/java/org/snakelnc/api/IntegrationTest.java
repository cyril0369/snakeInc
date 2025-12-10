package org.snakelnc.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.snake.ApiApplication;
import org.snakeinc.snake.dto.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@SpringBootTest(
        classes = ApiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)

public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/v1/players";
    }

    @Test
    void testCreationOfAPlayers() {
        String url = getBaseUrl();
        Map<String,Object> body = Map.of(
                "name","Cyril",
                "age",22
        );

        ResponseEntity<PlayerResponse> response = restTemplate.postForEntity(
                url,
                body,
                PlayerResponse.class
        );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Cyril", response.getBody().name());
        Assertions.assertEquals(22, response.getBody().age());
        Assertions.assertEquals("SENIOR", response.getBody().category());
        Assertions.assertNotNull(response.getBody().id());
        Assertions.assertNotNull(response.getBody().createdAt());
    }
}
