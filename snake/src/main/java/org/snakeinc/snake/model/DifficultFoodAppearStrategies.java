package org.snakeinc.snake.model;

import org.snakeinc.snake.GameParams;

import java.util.concurrent.ThreadLocalRandom;

public class DifficultFoodAppearStrategies implements FoodAppearStrategies{

    public Cell chooseCell(Grid grid, Snake snake) {
        if (grid == null) return null;

        int width = GameParams.TILES_X;
        int height = GameParams.TILES_Y;
        Cell chosen = null;
        int count = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean nearWall = (x == 0 || y == 0 || x == width - 1 || y == height - 1)
                        || (x == 1 || y == 1 || x == width - 2 || y == height - 2);
                if (!nearWall) continue;

                Cell c = grid.getTile(x, y);
                if (c == null) continue;
                if (c.containsASnake() || c.containsAnFruit()) continue;

                count++;
                if (ThreadLocalRandom.current().nextInt(count) == 0) {
                    chosen = c;
                }
            }
        }

        return chosen;
    }
}
