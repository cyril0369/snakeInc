package org.snakeinc.snake.model;

import java.util.Random;
import lombok.Getter;

@Getter
public class Apple implements GameObject {

    private final Cell cell;

    public Apple() {
        var random = new Random();
        cell = Grid.getInstance().getTile(random.nextInt(0, Grid.TILES_X), random.nextInt(0, Grid.TILES_Y));
        cell.gameObjectsInTile.add(this);
    }

}
