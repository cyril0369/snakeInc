package org.snakeinc.snake.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.management.monitor.MonitorSettingException;
import javax.swing.*;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.MalnutritionExeption;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.Game;
import org.snakeinc.snake.model.Snake;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    public static final int TILE_PIXEL_SIZE = 20;
    public static final int GAME_PIXEL_WIDTH = TILE_PIXEL_SIZE * GameParams.TILES_X;
    public static final int GAME_PIXEL_HEIGHT = TILE_PIXEL_SIZE * GameParams.TILES_Y;
    private final StatusBar statusBar = new StatusBar();

    private Timer timer;
    private Game game;
    private boolean running = false;
    private Snake.Direction direction = Snake.Direction.RIGHT;

    public GamePanel() {
        this.setPreferredSize(new Dimension(GAME_PIXEL_WIDTH, GAME_PIXEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
        add(statusBar, BorderLayout.SOUTH);
        startGame();
    }

    private void startGame() {
        game = new Game();
        timer = new Timer(100, this);
        timer.start();
        running = true;
        updateStatus();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            UIUtils.draw(g, game);
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        FontMetrics metrics = getFontMetrics(g.getFont());

        String gameOverText = "Game Over";
        String foodText = "Food items eaten : " + game.getScore().getFood_items_eaten();
        String movesText = "Number of moves : " + game.getScore().getNumber_of_moves();
        String pointsText = "Points : " + game.getScore().getPoints();

        int y = GAME_PIXEL_HEIGHT / 2;
        int x = (GAME_PIXEL_WIDTH - metrics.stringWidth(gameOverText)) / 2;

        g.drawString(gameOverText, x, y);

        y += metrics.getHeight() + 10;
        g.drawString(foodText, (GAME_PIXEL_WIDTH - metrics.stringWidth(foodText)) / 2, y);

        y += metrics.getHeight() + 5;
        g.drawString(movesText, (GAME_PIXEL_WIDTH - metrics.stringWidth(movesText)) / 2, y);

        y += metrics.getHeight() + 5;
        g.drawString(pointsText, (GAME_PIXEL_WIDTH - metrics.stringWidth(pointsText)) / 2, y);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            try {
                game.iterate(direction);
            } catch (OutOfPlayException | SelfCollisionException | MalnutritionExeption exception) {
                timer.stop();
                running = false;
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != Snake.Direction.RIGHT) {
                    direction = Snake.Direction.LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != Snake.Direction.LEFT) {
                    direction = Snake.Direction.RIGHT;
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != Snake.Direction.DOWN) {
                    direction = Snake.Direction.UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != Snake.Direction.UP) {
                    direction = Snake.Direction.DOWN;
                }
                break;
        }
        updateStatus();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void updateStatus() {
        if (game == null) return;
        SwingUtilities.invokeLater(() -> {
            try {
                Snake s = game.getSnake();
                String snakeType = s == null ? "unknown" : s.getClass().getSimpleName();
                String health = s == null ? "unknown" : s.getState().getClass().getSimpleName();

                String difficulty;
                try {
                    Object spawn = game.getBasket().getFoodAppearStrategies();
                    difficulty = spawn == null ? "unknown" : spawn.getClass().getSimpleName();
                } catch (Throwable t) {
                    difficulty = "unknown";
                }

                statusBar.setSnakeType(snakeType);
                statusBar.setDifficulty(difficulty);
                statusBar.setHealth(health);
            } catch (Exception e) {
                // keep UI stable on unexpected errors
                statusBar.setSnakeType("-");
                statusBar.setDifficulty("-");
                statusBar.setHealth("-");
            }
        });
    }
}
