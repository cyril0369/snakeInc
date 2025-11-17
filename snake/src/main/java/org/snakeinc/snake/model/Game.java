package org.snakeinc.snake.model;

import lombok.Getter;
import org.snakeinc.snake.exception.MalnutritionExeption;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;

import java.util.Random;

@Getter
public class Game {

    private final Grid grid;
    private final Basket basket;
    private final Snake snake;

    public Game() {
        grid = new Grid();
        basket = new Basket(grid);
        basket.refillIfNeeded(1);
        var random = new Random();
        int randint = random.nextInt(3);
        switch(randint) {
            case 1: {
                snake = new Anaconda((apple, cell) -> basket.removeAppleInCell(apple,cell), grid);
                break;
            }
            case 2 : {
                snake = new Python((apple, cell) -> basket.removeAppleInCell(apple,cell), grid);
                break;
            }
            default: snake = new BoaConstrictor((apple, cell) -> basket.removeAppleInCell(apple,cell), grid);
        }
    }

    public void iterate(Snake.Direction direction) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        snake.move(direction);
        basket.refillIfNeeded(1);
    }


}
