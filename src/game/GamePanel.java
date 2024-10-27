package game;

import game.CollisionChecking.ISolid;
import game.CollisionChecking.PolygonSolid;
import game.CollisionChecking.RectangleSolid;
import key.KeyManager;
import maze.Maze;
import player.Player;
import state.GameState;
import tile.TileManager;
import ui.Screen;
import javax.swing.*;
import java.awt.*;

//
// The GamePanel class is responsible for the main game loop and rendering of the game elements.
// It extends JPanel and implements Runnable to manage game updates and rendering.
//
public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    final int originalPlayerSize = 16;
    final int playerScale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int playerSize = originalPlayerSize * playerScale;


    int FPS = 60;

    Maze maze = new Maze(tileSize);
    Screen screen = new Screen(this);
    public TileManager tilemanager = new TileManager(screen, maze);
    KeyHandler keyhandler = new KeyHandler();
    Thread gamethread; // Keep playing the game until stopped
    public ISolid collisionchecker;
    public Player player = new Player(this, keyhandler, maze);
    GameState state = new GameState(this, maze, player);
    public KeyManager keyManager = new KeyManager(maze, tileSize, tilemanager); // New item manager

    /*
     * Constructor initializes the game panel and sets up the collision checker based on the provided type.
     */
    public GamePanel(String collisionCheckerType) {

        this.setPreferredSize(new Dimension(screen.screenWidth, screen.screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyhandler);
        this.setFocusable(true);

        if (collisionCheckerType.equals("rectangle")) {
            collisionchecker = new RectangleSolid(this, maze);
        }
        else if (collisionCheckerType.equals("polygon")) {
            collisionchecker = new PolygonSolid(this, maze);
        }
        else {
            throw new IllegalArgumentException("Illegal collision checker type: " + collisionCheckerType);
        }
    }

    /*
     * Starts the game thread, which runs the game loop.
     */
    public void startGameThread() {
        gamethread = new Thread(this);
        gamethread.start();
    }

    /*
     * The main game loop that updates and renders the game at a consistent frame rate.
     */
    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gamethread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (long) (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /*
     * Updates game elements, including player movement and state checking.
     */
    public void update() {
        if (state.playing) {
            player.update();
            checkItemCollection();
            state.checkState();
        }
        else if (state.gameWon) {
        }
    }

    /*
     * Checks if the player has collected any items, specifically the key.
     */
    public void checkItemCollection () {
        if (keyManager.getKey() != null && !keyManager.getKey().isCollected()) {
            int playerTileX = player.playerX / tileSize;
            int playerTileY = player.playerY / tileSize;
            int keyTileX = keyManager.getKey().getKeyX();
            int keyTileY = keyManager.getKey().getKeyY();

            keyManager.checkKeyCollision(player);
        }
    }

    /*
     * Paints the game components onto the panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        screen.draw(g2);
        tilemanager.draw(g2, tileSize, maze.mazeStartX, maze.mazeStartY);
        keyManager.draw(g2);
        player.draw(g2);

        if (state.gameWon) {
            state.winState.draw(g2);
        }
        g2.dispose();
    }
}