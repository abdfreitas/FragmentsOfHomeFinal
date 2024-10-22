package game;

import items.Item_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // MAKE THESE VALUES RANDOM
    public void setObject() {
        gp.itm[0] = new Item_Key();
        gp.itm[0].worldX = 23 * gp.tileSize;
        gp.itm[0].worldY = 7 * gp.tileSize;

        gp.itm[1] = new Item_Key();
        gp.itm[1].worldX = 23 * gp.tileSize;
        gp.itm[1].worldY = 7 * gp.tileSize;
    }
}


