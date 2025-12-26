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
    @Getter
    public HealthsStates state;

    @Getter
    protected Cell head;

    protected Direction pendingDirection = null;

    // remember last applied direction so we can detect a player "change" event
    protected Direction lastDirection = null;

    public Snake(FruitEatenListener listener, Grid grid, Score score) {
        this.body = new ArrayList<>();
        this.onFruitEatenListener = listener;
        this.grid = grid;
        this.score = score;
        this.state = new GoodHealth();

        Cell first = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
        first.addSnake(this);
        body.add(first);
        this.head = first;

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

    public void requestDirectionChange(Direction dir) {
        this.pendingDirection = dir;
    }

    protected Direction consumePendingDirection() {
        Direction d = this.pendingDirection;
        this.pendingDirection = null;
        return d;
    }

    public abstract void eat(Fruit fruit, Cell cell) throws MalnutritionExeption;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public void move(Direction direction) throws OutOfPlayException, SelfCollisionException, MalnutritionExeption {
        if (pendingDirection != null && lastDirection != null && direction != lastDirection) {
            direction = consumePendingDirection();
        }

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

        newHead.addSnake(this);
        body.addFirst(newHead);
        this.head = newHead;

        if (newHead.containsAnFruit()) {
            this.eat(newHead.getFruit(), newHead);
            score.add_food_items_eaten();
            this.lastDirection = direction;
            return;
        }

        body.getLast().removeSnake();
        body.removeLast();

        this.lastDirection = direction;
    }

}
