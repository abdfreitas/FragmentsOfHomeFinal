package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MazeGenerator {

    private Maze maze;
    private int[][] mazeGrid;
    private int width, height;

    public MazeGenerator(Maze maze) {
        this.maze = maze;
        this.width = maze.getWidth();  // Expected width = 19
        this.height = maze.getHeight();  // Expected height = 13
        this.mazeGrid = maze.getMaze();

        // Initialize the maze with walls
        initializeMaze();

        // Create an entrance and exit
        mazeGrid[0][6] = Maze.PATH;  // Entrance at the first column (1, 0)
        mazeGrid[width - 2][height - 1] = Maze.PATH;  // Exit at the last column (width - 2, height - 1)

        // Start generating the maze from (1, 1) to avoid the outer walls
        mazeGenerator(1, 1);
    }

    // Initialize the maze with walls, leaving space for entrance and exit
    private void initializeMaze() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Set borders to walls
                if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
                    mazeGrid[i][j] = Maze.WALL;  // Set border walls
                } else {
                    mazeGrid[i][j] = Maze.WALL;  // Set inner cells to walls initially
                }
            }
        }
    }

    // Depth-First Search-based Maze Generation
    public void mazeGenerator(int x, int y) {
        int[] dx = {2, -2, 0, 0};  // Move by 2 to ensure walls between paths
        int[] dy = {0, 0, 2, -2};
        Random random = new Random();

        // SHUFFLING DIRECTIONS
        ArrayList<Integer> directions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            directions.add(i);
        }
        Collections.shuffle(directions, random);

        for (int direction : directions) {  // Cell for recursion
            int nx = x + dx[direction];  // nx = new x
            int ny = y + dy[direction];  // ny = new y

            // Ensure that the new position is within bounds and is a wall
            if (nx > 0 && ny > 0 && nx < width - 1 && ny < height - 1 && mazeGrid[nx][ny] == Maze.WALL) {
                mazeGrid[x + dx[direction] / 2][y + dy[direction] / 2] = Maze.PATH;  // Knock down wall
                mazeGrid[nx][ny] = Maze.PATH;  // Mark the new cell as part of the path
                mazeGenerator(nx, ny);  // Recursively generate the maze
            }
        }
    }
}
