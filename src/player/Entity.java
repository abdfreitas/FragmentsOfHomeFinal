package player;

import game.CollisionChecking.ISolid;

public class Entity {
    public int spawnX, spawnY;
    public int playerX, playerY;
    public int speed;
    public String direction;

    public ISolid solid;
    public boolean collisionOn = false;
    public boolean hasCollectedItem = false;
}
