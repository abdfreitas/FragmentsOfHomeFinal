package game.CollisionChecking;

import game.GamePanel;
import maze.Maze;
import player.Entity;

import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;

public class PolygonSolid implements ISolid {

    Maze maze;
    GamePanel gamepanel;

    double[] polygonX = {0.5, 1, 0.5, 0};
    double[] polygonY = {0, 0.5, 1, 0.5};

    public PolygonSolid(GamePanel gamepanel, Maze maze) {
        this.maze = maze;
        this.gamepanel = gamepanel;
    }

    public void checkTile(Entity entity) {

        for (int i = 0; i < polygonX.length; i++) {

            double pointX = entity.playerX + gamepanel.tileSize * polygonX[i];
            double pointY = entity.playerY + gamepanel.tileSize * polygonY[i];

            // Taking the next index of the polygon and making sure that it goes in a loop and not over the bounds of the array
            int nextIndex = (i+1)%polygonX.length;
            double nextPointX = entity.playerX + gamepanel.tileSize * polygonX[nextIndex];
            double nextPointY = entity.playerY + gamepanel.tileSize * polygonY[nextIndex];

            // Identify the tile coordinates for the two points
            int tileX1 = (int) (pointX / gamepanel.tileSize);
            int tileY1 = (int) (pointY / gamepanel.tileSize);
            int tileX2 = (int) (nextPointX / gamepanel.tileSize);
            int tileY2 = (int) (nextPointY / gamepanel.tileSize);

            // Case 1: Both points in the same tile
            if (inSameTile(tileX1, tileY1, tileX2, tileY2)) {
                if (intersectsTile(tileX1, tileY1)) {
                    entity.collisionOn = true;
                    break;
                }
            }
            // Case 2: Points are in separate tiles, edge crosses two tiles
            else if (isStraightLine(tileX1, tileY1, tileX2, tileY2)) {
                if (intersectsTile(tileX1, tileY1) || intersectsTile(tileX2, tileY2)) {
                    entity.collisionOn = true;
                    break;
                }
            }
            // Cases 3-6: More complex multi-tile intersections
            else {
                // Collect tiles between the two points and check for collisions
                for (int[] tile : tilesBetween(tileX1, tileY1, tileX2, tileY2)) {
                    if (intersectsTile(tile[0], tile[1])) {
                        entity.collisionOn = true;
                        break;
                    }
                }
            }
        }
    }

    private boolean inSameTile(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

    private boolean isStraightLine(int x1, int y1, int x2, int y2) {
        // Checks if the points align horizontally or vertically
        return x1 == x2 || y1 == y2;
    }

    private boolean intersectsTile(int tileX, int tileY) {
        // Retrieve tile number and check if it has a collision property
        int tileNum = gamepanel.tilemanager.mapTileNum[tileX][tileY];
        return gamepanel.tilemanager.tile[tileNum].collision;
    }

    private java.util.List<int[]> tilesBetween(int x1, int y1, int x2, int y2) {
        List<int[]> tiles = new ArrayList<>();

        // Check for a diagonal or multi-tile line between points
        if (x1 != x2 && y1 != y2) {
            tiles.add(new int[]{x1, y1});
            tiles.add(new int[]{x2, y2});
            tiles.add(new int[]{x1, y2});
            tiles.add(new int[]{x2, y1});
        } else {
            // If they are adjacent horizontally or vertically, add intermediary tiles
            if (x1 == x2) {
                int minY = Math.min(y1, y2);
                int maxY = Math.max(y1, y2);
                for (int y = minY; y <= maxY; y++) {
                    tiles.add(new int[]{x1, y});
                }
            } else if (y1 == y2) {
                int minX = Math.min(x1, x2);
                int maxX = Math.max(x1, x2);
                for (int x = minX; x <= maxX; x++) {
                    tiles.add(new int[]{x, y1});
                }
            }
        }
        return tiles;
    }
}