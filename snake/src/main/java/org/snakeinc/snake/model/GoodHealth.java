package org.snakeinc.snake.model;
import org.snakeinc.snake.exception.MalnutritionExeption;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;

public class GoodHealth implements HealthsStates {
    @Override
    public void moveUp(Snake snake) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        snake.move(Snake.Direction.UP);
    }
    @Override
    public void moveDown(Snake snake) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        snake.move(Snake.Direction.DOWN);
    }
    @Override
    public void moveLeft(Snake snake)throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        snake.move(Snake.Direction.LEFT);
    }
    @Override
    public void moveRight(Snake snake)throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        snake.move(Snake.Direction.RIGHT);
    }
    @Override
    public void eatPoisonedApple(Snake snake){
        snake.setState(new Poisoned());
    }
    @Override
    public void eatBroccoli(Snake snake){
    }
}
