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


public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    // TILE SIZE
    final int originalTileSize = 16; // 16x16 tile (default size of player, items, map tiles)
    final int scale = 3;

    // PLAYER SIZE
    final int originalPlayerSize = 16;
    final int playerScale = 3;

    // SCALING FOR TILES AND PLAYER
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int playerSize = originalPlayerSize * playerScale; // 48x48 player

    // FPS
    int FPS = 60;

    Maze maze = new Maze(tileSize);
    Screen screen = new Screen(this);
    public TileManager tilemanager = new TileManager(screen, maze);
    KeyHandler keyhandler = new KeyHandler();
    Thread gamethread; // Keep playing the game until stopped
    public ISolid collisionchecker;
    public Player player = new Player(this, keyhandler, maze);
    GameState state = new GameState(this, maze, player);
    public KeyManager keyManager = new KeyManager(maze, tileSize); // New item manager

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

    public void startGameThread() {
        gamethread = new Thread(this);
        gamethread.start();
    }

    // DELTA GAME LOOP
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

    public void update() {
        if (state.playing) {
            player.update();
            checkItemCollection();
        }
    }

    // Check if player has collected the item
    public void checkItemCollection () {
        if (keyManager.getKey() != null && !keyManager.getKey().isCollected()) {
            int playerTileX = player.playerX / tileSize;
            int playerTileY = player.playerY / tileSize;
            //System.out.println("Player: " + playerTileX + " " + playerTileY);
            int keyTileX = keyManager.getKey().getKeyX() / tileSize;
            int keyTileY = keyManager.getKey().getKeyY() / tileSize;
            //System.out.println("Key: " + keyTileX + " " + keyTileY);

            // Check if player collects the key
            keyManager.checkKeyCollision(player.playerX, player.playerY);

            // Check if player's tile matches key's tile
            if (playerTileX == keyTileX && playerTileY == keyTileY) {
                keyManager.getKey().collect();
                player.hasCollectedItem = true; // Set flag for collected key
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        screen.draw(g2);
        tilemanager.draw(g2, tileSize, maze.mazeStartX, maze.mazeStartY);
        keyManager.draw(g2);
        player.draw(g2);
        state.draw(g2);
        g2.dispose();
    }
}