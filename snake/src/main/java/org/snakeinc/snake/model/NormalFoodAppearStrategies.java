package org.snakeinc.snake.model;

import org.snakeinc.snake.GameParams;

import java.util.Random;

public class NormalFoodAppearStrategies implements FoodAppearStrategies {

    public Cell chooseCell(Grid grid, Snake snake) {
        var random = new Random();
        int randX = random.nextInt(0, GameParams.TILES_X);
        int randY = random.nextInt(0, GameParams.TILES_Y);
        while (grid.getTile(randX,randY).snake != null) {
            randX = random.nextInt(0, GameParams.TILES_X);
            randY = random.nextInt(0, GameParams.TILES_Y);
        }
        return grid.getTile(randX,randY);
    }
}
