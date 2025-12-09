package org.snakeinc.snake.model;

import java.util.ArrayList;

import lombok.Setter;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.MalnutritionExeption;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;

public abstract sealed class Snake permits Anaconda, Python, BoaConstrictor {

    protected final ArrayList<Cell> body;
    protected final FruitEatenListener onFruitEatenListener;
    protected final Grid grid;
    protected final Score score;
    @Setter
    protected HealthsStates state;

    public Snake(FruitEatenListener listener, Grid grid, Score score) {
        this.body = new ArrayList<>();
        this.onFruitEatenListener = listener;
        this.grid = grid;
        this.score = score;
        this.state = new GoodHealth();
        Cell head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
        head.addSnake(this);
        body.add(head);
        head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y - 1);
        head.addSnake(this);
        body.add(head);
        head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y - 2);
        head.addSnake(this);
        body.add(head);
    }

    public int getSize() {
        return body.size();
    }

    public Cell getHead() {
        return body.getFirst();
    }

    public abstract void eat(Fruit fruit, Cell cell) throws MalnutritionExeption;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public void move(Direction direction) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        int x = getHead().getX();
        int y = getHead().getY();
        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        score.add_number_of_moves();
        Cell newHead = grid.getTile(x, y);
        if (newHead == null) {
            throw new OutOfPlayException();
        }
        if (newHead.containsASnake()) {
            throw new SelfCollisionException();
        }

        // Eat apple :
        if (newHead.containsAnFruit()) {
            this.eat(newHead.getFruit(), newHead);
            score.add_food_items_eaten();
            return;
        }

        // The snake did not eat :
        newHead.addSnake(this);
        body.addFirst(newHead);

        body.getLast().removeSnake();
        body.removeLast();

    }

}
