package org.snakeinc.snake.model;

import lombok.Getter;
import org.snakeinc.snake.exception.MalnutritionExeption;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;

import java.util.Random;
//test
@Getter
public class Game {

    private final Grid grid;
    private final Basket basket;
    private final Snake snake;
    private final Score score;

    public Game() {
        grid = new Grid();
        basket = new Basket(grid);
        basket.refillIfNeeded(1);
        score = new Score();
        var random = new Random();
        // int randint = 1;
        int randint = random.nextInt(3);
        switch(randint) {
            case 1: {
                snake = new Anaconda((apple, cell) -> basket.removeFruitInCell(apple,cell), grid, score);
                break;
            }
            case 2 : {
                snake = new Python((apple, cell) -> basket.removeFruitInCell(apple,cell), grid, score);
                break;
            }
            default: snake = new BoaConstrictor((apple, cell) -> basket.removeFruitInCell(apple,cell), grid, score);
        }
    }

    public void iterate(Snake.Direction direction) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        switch (direction) {
            case UP -> snake.state.moveUp(snake);
            case DOWN -> snake.state.moveDown(snake);
            case LEFT -> snake.state.moveLeft(snake);
            default -> snake.state.moveRight(snake);
        }
        basket.refillIfNeeded(1);
    }


}
