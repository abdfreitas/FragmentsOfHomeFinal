package game.CollisionChecking;

import game.GamePanel;
import maze.Maze;
import player.Entity;

public class PolygonCollisionChecker implements ICollisionChecker {

    Maze maze;
    GamePanel gamepanel;

    public PolygonCollisionChecker(GamePanel gamepanel, Maze maze) {
        this.maze = maze;
        this.gamepanel = gamepanel;

    }

    public void checkTile(Entity entity) {

    }
}
