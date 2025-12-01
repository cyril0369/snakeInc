package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.snakeinc.snake.GameParams;

@Data
public class Basket {

    private Grid grid;
    private List<Fruit> fruits;

    public Basket(Grid grid) {
        fruits = new ArrayList<>();
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
        var random = new Random();
        int randI = random.nextInt(0, 3);
        if (randI >= 1) {
            Apple apple = AppleFactory.createAppleInCell(cell);
            fruits.add(apple);
        }
        else {
            Brocoli brocoli = BrocoliFactory.createBrocoliInCell(cell);
            fruits.add(brocoli);
        }
    }

    public void removeFruitInCell(Fruit fruit, Cell cell) {
        cell.removeFruit();
        fruits.remove(fruit);
    }

    public boolean isEmpty() {
        return fruits.isEmpty();
    }

    private void refill(int nApples) {
        for (int i = 0; i < nApples; i++) {
            addApple(null);
        }
    }

    public void refillIfNeeded(int nApples) {
        int missingApple = nApples - fruits.size();
        if (missingApple > 0) {
            refill(missingApple);
        }
    }

}
