package org.snakeinc.snake.model;

public abstract sealed class Fruit permits Apple, Brocoli {
    public Fruit() {}
}
