package game;

import maze.Maze;
import player.Player;
import tile.TileManager;

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
    // SCREEN DIMENSIONS
    public final int maxScreenCol = 28;
    public final int maxScreenRow = 18;
    final int screenWidth = tileSize * maxScreenCol; // 1344 pixels
    final int screenHeight = tileSize * maxScreenRow; // 864 pixels

    // MAZE DIMENSIONS
    private final int mazeWidth = 19;
    private final int mazeHeight = 14;
    private int mazeStartCol = 2;
    private int mazeStartRow = 2;

    private final int mazeStartX = mazeStartCol * tileSize;
    private final int mazeStartY = mazeStartRow * tileSize;

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // Keep playing the game until stopped
    //    public CollisionChecker cChecker = new CollisionChecker(this);
    Player player = new Player(this, keyH);
    Maze maze = new Maze(mazeWidth, mazeHeight);
    TileManager tileM = new TileManager(this, maze);


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // DELTA GAME LOOP
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

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
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2, tileSize, mazeStartX, mazeStartY);
        player.draw(g2);
        g2.dispose();
    }
}