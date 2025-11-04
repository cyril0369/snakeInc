package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Basket {

    private List<Apple> apples;

    private static Basket instance;

    private Basket() {
        apples = new ArrayList<>();
    }

    public static Basket getInstance() {
        if (instance == null) {
            instance = new Basket();
        }
        return instance;
    }

    public void addApple() {
        apples.add(new Apple());
    }

    public void removeApple(Apple apple) {
        apple.getCell().gameObjectsInTile.remove(apple);
        apples.remove(apple);
    }

    public boolean isEmpty() {
        return apples.isEmpty();
    }

    private void refill(int nApples) {
        for (int i = 0; i < nApples; i++) {
            addApple();
        }
    }

    public void refillIfNeeded(int nApples) {
        int missingApple = nApples - apples.size();
        if (missingApple > 0) {
            refill(missingApple);
        }
    }

}
