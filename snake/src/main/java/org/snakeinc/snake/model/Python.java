package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.MalnutritionExeption;

public final class Python extends Snake {

    public Python(FruitEatenListener listener, Grid grid, Score score) {
        super(listener, grid, score);
    }

    public void eat(Fruit fruit, Cell cell) throws MalnutritionExeption {
        switch (fruit) {
            case Apple apple -> {
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
