package game;

import maze.Maze;
import player.Entity;

public class CollisionChecker {

    Maze maze;
    GamePanel gp;

    public CollisionChecker(GamePanel gp, Maze maze) {
        this.gp = gp;
        this.maze = maze;
    }

    public void checkTile(Entity entity) {
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
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                // Debugging output
                System.out.println("Checking collision: Up");
                System.out.println("Top tiles: " + tileNum1 + ", " + tileNum2);
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // Collision detected
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                // Debugging output
                System.out.println("Checking collision: Down");
                System.out.println("Bottom tiles: " + tileNum1 + ", " + tileNum2);
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // Collision detected
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                // Debugging output
                System.out.println("Checking collision: Left");
                System.out.println("Left tiles: " + tileNum1 + ", " + tileNum2);
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // Collision detected
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                // Debugging output
                System.out.println("Checking collision: Right");
                System.out.println("Right tiles: " + tileNum1 + ", " + tileNum2);
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // Collision detected
                }
                break;
        }
    }

}
