package player;

import game.CollisionChecking.ISolid;

/*
 * Entity class represents the object in the game that has a position, speed,
 * direction, and collision capabilities. It serves as a base class for
 * player.
 */
public class Entity {
    public int spawnX, spawnY;
    public int playerX, playerY;
    public int speed;
    public String direction;

    public ISolid solid;
    public boolean collisionOn = false;
    public boolean hasCollectedItem = false;
}
