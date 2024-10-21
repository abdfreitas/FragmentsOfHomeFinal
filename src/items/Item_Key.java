package items;

import javax.imageio.ImageIO;

public class Item_Key extends item{

    public Item_Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("items"))
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
