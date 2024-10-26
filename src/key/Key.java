package key;

import java.awt.image.BufferedImage;

public class Key {
    public int keyX, keyY; // Key coordinates
    public boolean isCollected;
    public BufferedImage image;

    public Key(int keyX, int keyY, BufferedImage image) {
        this.keyX = keyX;
        this.keyY = keyY;
        this.image = image;
        this.isCollected = false;
    }

    public int getKeyX() {
        return keyX;
    }

    public int getKeyY() {
        return keyY;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void collect() {
        isCollected = true;
    }

    public BufferedImage getImage() {
        return image;
    }
}
