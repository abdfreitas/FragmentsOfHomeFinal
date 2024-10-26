package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MazeGenerator {

    Maze maze;
    private final int[][] mazeGrid;
    private final int width, height;

    public MazeGenerator(Maze maze) {
        this.maze = maze;
        this.width = maze.getWidth();  // Expected width = 19
        this.height = maze.getHeight();  // Expected height = 13
        this.mazeGrid = maze.getMaze();

        // Create an entrance and exit
        mazeGrid[0][6] = Maze.PATH;  // Entrance at the first column (1, 0)
        mazeGrid[1][6] = Maze.PATH;  // Ensures the entrance is never blocked
        mazeGrid[width - 2][height - 1] = Maze.PATH;  // Exit at the last column (width - 2, height - 1)


        // Start generating the maze from (1, 1) to avoid the outer walls
        mazeGenerator(1, 1);
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

            // Ensure the new position is within bounds and is a wall
            if (nx > 0 && ny > 0 && nx < width - 1 && ny < height - 1 && mazeGrid[nx][ny] == Maze.WALL) {
                mazeGrid[x + dx[direction] / 2][y + dy[direction] / 2] = Maze.PATH;  // Knock down wall
                mazeGrid[nx][ny] = Maze.PATH;  // Mark the new cell as part of the path
                mazeGenerator(nx, ny);  // Recursively generate the maze
            }
        }
    }
}
