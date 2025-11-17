package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.snakeinc.snake.GameParams;

@Data
public class Basket {

    private Grid grid;
    private List<Apple> apples;

    public Basket(Grid grid) {
        apples = new ArrayList<>();
        this.grid = grid;
    }

    public void addApple(Cell cell) {
        if (cell == null) {
            var random = new Random();
            int randX = random.nextInt(0, GameParams.TILES_X);
            int randY = random.nextInt(0, GameParams.TILES_Y);
            while (grid.getTile(randX,randY).snake != null) {
                randX = random.nextInt(0, GameParams.TILES_X);
                randY = random.nextInt(0, GameParams.TILES_Y);
            }
            cell = grid.getTile(randX,randY);
        }
        Apple apple = AppleFactory.createAppleInCell(cell);
        apples.add(apple);
    }

    public void removeAppleInCell(Apple apple, Cell cell) {
        cell.removeApple();
        apples.remove(apple);
    }

    public boolean isEmpty() {
        return apples.isEmpty();
    }

    private void refill(int nApples) {
        for (int i = 0; i < nApples; i++) {
            addApple(null);
        }
    }

    public void refillIfNeeded(int nApples) {
        int missingApple = nApples - apples.size();
        if (missingApple > 0) {
            refill(missingApple);
        }
    }

}
