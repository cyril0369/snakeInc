package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Data;

@Data
public class Basket {

    private Grid grid;
    private List<Fruit> fruits;
    private FoodAppearStrategies foodAppearStrategies;

    public Basket(Grid grid) {
        fruits = new ArrayList<>();
        this.grid = grid;
        var random = new Random();
        int randint = random.nextInt(3);
        switch(randint) {
            case 1: {
                this.foodAppearStrategies = new EasyFoodAppearStrategies();
                break;
            }
            case 2 : {
                this.foodAppearStrategies = new DifficultFoodAppearStrategies();
                break;
            }
            default: {
                this.foodAppearStrategies = new NormalFoodAppearStrategies();
                break;
            }
        }
    }

    public void addApple(Cell cell) {
        foodAppearStrategies.addApple(cell, grid, fruits);
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
