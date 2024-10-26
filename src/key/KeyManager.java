package key;

import game.GamePanel;
import maze.Maze;
import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class KeyManager {
    public Key key;
    Maze maze;
    public final int tileSize;
    int x,y;

    public KeyManager(Maze maze, int tileSize) {
        this.maze = maze;
        this.tileSize = tileSize;
        spawnKey();
    }

    // Spawn the key at a random path position
    public void spawnKey() {
        Random random = new Random();
        BufferedImage keyImage = null;

        try {
            keyImage = ImageIO.read(new File("res/items/Key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = maze.mazeStartCol; i < maze.mazeStartCol + maze.getWidth(); i++) {
            for (int j = maze.mazeStartRow; j < maze.mazeStartRow + maze.getHeight(); j++) {
                do {
                    x = random.nextInt(maze.getWidth());
                    y = random.nextInt(maze.getHeight());
                    // Log for debugging to see if we’re on a path tile
                    System.out.println("Attempting spawn at (" + x + ", " + y + ")");
                    System.out.println("Tile type: " + maze.getMaze()[x][y]);

                } while (maze.getMaze()[x][y] != Maze.PATH);
            }
        }

        // Confirm that coordinates map to pixel positions in the maze
        key = new Key(x * tileSize, y * tileSize, keyImage);
        System.out.println("Key spawned at (" + x + ", " + y + ") in tile coordinates, pixel: (" + (x * tileSize) + ", " + (y * tileSize) + ")");
    }

    // Check if the player has collected the key
    public void checkKeyCollision(int playerX, int playerY) {
        if (key != null && !key.isCollected()) {
            int keyX = key.getKeyX();
            int keyY = key.getKeyY();

            // Check if the player is within range to collect the key
            if (Math.abs(playerX - keyX) < tileSize && Math.abs(playerY - keyY) < tileSize) {
                key.collect();
                System.out.println("Collected");
            }
        }
    }

    // Draw the key if it hasn't been collected
    public void draw(Graphics2D g2) {
        if (key != null && !key.isCollected()) {
            g2.drawImage(key.getImage(), key.getKeyX(), key.getKeyY(), tileSize, tileSize, null);
        }
    }

    public Key getKey() {
        return key;
    }
}
