package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.MalnutritionExeption;

public final class Anaconda extends Snake {

    public Anaconda(FruitEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public void eat(Fruit fruit, Cell cell) throws MalnutritionExeption {
        switch (fruit) {
            case Apple apple -> {
                body.addFirst(cell);
                cell.addSnake(this);
                onFruitEatenListener.onFruitEaten(apple, cell);
            }
            case Brocoli brocoli -> {
                onFruitEatenListener.onFruitEaten(brocoli, cell);
                int x = body.getLast().getX();
                int y = body.getLast().getY();
                body.remove(body.getLast());
                grid.getTile(x,y).snake = null;
                if (body.isEmpty()) {
                    throw new MalnutritionExeption();
                }
                x = body.getLast().getX();
                y = body.getLast().getY();
                body.remove(body.getLast());
                grid.getTile(x,y).snake = null;
                if (body.isEmpty()) {
                    throw new MalnutritionExeption();
                }
            }
        }
    }
}
