package org.snakeinc.snake.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.snakeinc.snake.model.Score;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
    List<Score> findByPlayerId(Long playerId);
}
