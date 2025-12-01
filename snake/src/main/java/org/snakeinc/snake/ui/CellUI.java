package org.snakeinc.snake.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lombok.AllArgsConstructor;
import org.snakeinc.snake.model.*;

@AllArgsConstructor
public class CellUI {

    private Cell cell;
    private int upperPixelX;
    private int upperPixelY;

    public void drawRectangle(Graphics g) {
        g.fillRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN.darker());
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
    }

    public void drawOval(Graphics g) {
        g.fillOval(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
    }

    public void draw(Graphics g) {

        if (cell.containsAnFruit()) {
            switch (cell.getFruit()) {
                case Apple apple -> {
                    if (apple.isPoisoned()){
                        g.setColor(Color.MAGENTA);
                        drawOval(g);
                    }
                    else {
                        g.setColor(Color.RED);
                        drawOval(g);
                    }
                }
                case Brocoli brocoli -> {
                    if (brocoli.isSteamed()){
                        g.setColor(Color.GREEN.darker().darker());
                        drawOval(g);
                    }
                    else {
                        g.setColor(Color.GREEN);
                        drawOval(g);
                    }
                }
            }
        }
        if (cell.containsASnake()) {
            switch (cell.getSnake()) {
                case Anaconda anaconda -> {
                    g.setColor(Color.GREEN);
                    drawRectangle(g);
                }
                case Python python -> {
                    g.setColor(Color.GRAY);
                    drawRectangle(g);
                }
                case BoaConstrictor boaConstrictor -> {
                    g.setColor(Color.BLUE);
                    drawRectangle(g);
                }

            }
        }

    }

}
