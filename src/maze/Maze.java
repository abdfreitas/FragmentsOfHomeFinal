package maze;

/*
 * The Maze class represents a maze structure with defined dimensions and pathways.
 * It initializes the maze grid and generates the maze using the MazeGenerator class.
 */
public class Maze {

    private final int mazeWidth, mazeHeight;
    private final int[][] maze;
    public final int mazeStartX, mazeStartY, mazeEndX, mazeEndY;
    public final int mazeStartCol, mazeStartRow;

    public static final int WALL = 1;
    public static final int PATH = 0;

    /*
    * Constructor initializes the maze with specified tile size and sets up
    * its dimensions, starting and ending coordinates.
     */
    public Maze(int tileSize) {
        this.mazeWidth = 19;
        this.mazeHeight = 13;
        this.maze = new int[mazeWidth][mazeHeight];

        this.mazeStartCol = 2;
        this.mazeStartRow = 2;
        this.mazeStartX = mazeStartCol * tileSize;
        this.mazeStartY = mazeStartRow * tileSize;
        this.mazeEndX = mazeStartX + (mazeWidth * tileSize);
        this.mazeEndY = mazeStartY + (mazeHeight * tileSize);

        initializeMaze();
        new MazeGenerator(this);
    }

    /*
     * Initializes the maze grid by setting all cells to the wall state.
     */
    private void initializeMaze() {
        for (int x = 0; x < mazeWidth; x++) {
            for (int y = 0; y < mazeHeight; y++) {
                maze[x][y] = WALL;
            }
        }
    }

    /*
    * Retrieves the maze grid.
     */
    public int[][] getMaze() {
        return maze;
    }

    /*
    * Gets the width of the maze.
     */
    public int getWidth() {
        return mazeWidth;
    }

    /*
    * Gets the height of the maze.
     */
    public int getHeight() {
        return mazeHeight;
    }
}

