package maze;

public class Maze {

    private final int mazeWidth, mazeHeight;
    private final int[][] maze;
    public final int mazeStartX, mazeStartY, mazeEndX, mazeEndY;
    public final int mazeStartCol, mazeStartRow;

    public static final int WALL = 1;
    public static final int PATH = 0;

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

    private void initializeMaze() {
        for (int x = 0; x < mazeWidth; x++) {
            for (int y = 0; y < mazeHeight; y++) {
                maze[x][y] = WALL;
            }
        }
    }

    public int[][] getMaze() {
        return maze;
    }

    public int getWidth() {
        return mazeWidth;
    }

    public int getHeight() {
        return mazeHeight;
    }
}

