package org.snakeinc.snake.dto;

import java.util.List;

public record PlayerStatsResponse(Long playerId, List<SnakeStat> stats) {}

