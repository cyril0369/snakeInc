package org.snakeinc.snake.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import java.awt.Font;

public class StatusBar extends JPanel {

    private final JLabel snakeTypeLabel = new JLabel("Snake: -");
    private final JLabel difficultyLabel = new JLabel("Difficulty: -");
    private final JLabel healthLabel = new JLabel("Health: -");

    public StatusBar() {
        super(new FlowLayout(FlowLayout.LEFT, 12, 4));
        snakeTypeLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        difficultyLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        healthLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        add(snakeTypeLabel);
        add(difficultyLabel);
        add(healthLabel);
    }

    public void setSnakeType(String type) {
        SwingUtilities.invokeLater(() -> snakeTypeLabel.setText("Snake: " + (type == null ? "-" : type)));
    }

    public void setDifficulty(String difficulty) {
        SwingUtilities.invokeLater(() -> difficultyLabel.setText("Difficulty: " + (difficulty == null ? "-" : difficulty)));
    }

    public void setHealth(String health) {
        SwingUtilities.invokeLater(() -> healthLabel.setText("Health: " + (health == null ? "-" : health)));
    }
}
