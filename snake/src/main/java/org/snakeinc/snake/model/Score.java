package org.snakeinc.snake.model;

import lombok.Getter;

@Getter
public class Score {
    public int food_items_eaten;
    public int number_of_moves;
    public int points;

    public Score () {
        this.food_items_eaten = 0;
        this.number_of_moves = 0;
        this.points = 0;
    }

    public void add_food_items_eaten() {
        food_items_eaten ++;
    }

    public void add_number_of_moves() {
        number_of_moves ++;
    }

    public void add_point(int n) {
        points += n;
    }
}
