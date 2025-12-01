package org.snakeinc.snake.model;

import org.snakeinc.snake.exception.MalnutritionExeption;

public final class BoaConstrictor extends Snake{

    public BoaConstrictor(FruitEatenListener listener, Grid grid) {
        super(listener, grid);
    }

    public void eat(Fruit fruit, Cell cell) throws MalnutritionExeption {
        switch (fruit) {
            case Apple apple -> {
                onFruitEatenListener.onFruitEaten(apple, cell);
                int x = body.getLast().getX();
                int y = body.getLast().getY();
                body.remove(body.getLast());
                grid.getTile(x,y).snake = null;
                if (body.isEmpty()) {
                    throw new MalnutritionExeption();
                }
            }
            case Brocoli brocoli -> {
                onFruitEatenListener.onFruitEaten(brocoli, cell);
            }
        }

    }
}
