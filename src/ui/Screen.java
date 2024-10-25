package ui;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen {

    GamePanel gamepanel;
    private BufferedImage screen;
    public final int maxScreenCol, maxScreenRow;
    public final int screenWidth, screenHeight;

    public Screen(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        this.maxScreenCol = 28;
        this.maxScreenRow = 18;
        this.screenWidth = gamepanel.tileSize * maxScreenCol; //1344 pixels
        this.screenHeight = gamepanel.tileSize * maxScreenRow; //864 pixels

        getScreen();
    }

    public void getScreen() {
        try {
            screen = ImageIO.read(new File( "res/tiles/planetPixelBg.png")); // Adjust path as necessary

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(screen,0,0, screenWidth, screenHeight,null);
    }
}
