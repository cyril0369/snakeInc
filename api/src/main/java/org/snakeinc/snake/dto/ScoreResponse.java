package org.snakeinc.snake.dto;

import java.time.LocalDateTime;

public record ScoreResponse(Long id, String snake, Integer score, LocalDateTime playedAt, Long playerId) {}

