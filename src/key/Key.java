package key;

import java.awt.image.BufferedImage;

/*
 * The Key class represents a collectible item within the game.
 * It contains the items position, its collected state, and an image representation.
 */
public class Key {
    public int keyX, keyY; // Key coordinates
    public boolean isCollected;
    public BufferedImage image;

    /*
    * Constructor initializes the item's position and its image.
     */
    public Key(int keyX, int keyY, BufferedImage image) {
        this.keyX = keyX;
        this.keyY = keyY;
        this.image = image;
        this.isCollected = false;
    }

    /*
    * Retrieves the x-coordinate of the key.
     */
    public int getKeyX() {
        return keyX;
    }

    /*
    * Retrieves the y-coordinate of the key.
     */
    public int getKeyY() {
        return keyY;
    }

    /*
    * Checks if the key has been collected.
     */
    public boolean isCollected() {
        return isCollected;
    }

    /*
    * Marks the key as collected.
     */
    public void collect() {
        isCollected = true;
    }

    /*
    * Retrieves the image representation of the key.
     */
    public BufferedImage getImage() {
        return image;
    }
}
