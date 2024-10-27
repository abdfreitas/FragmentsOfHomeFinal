package tile;

import maze.Maze;
import ui.Screen;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.*;
import java.util.Arrays;


/*
 * TileManager class manages the tiles in the maze, including loading their images,
 * assigning tile types based on the maze layout, and rendering the tiles to the screen.
 */
public class TileManager {

    Screen screen;
    public Tile[] tile;
    public int[][] mapTileNum;
    Maze maze;

    /*
    * Initializes the TileManager with references to the screen and maze,
    * sets up tile images, and assigns the maze grid.
    */
    public TileManager(Screen screen, Maze maze) {
        this.screen = screen;
        this.maze = maze;
        tile = new Tile[10];
        mapTileNum = new int[screen.maxScreenCol][screen.maxScreenRow];
        assignMazeGrid();
        getTileImage();
        System.out.println(Arrays.deepToString(mapTileNum));
    }

    /*
     * Loads tile images from files and assigns collision properties to each tile type.
     */
    public void getTileImage() {

        try {
            // MAZE WALL
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("res/tiles/LavaFloor.png"));
            tile[0].collision = true;

            // START TILE
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/tiles/SideBarBg.png"));
            tile[1].collision = true;

            // WIN TILE
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/tiles/WinTile.png"));

            // BASIC PATH
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("res/tiles/Path.png"));
            tile[3].collision = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


     /*
     * Draws the tiles on the screen based on the maze layout and their respective positions.
     */
    public void draw(Graphics g, int tileSize, int mazeStartX, int mazeStartY) {

        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                int cellType = maze.getMaze()[x][y];

                if (cellType == Maze.WALL) {
                    g.drawImage(tile[0].image,mazeStartX + (x * tileSize), mazeStartY + (y * tileSize), tileSize, tileSize,null);
                } else if (cellType == Maze.PATH) {
                    g.drawImage(tile[3].image,mazeStartX + (x * tileSize), mazeStartY + (y * tileSize), tileSize, tileSize,null);
                }
            }
        }

        for (int x = 23; x < screen.maxScreenCol; x++) {
            for (int y = 0; y < screen.maxScreenRow; y++) {
                g.drawImage(tile[1].image, x * tileSize, y * tileSize, tileSize, tileSize, null);
            }
        }
    }

    /*
     * Assigns tile types in the maze grid based on the maze layout.
     * Sets the appropriate tile number for path tiles in the mapTileNum array.
     */
    public void assignMazeGrid() {

        for (int i = maze.mazeStartCol; i < maze.mazeStartCol + maze.getWidth(); i++) {
            for (int j = maze.mazeStartRow; j < maze.mazeStartRow + maze.getHeight(); j++) {
                int cell = maze.getMaze()[i- maze.mazeStartCol][j-maze.mazeStartRow];
                if (cell == 0) {
                    mapTileNum[i][j] = 3;
                }
            }
        }
    }
}
