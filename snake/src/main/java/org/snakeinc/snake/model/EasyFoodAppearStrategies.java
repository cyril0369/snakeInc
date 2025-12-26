package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EasyFoodAppearStrategies implements FoodAppearStrategies{

    public Cell chooseCell(Grid grid, Snake snake) {
        Cell head = snake.getHead();
        if (head == null) return null;

        int maxDistance = 3; // distance max
        List<Cell> candidates = new ArrayList<>();

        for (int dx = -maxDistance; dx <= maxDistance; dx++) {
            for (int dy = -maxDistance; dy <= maxDistance; dy++) {
                int manhattan = Math.abs(dx) + Math.abs(dy);
                if (manhattan == 0 || manhattan > maxDistance) continue;

                int x = head.getX() + dx;
                int y = head.getY() + dy;


                Cell c = grid.getTile(x, y);
                if (c == null) continue;


                if (!c.containsASnake() && !c.containsAnFruit()) {
                    candidates.add(c);
                }
            }
        }

        if (candidates.isEmpty()) return null;
        return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
    }
}
