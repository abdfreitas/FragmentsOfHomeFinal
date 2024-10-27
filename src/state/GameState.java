package state;

import game.GamePanel;
import maze.Maze;
import player.Entity;
import java.awt.*;

public class GameState {

    GamePanel gamepanel;
    Maze maze;
    Entity entity;
    public WinState winState;
    public GameOverState gameOverState;
    public boolean gameWon = false;
    public boolean gameOver = false;
    public boolean playing = true;

    public GameState(GamePanel gamepanel, Maze maze, Entity entity) {
        this.gamepanel = gamepanel;
        this.maze = maze;
        this.entity = entity;

        this.winState = new WinState(gamepanel, maze, entity);
        this.gameOverState = new GameOverState(gamepanel, maze, entity);
    }

    public void checkState() {
        if (winState.checkWinCondition(entity)) {
            playing = false;
            gameWon = true;
        }
//       else if (gameOverState.checkGameOverCondition(entity)){
//            playing = false;
//            gameOver = true;
//        }
    }
}
