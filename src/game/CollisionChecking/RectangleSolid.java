package game.CollisionChecking;

import game.GamePanel;
import maze.Maze;
import player.Entity;

import java.awt.*;

//
// The RectangleSolid class implements the ISolid interface and handles collision detection
// for entities using rectangular areas. It checks for collisions based on the entity's movement direction.
//
public class RectangleSolid implements ISolid {

    Maze maze;
    GamePanel gp;

    Rectangle area = new Rectangle(10, 27, 28, 21 );

    /*
     * Constructor initializes the RectangleSolid with the GamePanel and Maze instances.
     */
    public RectangleSolid(GamePanel gp, Maze maze) {
        this.gp = gp;
        this.maze = maze;
    }

    /*
     * Checks for collisions between the entity and the maze tiles based on the entity's direction.
     */
    public void checkTile(Entity entity) {

        // Calculate entity's edges using playerX and playerY
        int entityLeftWorldX = entity.playerX + this.area.x;
        int entityRightWorldX = entity.playerX + this.area.x + this.area.width;
        int entityTopWorldY = entity.playerY + this.area.y;
        int entityBottomWorldY = entity.playerY + this.area.y + this.area.height;

        // Convert to tile coordinates
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        // Collision tile numbers
        int tileNum1, tileNum2;

        // Check collision based on entity's direction
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tilemanager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tilemanager.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tilemanager.tile[tileNum1].collision || gp.tilemanager.tile[tileNum2].collision) {
                    entity.collisionOn = true; // Collision detected
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tilemanager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tilemanager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tilemanager.tile[tileNum1].collision || gp.tilemanager.tile[tileNum2].collision) {
                    entity.collisionOn = true; // Collision detected
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tilemanager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tilemanager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tilemanager.tile[tileNum1].collision || gp.tilemanager.tile[tileNum2].collision) {
                    entity.collisionOn = true; // Collision detected
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tilemanager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tilemanager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tilemanager.tile[tileNum1].collision || gp.tilemanager.tile[tileNum2].collision) {
                    entity.collisionOn = true; // Collision detected
                }
                break;
        }
    }
}
