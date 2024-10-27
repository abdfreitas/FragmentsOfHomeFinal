package state;

import game.GamePanel;
import maze.Maze;
import player.Entity;
import java.awt.*;

/*
 * GameState class manages the current state of the game,
 * including win and game-over conditions.
 */
public class GameState {

    GamePanel gamepanel;
    Maze maze;
    Entity entity;
    public WinState winState;
    public boolean gameWon = false;
    public boolean playing = true;

    /*
    * Constructor for GameState.
     */
    public GameState(GamePanel gamepanel, Maze maze, Entity entity) {
        this.gamepanel = gamepanel;
        this.maze = maze;
        this.entity = entity;

        this.winState = new WinState(gamepanel, maze, entity);
    }

    /*
     * Checks the current game state to determine if the game is won or lost.
     * Updates the game status accordingly.
     */
    public void checkState() {
        if (winState.checkWinCondition(entity)) {
            playing = false;
            gameWon = true;
        }

    }
}
