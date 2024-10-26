package state;

import game.GamePanel;
import maze.Maze;
import player.Entity;
//import timer.GameTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverState {

    GamePanel gamepanel;
    Maze maze;
    Entity entity;
    boolean gameOver = false;
    //GameTimer timer;

    public GameOverState(GamePanel gamepanel, Maze maze, Entity entity) {
        this.gamepanel = gamepanel;
        this.maze = maze;
        this.entity = entity;

    }

//    public boolean checkGameOverCondition(Entity entity) {
//        if (timer.getRemainingTime() <= 0) {
//            gameOver = true;
//        }
//        return gameOver;
//    }

    public void draw(Graphics g) {
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
        gameOverPanel.setBackground(Color.BLACK);

        JLabel winLabel = new JLabel("GAME OVER");
        winLabel.setForeground(Color.WHITE);
        winLabel.setFont(new Font("Arial", Font.BOLD, 48));
        winLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playAgainButton = new JButton("Try Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamepanel.startGameThread();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });
    }
}
