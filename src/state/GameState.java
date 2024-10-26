package state;

import game.GamePanel;
import maze.Maze;
import player.Entity;
import java.awt.*;

public class GameState {

    GamePanel gamepanel;
    Maze maze;
    Entity entity;
    WinState winState;
    GameOverState gameOverState;
    public boolean gameWon = false;
    public boolean gameOver = false;
    public boolean playing = false;

    public GameState(GamePanel gamepanel, Maze maze, Entity entity) {
        this.gamepanel = gamepanel;
        this.maze = maze;
        this.entity = entity;

        this.winState = new WinState(gamepanel, maze);
        this.gameOverState = new GameOverState(gamepanel, maze, entity);

        checkState();
    }

    private void checkState() {
        if (winState.checkWinCondition(entity)) {
            gameWon = true;
        }
//        else if (gameOverState.checkGameOverCondition(entity)){
//            gameOver = true;
//        }
        else if (!gameOver && !gameWon){
            playing = true;
        }
    }

    public void draw(Graphics g) {
        if (gameWon) {
            winState.draw(g);
        }
        else if (gameOver) {
            gameOverState.draw(g);
        }
    }
}
