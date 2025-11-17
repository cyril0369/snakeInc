package org.snakeinc.snake.model;

public final class Python extends Snake {

    public Python(AppleEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public void eat(Apple apple, Cell cell) {
        onAppleEatenListener.onAppleEaten(apple, cell);
    }
}
