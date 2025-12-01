package org.snakeinc.snake.model;

import java.util.Random;
import lombok.Getter;

@Getter
public final class Brocoli extends Fruit{
    boolean steamed;
    public Brocoli() {
        super();
        var random = new Random();
        int randint = random.nextInt(2);
        if (randint > 0) {
            this.steamed = true;
        }
    }
}
