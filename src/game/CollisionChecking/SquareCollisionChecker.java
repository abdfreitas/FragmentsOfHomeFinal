package game.CollisionChecking;

import game.GamePanel;
import maze.Maze;
import player.Entity;

public class SquareCollisionChecker implements ICollisionChecker {

    Maze maze;
    GamePanel gp;

    public SquareCollisionChecker(GamePanel gp, Maze maze) {
        this.gp = gp;
        this.maze = maze;
    }

    public void checkTile(Entity entity) {
        //entity.collisionOn = false;

        // Calculate entity's edges using playerX and playerY
        int entityLeftWorldX = entity.playerX + entity.solidArea.x;
        int entityRightWorldX = entity.playerX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.playerY + entity.solidArea.y;
        int entityBottomWorldY = entity.playerY + entity.solidArea.y + entity.solidArea.height;

        // Convert to tile coordinates
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        // Collision tile numbers
        int tileNum1, tileNum2;

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
