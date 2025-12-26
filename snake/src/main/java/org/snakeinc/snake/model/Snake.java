// java
package org.snakeinc.snake.model;

import java.util.ArrayList;

import lombok.Getter;
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

    @Getter
    protected Cell head;

    public Snake(FruitEatenListener listener, Grid grid, Score score) {
        this.body = new ArrayList<>();
        this.onFruitEatenListener = listener;
        this.grid = grid;
        this.score = score;
        this.state = new GoodHealth();

        // first segment -> set as head (tete)
        Cell first = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
        first.addSnake(this);
        body.add(first);
        this.head = first;

        // remaining initial segments
        Cell second = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y - 1);
        second.addSnake(this);
        body.add(second);

        Cell third = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y - 2);
        third.addSnake(this);
        body.add(third);
    }

    public int getSize() {
        return body.size();
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

        // Add the new head cell to the snake immediately and update `tete`
        newHead.addSnake(this);
        body.addFirst(newHead);
        this.head = newHead;

        // Eat apple :
        if (newHead.containsAnFruit()) {
            this.eat(newHead.getFruit(), newHead);
            score.add_food_items_eaten();
            return;
        }

        // The snake did not eat :
        body.getLast().removeSnake();
        body.removeLast();
    }

}
