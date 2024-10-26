package ui.graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/*
Animation class loads the animation frames from a specific path and can be used to animate different objects in the game.
The class also cycles through all the given sprites and updates the current frame using a spriteCounter.
 */
public class Animation {
    private final BufferedImage[] frames;
    private final int frameCount;
    private final int frameLimit;
    private int spriteCounter = 0;
    private int spriteNum = 1;

    /*
    Constructor initializes the Animation class by loading the appropriate frames.
    */
    public Animation(String pathPrefix, int frameCount, int frameLimit) {
        this.frameCount = frameCount;
        this.frameLimit = frameLimit;
        frames = new BufferedImage[frameCount];
        loadFrames(pathPrefix);
    }

    /*
    Loads frames for the animation from a specific path.
    */
    private void loadFrames(String pathPrefix) {
        try {
            for (int i = 0; i < frameCount; i++) {
                frames[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathPrefix + (i + 1) + ".png.png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Updates the current sprite frame based on the spriteCounter.
    */
    public BufferedImage getNextFrame() {
        if (++spriteCounter > frameLimit) {
            spriteNum = (spriteNum % frameCount) + 1;
            spriteCounter = 0;
        }
        return frames[spriteNum - 1];
    }
}
