package org.snakeinc.snake.model;

import org.snakeinc.snake.GameParams;

import java.util.List;
import java.util.Random;

public class EasyFoodAppearStrategies implements FoodAppearStrategies{

    public void addApple(Cell cell, Grid grid, List<Fruit> fruits) {
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
}
