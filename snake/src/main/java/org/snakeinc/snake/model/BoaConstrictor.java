package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.MalnutritionExeption;
import org.snakeinc.snake.exception.OutOfPlayException;

public final class BoaConstrictor extends Snake{

    public BoaConstrictor(AppleEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public void eat(Apple apple, Cell cell) throws MalnutritionExeption {
        onAppleEatenListener.onAppleEaten(apple, cell);
        int x = body.getLast().getX();
        int y = body.getLast().getY();
        body.remove(body.getLast());
        grid.getTile(x,y).snake = null;
        if (body.isEmpty()) {
            throw new MalnutritionExeption();
        }
    }
}
