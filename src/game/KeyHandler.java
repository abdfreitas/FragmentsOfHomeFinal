package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * The KeyHandler class implements KeyListener to manage keyboard input
 * for controlling character movement within the game.
 */
public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    /*
     * Invoked when a key has been typed.
     * This implementation does nothing.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /*
     * Invoked when a key is pressed. Sets the corresponding movement flag to true.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) upPressed = true;
        if(code == KeyEvent.VK_A) leftPressed = true;
        if(code == KeyEvent.VK_S) downPressed = true;
        if(code == KeyEvent.VK_D) rightPressed = true;

    }


    /*
     * Invoked when a key is released. Sets the corresponding movement flag to false.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) upPressed = false;
        if(code == KeyEvent.VK_A) leftPressed = false;
        if(code == KeyEvent.VK_S) downPressed = false;
        if(code == KeyEvent.VK_D) rightPressed = false;
    }
}
