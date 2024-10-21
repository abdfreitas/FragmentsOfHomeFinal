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
        this.mazeGrid = maze.getMaze();
        this.width = maze.getWidth();
        this.height = maze.getHeight();
    }

    public void mazeGenerator(int x, int y) {
        int[] dx = {2, -2, 0, 0};
        int[] dy = {0, 0, 2, -2};
        Random random = new Random();

        // SHUFFLING DIRECTIONS
        ArrayList<Integer> directions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            directions.add(i);
        }
        Collections.shuffle(directions, random);

        for (int direction : directions) { // Cell for recursion
            int nx = x + dx[direction]; // nx = new x
            int ny = y + dy[direction]; // ny = new y

            if (nx >= 0 && ny >= 0 && nx < width && ny < height && mazeGrid[nx][ny] == Maze.WALL) {
                mazeGrid[x + dx[direction] / 2][y + dy[direction] / 2] = Maze.PATH;
                mazeGrid[nx][ny] = Maze.PATH;
                mazeGenerator(nx, ny);
            }
        }
    }
}
