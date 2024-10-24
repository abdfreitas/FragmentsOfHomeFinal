package player;

import game.GamePanel;
import game.KeyHandler;
import graphics.Animation;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
Player class creates a character that's movable by the user and is considered as an entity
from the rest of the program and has personalized animations for each movement. The class also looks for any collisions
between a solid area within the player and the maze, and allows and disables movement accordingly.
 */

public class Player extends Entity {
    GamePanel gamepanel;
    KeyHandler keyhandler;
    boolean isIdle = true;

    /*
    Arrays to store images for animating the player in different movement states.
    */
    Animation idleAnimation;
    Animation upAnimation;
    Animation downAnimation;
    Animation leftAnimation;
    Animation rightAnimation;

    public Player(GamePanel gp, KeyHandler keyhandler) {
        this.gamepanel = gp;
        this.keyhandler = keyhandler;
        solidArea = new Rectangle(10, 27, 28, 21);

        setDefaultValues();
        loadAnimations();
    }

    /*
   Sets the initial values for the player's position and speed.
   */
    public void setDefaultValues() {
        spawnX = 1;
        spawnY = 45;
        playerX = spawnX;
        playerY = spawnY;
        speed = 2;
    }

    /*
    Initializes all player animations.
    */
    public void loadAnimations() {
        idleAnimation = new Animation("/player/idle-", 8, 8);
        upAnimation = new Animation("/player/up-", 12, 8);
        downAnimation = new Animation("/player/down-", 12, 8);
        leftAnimation = new Animation("/player/left-", 12, 8);
        rightAnimation = new Animation("/player/right-", 12, 8);
    }

    /*
    Updates the player's movement state based on input and handles animation.
    - Checks if movement keys are pressed to update the player's position.
    - If a key is pressed, the input is processed, collision is checked, and movement animation plays.
    - If no pressed keys, the player remains idle and the idle animation plays.
    */
    public void update() {
        if (keyhandler.upPressed || keyhandler.downPressed || keyhandler.leftPressed || keyhandler.rightPressed) {
            processInput();
            checkCollision();
        } else {
            animateIdle();
        }
    }

    /*
    Draws the player on the screen using the appropriate animation frame.
    */
    public void draw(Graphics2D g2) {
        BufferedImage image;
        if (isIdle) {
            image = idleAnimation.getNextFrame();
        } else {
            image = movementSprites();
        }
        g2.drawImage(image, playerX, playerY, gamepanel.playerSize, gamepanel.playerSize, null);
    }

    /*
    Processes player input to determine movement direction.
    - isIdle is set to false if any movement key is pressed.
    - direction is updated to reflect the key pressed (up, down, left, right).
    */
    public void processInput() {
        isIdle = false;
        if (keyhandler.upPressed) direction = "up";
        else if (keyhandler.downPressed) direction = "down";
        else if (keyhandler.leftPressed) direction = "left";
        else if (keyhandler.rightPressed) direction = "right";
    }

    /*
    Moves the player in the current direction based on the speed value.
    - Adjusts the player's position on the X or Y axis depending on the direction.
    */
    public void movePlayer() {
        if (direction.equals("up")) playerY -= speed;
        else if (direction.equals("down")) playerY += speed;
        else if (direction.equals("left")) playerX -= speed;
        else if (direction.equals("right")) playerX += speed;
    }

    /*
    Checks for collisions between the player and the environment (e.g., walls).
    - If a collision is detected, the player does not move.
    - If no collision is detected, the player moves.
    */
    public void checkCollision() {
        collisionOn = false;
        if (playerX >= gamepanel.mazeStartX && playerX < gamepanel.mazeEndX &&
                playerY >= gamepanel.mazeStartY && playerY < gamepanel.mazeEndY) {
            gamepanel.collisionchecker.checkTile(this);
//            playerX = playerX - 1;
//            playerY = playerY - 1;
        }
        // Debugging
        System.out.println("Player position: (" + playerX + ", " + playerY + ")");
        System.out.println("Collision status: " + collisionOn);
        //System.out.println("Current playerX and playerY: " + playerX + ", " + playerY);

        if (!collisionOn) {
            movePlayer();
        }
    }

    /*
    Returns the appropriate movement animation frames based on the player's direction.
    - Uses a switch statement to determine the correct frames for each direction.
    */
    public BufferedImage movementSprites() {
        return switch (direction) {
            case "up" -> upAnimation.getNextFrame();
            case "down" -> downAnimation.getNextFrame();
            case "left" -> leftAnimation.getNextFrame();
            case "right" -> rightAnimation.getNextFrame();
            default -> null;
        };
    }

    /*
    If the idle is being animated, that means the player is idle.
     */
    public void animateIdle() {
        isIdle = true;
    }
}
