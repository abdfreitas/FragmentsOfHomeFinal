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
            // IDLE IMAGES
            idle1 = ImageIO.read(getClass().getResourceAsStream("/player/idle-1.png.png"));
            idle2 = ImageIO.read(getClass().getResourceAsStream("/player/idle-2.png.png"));
            idle3 = ImageIO.read(getClass().getResourceAsStream("/player/idle-3.png.png"));
            idle4 = ImageIO.read(getClass().getResourceAsStream("/player/idle-4.png.png"));
            idle5 = ImageIO.read(getClass().getResourceAsStream("/player/idle-5.png.png"));
            idle6 = ImageIO.read(getClass().getResourceAsStream("/player/idle-6.png.png"));
            idle7 = ImageIO.read(getClass().getResourceAsStream("/player/idle-7.png.png"));
            idle8 = ImageIO.read(getClass().getResourceAsStream("/player/idle-8.png.png"));

            // RIGHT IMAGES
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right-1.png.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right-2.png.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/right-3.png.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/right-4.png.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/right-5.png.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/player/right-6.png.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/player/right-7.png.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/player/right-8.png.png"));
            right9 = ImageIO.read(getClass().getResourceAsStream("/player/right-9.png.png"));
            right10 = ImageIO.read(getClass().getResourceAsStream("/player/right-10.png.png"));
            right11 = ImageIO.read(getClass().getResourceAsStream("/player/right-11.png.png"));
            right12 = ImageIO.read(getClass().getResourceAsStream("/player/right-12.png.png"));

            // LEFT IMAGES
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left-1.png.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left-2.png.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/left-3.png.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/left-4.png.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/left-5.png.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/player/left-6.png.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/player/left-7.png.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/player/left-8.png.png"));
            left9 = ImageIO.read(getClass().getResourceAsStream("/player/left-9.png.png"));
            left10 = ImageIO.read(getClass().getResourceAsStream("/player/left-10.png.png"));
            left11 = ImageIO.read(getClass().getResourceAsStream("/player/left-11.png.png"));
            left12 = ImageIO.read(getClass().getResourceAsStream("/player/left-12.png.png"));

            // DOWN IMAGES
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down-1.png.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down-2.png.png"));
            //down3 = ImageIO.read(getClass().getResourceAsStream("/player/down-3.png.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/down-4.png.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/down-5.png.png"));
            //down6 = ImageIO.read(getClass().getResourceAsStream("/player/down-6.png.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/player/down-7.png.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/player/down-8.png.png"));
            down9 = ImageIO.read(getClass().getResourceAsStream("/player/down-9.png.png"));
            down10 = ImageIO.read(getClass().getResourceAsStream("/player/down-10.png.png"));
            //down11 = ImageIO.read(getClass().getResourceAsStream("/player/down-11.png.png"));
            down12 = ImageIO.read(getClass().getResourceAsStream("/player/down-12.png.png"));
            down13 = ImageIO.read(getClass().getResourceAsStream("/player/down-13.png.png"));
            //down14 = ImageIO.read(getClass().getResourceAsStream("/player/down-14.png.png"));
            down15 = ImageIO.read(getClass().getResourceAsStream("/player/down-15.png.png"));
            down16 = ImageIO.read(getClass().getResourceAsStream("/player/down-16.png.png"));

            // UP IMAGES
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up-1.png.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up-2.png.png"));
            //up3 = ImageIO.read(getClass().getResourceAsStream("/player/up-3.png.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/up-4.png.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/player/up-5.png.png"));
            //up6 = ImageIO.read(getClass().getResourceAsStream("/player/up-6.png.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/player/up-7.png.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/player/up-8.png.png"));
            up9 = ImageIO.read(getClass().getResourceAsStream("/player/up-9.png.png"));
            up10 = ImageIO.read(getClass().getResourceAsStream("/player/up-10.png.png"));
            //up11 = ImageIO.read(getClass().getResourceAsStream("/player/up-11.png.png"));
            up12 = ImageIO.read(getClass().getResourceAsStream("/player/up-12.png.png"));
            up13 = ImageIO.read(getClass().getResourceAsStream("/player/up-13.png.png"));
            //up14 = ImageIO.read(getClass().getResourceAsStream("/player/up-14.png.png"));
            up15 = ImageIO.read(getClass().getResourceAsStream("/player/up-15.png.png"));
            up16 = ImageIO.read(getClass().getResourceAsStream("/player/up-16.png.png"));
        }
        catch (IOException e) {
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
            gp.cChecker.checkTile(this);

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

        if (isIdle) {
            // SHOW IDLE ANIMATIONS
            switch (spriteNum) {
                case 1: image = idle1; break;
                case 2: image = idle2; break;
                case 3: image = idle3; break;
                case 4: image = idle4; break;
                case 5: image = idle5; break;
                case 6: image = idle6; break;
                case 7: image = idle7; break;
                case 8: image = idle8; break;
            }
        }
        else {
            switch (direction) {
                // SHOW WALKING ANIMATIONS
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up4;
                    }
                    if (spriteNum == 4) {
                        image = up5;
                    }
                    if (spriteNum == 5) {
                        image = up7;
                    }
                    if (spriteNum == 6) {
                        image = up8;
                    }
                    if (spriteNum == 7) {
                        image = up9;
                    }
                    if (spriteNum == 8) {
                        image = up10;
                    }
                    if (spriteNum == 9) {
                        image = up12;
                    }
                    if (spriteNum == 10) {
                        image = up13;
                    }
                    if (spriteNum == 11) {
                        image = up15;
                    }
                    if (spriteNum == 12) {
                        image = up16;
                    }
                    break;

                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 3) {
                        image = down4;
                    }
                    if (spriteNum == 4) {
                        image = down5;
                    }
                    if (spriteNum == 5) {
                        image = down7;
                    }
                    if (spriteNum == 6) {
                        image = down8;
                    }
                    if (spriteNum == 7) {
                        image = down9;
                    }
                    if (spriteNum == 8) {
                        image = down10;
                    }
                    if (spriteNum == 9) {
                        image = down12;
                    }
                    if (spriteNum == 10) {
                        image = down13;
                    }
                    if (spriteNum == 11) {
                        image = down15;
                    }
                    if (spriteNum == 12) {
                        image = down16;
                    }
                    break;

                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                    if (spriteNum == 4) {
                        image = left4;
                    }
                    if (spriteNum == 5) {
                        image = left5;
                    }
                    if (spriteNum == 6) {
                        image = left6;
                    }
                    if (spriteNum == 7) {
                        image = left7;
                    }
                    if (spriteNum == 8) {
                        image = left8;
                    }
                    if (spriteNum == 9) {
                        image = left9;
                    }
                    if (spriteNum == 10) {
                        image = left10;
                    }
                    if (spriteNum == 11) {
                        image = left11;
                    }
                    if (spriteNum == 12) {
                        image = left12;
                    }
                    break;

                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                    if (spriteNum == 4) {
                        image = right4;
                    }
                    if (spriteNum == 5) {
                        image = right5;
                    }
                    if (spriteNum == 6) {
                        image = right6;
                    }
                    if (spriteNum == 7) {
                        image = right7;
                    }
                    if (spriteNum == 8) {
                        image = right8;
                    }
                    if (spriteNum == 9) {
                        image = right9;
                    }
                    if (spriteNum == 10) {
                        image = right10;
                    }
                    if (spriteNum == 11) {
                        image = right11;
                    }
                    if (spriteNum == 12) {
                        image = right12;
                    }
                    break;

            }
        }
        g2.drawImage(image, playerX, playerY, gp.playerSize, gp.playerSize, null);
    }
}
