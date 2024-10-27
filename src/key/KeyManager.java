package key;

import game.GamePanel;
import maze.Maze;
import player.Player;
import tile.TileManager;

import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class KeyManager {
    public Key key;
    Maze maze;
    TileManager tilemanager;
    public final int tileSize;
    int x,y;

    public KeyManager(Maze maze, int tileSize, TileManager tilemanager) {
        this.maze = maze;
        this.tileSize = tileSize;
        this.tilemanager = tilemanager;
        spawnKey();
    }

    // Spawn the key at a random path position
    public void spawnKey() {
        Random random = new Random();
        BufferedImage keyImage = null;

        try {
            keyImage = ImageIO.read(new File("res/items/Star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            x = random.nextInt(maze.getWidth()-2)+2;
            y = random.nextInt(maze.getHeight()-2)+2;
            // Log for debugging to see if we’re on a path tile
            System.out.println("Attempting spawn at (" + x + ", " + y + ")");
            System.out.println("Tile type: " + tilemanager.mapTileNum[x][y]);

        } while (tilemanager.mapTileNum[x][y] != 3);


        // Confirm that coordinates map to pixel positions in the maze
        key = new Key(x * tileSize, y * tileSize, keyImage);
        System.out.println("Key spawned at (" + x + ", " + y + ") in tile coordinates, pixel: (" + (x * tileSize) + ", " + (y * tileSize) + ")");
    }

    // Check if the player has collected the key
    public void checkKeyCollision(Player player) {
        if (key != null && !key.isCollected()) {
            int keyX = key.getKeyX();
            int keyY = key.getKeyY();

            // Check if the player is within range to collect the key
            if (Math.abs(player.playerX - keyX) < tileSize && Math.abs(player.playerY - keyY) < tileSize) {
                key.collect();
                player.hasCollectedItem = true;
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