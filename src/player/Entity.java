package player;

import java.awt.*;

public class Entity {
    public int spawnX, spawnY;
    public int playerX, playerY;
    public int speed;
    public String direction;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
