package ui;

import game.GamePanel;

import java.awt.*;

public class SideBar {

    GamePanel gamepanel;
    int sideBarWidth = 5;
    int sideBarHeight = 13;

    public SideBar(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        sideBarWidth = gamepanel.tileSize * sideBarWidth;
        sideBarHeight = gamepanel.tileSize * sideBarHeight;
    }

    public void draw(Graphics g2) {

    }
}
