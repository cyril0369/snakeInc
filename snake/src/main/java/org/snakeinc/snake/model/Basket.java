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
        if (cell == null) {
            Snake snake = grid.getSnake();
            cell = foodAppearStrategies.chooseCell(grid, snake);
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
