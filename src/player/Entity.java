package player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int spawnX, spawnY;
    public int playerX, playerY;
    public int speed;


    // ANIMATIONS
    public BufferedImage idle1, idle2, idle3, idle4, idle5, idle6, idle7, idle8;
    public BufferedImage right1, right2, right3, right4, right5, right6, right7, right8, right9, right10, right11, right12;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7, left8, left9, left10, left11, left12;
    public BufferedImage down1, down2, down3, down4, down5, down6, down7, down8, down9, down10, down11, down12;
    public BufferedImage up1, up2, up3, up4, up5, up6, up7, up8, up9, up10, up11, up12;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
