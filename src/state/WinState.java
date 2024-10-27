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
    Entity entity;
    boolean gameWon = false;
    //GameTimer timer;
    Key key;

    public WinState(GamePanel gamepanel, Maze maze, Entity entity) {
        this.gamepanel = gamepanel;
        this.maze = maze;
        this.entity = entity;
    }

    public boolean checkWinCondition(Entity entity) {
        int winTileX, winTileY;

        winTileX = 19;
        winTileY = 13;

//        winTileX =  / gamepanel.tileSize
//        winTileY = maze.getHeight();

        if (entity.playerX / gamepanel.tileSize == winTileX && entity.playerY / gamepanel.tileSize == winTileY && entity.hasCollectedItem) {
            gameWon = true;
            //timer.timerStop();
            System.out.println("You win!");
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
                gamepanel.remove(winPanel); // Removes the win screen panel
                gamepanel.startGameThread(); // Restarts the game
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

        // Add components to the win panel
        winPanel.add(Box.createVerticalGlue()); // Adds top spacing to center vertically
        winPanel.add(winLabel);
        winPanel.add(Box.createVerticalStrut(20)); // Adds space between the label and play button
        winPanel.add(playAgainButton);
        winPanel.add(Box.createVerticalStrut(10)); // Adds space between buttons
        winPanel.add(exitButton);
        winPanel.add(Box.createVerticalGlue()); // Adds bottom spacing to center vertically


        // Adding the panel to GamePanel
        gamepanel.setLayout(new BorderLayout());
        gamepanel.add(winPanel, BorderLayout.CENTER);

        // Repaint to refresh GamePanel with win screen
        gamepanel.revalidate();
        gamepanel.repaint();
    }
}
