package maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/*
 * MazeGenerator class is responsible for generating a maze using
 * recursive backtracking algorithm. It modifies the maze grid
 * to create a path from the starting point to the exit.
 */
public class MazeGenerator {

    Maze maze;
    private final int[][] mazeGrid;
    private final int width, height;

    /*
    * Constructor initializes the maze generator with the specified maze.
    * It sets the starting path and triggers the maze generation process.
     */
    public MazeGenerator(Maze maze) {
        this.maze = maze;
        this.width = maze.getWidth();
        this.height = maze.getHeight();
        this.mazeGrid = maze.getMaze();

        mazeGrid[0][6] = Maze.PATH;
        mazeGrid[1][6] = Maze.PATH;
        mazeGrid[width - 2][height - 1] = Maze.PATH;
        mazeGenerator(1, 1);
    }

    /*
    * Recursively generates the maze by carving out paths
    * using randomized directions.
     */
    public void mazeGenerator(int x, int y) {
        int[] dx = {2, -2, 0, 0};
        int[] dy = {0, 0, 2, -2};
        Random random = new Random();

        ArrayList<Integer> directions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            directions.add(i);
        }
        Collections.shuffle(directions, random);

        for (int direction : directions) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (nx > 0 && ny > 0 && nx < width - 1 && ny < height - 1 && mazeGrid[nx][ny] == Maze.WALL) {
                mazeGrid[x + dx[direction] / 2][y + dy[direction] / 2] = Maze.PATH;
                mazeGrid[nx][ny] = Maze.PATH;
                mazeGenerator(nx, ny);
            }
        }
    }
}