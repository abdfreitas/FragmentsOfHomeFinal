package items;

import javax.imageio.ImageIO;

public class Item_Key extends Item {

    public Item_Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/Key.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
