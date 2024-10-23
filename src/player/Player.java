package player;
// IMPORT METHODS
import game.GamePanel;
import game.KeyHandler;
import maze.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    boolean isIdle = true;

    // Arrays to hold animation frames
    BufferedImage[] idleFrames = new BufferedImage[8];
    BufferedImage[] upFrames = new BufferedImage[12];
    BufferedImage[] downFrames = new BufferedImage[12];
    BufferedImage[] leftFrames = new BufferedImage[12];
    BufferedImage[] rightFrames = new BufferedImage[12];

    public Player (GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        // SOLID AREA FOR COLLISION CHECKER
        solidArea = new Rectangle(10,27,28,21);
        solidArea.x = 10;
        solidArea.y = 27;
        solidArea.width = 28;
        solidArea.height = 21;

        setDefaultValues();
        getPlayerImage();
    }

    // PLAYER SPAWN POINT AND SPEED
    public void setDefaultValues() {
        spawnX = 1;
        spawnY = 45;

        playerX = spawnX;
        playerY = spawnY;

        speed = 2;
        direction = "down";
    }

    // SPRITE IMAGES
    public void getPlayerImage() {

            try {
                for (int i = 0; i < 8; i++) {
                    idleFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/idle-" + (i + 1) + ".png.png"));
                }

                for (int i = 0; i < 12; i++) {
                        rightFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/right-" + (i + 1) + ".png.png"));
                        leftFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/left-" + (i + 1) + ".png.png"));
                        upFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/up-" + (i + 1) + ".png.png"));
                        downFrames[i] = ImageIO.read(getClass().getResourceAsStream("/player/down-" + (i + 1) + ".png.png"));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    // PLAYER DIRECTION/MOVEMENT WITH KEY HANDLER
    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            isIdle = false;
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK FOR TILE COLLISION
            collisionOn = false;

            if (playerX >= gp.mazeStartX  && playerY >= gp.mazeStartY) {
                // Run collision detection only within maze bounds
                gp.cChecker.checkTile(this);
            }

            System.out.println("Player position: (" + playerX + ", " + playerY + ")");
            System.out.println("Collision status: " + collisionOn);
            // IF COLLISION IS FALSE, THE PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        playerY -= speed;
                        break;
                    case "down":
                        playerY += speed;
                        break;
                    case "left":
                        playerX -= speed;
                        break;
                    case "right":
                        playerX += speed;
                        break;
                }
            }

            // ANIMATE PLAYER MOVEMENT
            spriteCounter++;
            if (spriteCounter > 8) {
                spriteNum = (spriteNum % 12) + 1;
                spriteCounter = 0;
            }
        } else {
            // NO MOVEMENT. THE PLAYER IS IDLE
            isIdle = true;
            spriteCounter++;
            if (spriteCounter > 8) {
                spriteNum = (spriteNum % 8) + 1;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        try {
            if (isIdle) {
                // Show idle animation
                image = idleFrames[spriteNum - 1];
            } else {
                // Show movement animation based on direction
                switch (direction) {
                    case "up":
                        image = upFrames[spriteNum - 1];
                        break;
                    case "down":
                        image = downFrames[spriteNum - 1];
                        break;
                    case "left":
                        image = leftFrames[spriteNum - 1];
                        break;
                    case "right":
                        image = rightFrames[spriteNum - 1];
                        break;
                }
            }
        }
            catch(ArrayIndexOutOfBoundsException e) {
                e.getMessage();
                spriteNum = 1;
            }

        g2.drawImage(image, playerX, playerY, gp.playerSize, gp.playerSize, null);
    }
}