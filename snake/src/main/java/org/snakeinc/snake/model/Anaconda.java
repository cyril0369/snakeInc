package org.snakeinc.snake.model;

public final class Anaconda extends Snake {

    public Anaconda(AppleEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public void eat(Apple apple, Cell cell) {
        body.addFirst(cell);
        cell.addSnake(this);
        onAppleEatenListener.onAppleEaten(apple, cell);
    }
}
