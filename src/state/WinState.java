package state;

import game.GamePanel;
import key.Key;
import maze.Maze;
import player.Entity;
//import timer.GameTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinState {
    GamePanel gamepanel;
    Maze maze;
    boolean gameWon = false;
    //GameTimer timer;
    Key key;

    public WinState(GamePanel gamepanel, Maze maze) {
        this.gamepanel = gamepanel;
        this.maze = maze;
    }

    public boolean checkWinCondition(Entity entity) {
        int winTileX, winTileY;

        winTileX = maze.getWidth() * gamepanel.tileSize;
        winTileY = (maze.getHeight() + 1) * gamepanel.tileSize;

        if (entity.playerX == winTileX && entity.playerY == winTileY && entity.hasCollectedItem) {
            gameWon = true;
            //timer.timerStop();
            return gameWon;
        }
        return false;
    }

    public void draw(Graphics g) {
        JPanel winPanel = new JPanel();
        winPanel.setLayout(new BoxLayout(winPanel, BoxLayout.Y_AXIS));
        winPanel.setBackground(Color.BLACK);

        JLabel winLabel = new JLabel("YOU WIN!");
        winLabel.setForeground(Color.WHITE);
        winLabel.setFont(new Font("Arial", Font.BOLD, 48));
        winLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playAgainButton = new JButton("Play Again");
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
