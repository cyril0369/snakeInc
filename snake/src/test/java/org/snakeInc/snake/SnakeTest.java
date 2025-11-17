package org.snakeInc.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.snake.exception.MalnutritionExeption;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.Game;
import org.snakeinc.snake.model.Snake;

public class SnakeTest {

    Game game = new Game();

    @Test
    public void snakeEatApplesAfterMove_ReturnsCorrectBodySize() throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        game.getBasket().addApple(game.getGrid().getTile(5, 4));
        game.getSnake().move(Snake.Direction.UP);
        Assertions.assertEquals(2, game.getSnake().getSize());
    }

    @Test
    void snakeMovesUp_ReturnCorrectHead() throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        game.getSnake().move(Snake.Direction.UP);
        Assertions.assertEquals(5, game.getSnake().getHead().getX());
        Assertions.assertEquals(4, game.getSnake().getHead().getY());
    }

    @Test
    void snakeOutOfPlayBehavior() throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        while (game.getSnake().getHead().getY() != 0) {
            game.getSnake().move(Snake.Direction.UP);
        }
        Assertions.assertThrows(OutOfPlayException.class, () -> {
            game.getSnake().move(Snake.Direction.UP);
        });
    }

    @Test
    void snakeSelfCollisionBehavior()  throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        game.getBasket().addApple(game.getGrid().getTile(5, 4));
        game.getSnake().move(Snake.Direction.UP);
        game.getBasket().addApple(game.getGrid().getTile(5, 3));
        game.getSnake().move(Snake.Direction.UP);
        game.getBasket().addApple(game.getGrid().getTile(5, 2));
        game.getSnake().move(Snake.Direction.UP);
        game.getBasket().addApple(game.getGrid().getTile(5, 1));
        game.getSnake().move(Snake.Direction.RIGHT);
        game.getSnake().move(Snake.Direction.DOWN);

        Assertions.assertThrows(SelfCollisionException.class, () -> {
            game.getSnake().move(Snake.Direction.LEFT);
        });

    }
}
