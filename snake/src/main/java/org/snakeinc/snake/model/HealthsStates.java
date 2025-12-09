package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.MalnutritionExeption;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;

public interface HealthsStates {
    void moveUp(Snake snake) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption;
    void moveDown(Snake snake) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption;
    void moveLeft(Snake snake) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption;
    void moveRight(Snake snake) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption;
    void eatPoisonedApple(Snake snake);
    void eatBroccoli(Snake snake);
}
