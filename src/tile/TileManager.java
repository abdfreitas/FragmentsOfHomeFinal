package tile;

import game.GamePanel;
import maze.Maze;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.*;
import java.io.*;
import java.util.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    private Maze maze;

    public TileManager(GamePanel gp, Maze maze) {
        this.gp = gp;
        this.maze = maze;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
    }

    public void getTileImage() {
        try {
            // MAZE WALL
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("res/tiles/MazeWallTile.png"));
            tile[0].collision = true;

            // START TILE
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/tiles/StartTile.png"));

            // WIN TILE
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/tiles/WinTile.png"));

            // BASIC PATH
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("res/tiles/BasicPath.png"));
            tile[3].collision = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, int tileSize, int mazeStartX, int mazeStartY) {
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                int cellType = maze.getMaze()[x][y];

                // Debugging
                // System.out.println("Drawing tile at (" + x + ", " + y + "): " + (cellType == Maze.WALL ? "WALL" : "PATH"));
                if (cellType == Maze.WALL) {
                    g.drawImage(tile[0].image,mazeStartX + (x * tileSize), mazeStartY + (y * tileSize), tileSize, tileSize,null);
                } else if (cellType == Maze.PATH) {
                    // Debugging
                    // System.out.println("Path tile at (" + x + ", " + y + ").");
                    g.drawImage(tile[3].image,mazeStartX + (x * tileSize), mazeStartY + (y * tileSize), tileSize, tileSize,null);
                }
            }
        }
    }
}
