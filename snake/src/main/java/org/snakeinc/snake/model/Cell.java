package org.snakeinc.snake.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Data
@EqualsAndHashCode
public class Cell {

    @Getter
    private int x;

    @Getter
    private int y;

    Snake snake;
    Fruit fruit;

    protected Cell(int x, int y) {
        setX(x);
        setY(y);
    }

    public void addFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public void addSnake(Snake snake) {
        this.snake = snake;
    }

    public void removeSnake() {
        this.snake = null;
    }

    public void removeFruit() {
        this.fruit = null;
    }

    public boolean containsASnake() {
        return this.snake != null;
    }
    
    public boolean containsAnFruit() {
        return this.fruit != null;
    }

}
