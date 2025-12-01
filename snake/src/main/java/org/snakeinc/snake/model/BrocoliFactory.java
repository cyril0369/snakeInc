package org.snakeinc.snake.model;

public class BrocoliFactory {

    public static Brocoli createBrocoliInCell(Cell cell) {
        Brocoli brocoli = new Brocoli();
        cell.addFruit(brocoli);
        return brocoli;
    }

}
