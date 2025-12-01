package org.snakeinc.snake.model;

import java.util.Random;

import lombok.Getter;

@Getter
public final class Apple extends Fruit{

    boolean poisoned;

    public Apple() {
        super();
        var random = new Random();
        int randint = random.nextInt(2);
        if (randint > 0) {
            this.poisoned = true;
        }
    }

}
