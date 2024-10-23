package maze;

public class Maze {

    private int width, height;
    private int[][] maze;
    public static final int WALL = 1;
    public static final int PATH = 0;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[width][height];

        initializeMaze();
        MazeGenerator generator = new MazeGenerator(this);
        // generator.mazeGenerator(0,0);
    }

    private void initializeMaze() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                maze[x][y] = WALL;
            }
        }
    }

    public int[][] getMaze() {
        return maze;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

