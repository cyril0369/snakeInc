package org.snakeinc.snake.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String snake;

    @Column(nullable = false)
    private Integer score;

    private LocalDateTime playedAt;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public Score() {}

    public Score(Long id, String snake, Integer score, LocalDateTime playedAt, Player player) {
        this.id = id;
        this.snake = snake;
        this.score = score;
        this.playedAt = playedAt;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSnake() {
        return snake;
    }

    public void setSnake(String snake) {
        this.snake = snake;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(LocalDateTime playedAt) {
        this.playedAt = playedAt;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

