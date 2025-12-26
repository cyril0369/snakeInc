package org.snakeinc.snake.model;

import java.util.List;
import java.util.Random;

public interface FoodAppearStrategies {
    Cell chooseCell(Grid grid, Snake snake);
}
