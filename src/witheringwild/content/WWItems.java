package witheringwild.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class WWItems {
    public static Item 
            weed,
            wood;
    
    public static void load() {
        weed = new Item("weed", Color.valueOf("65a95e")) {{
            flammability = 0.4f;
            cost = 0.5f;
            healthScaling = -0.2f;
        }};
        
        wood = new Item("wood", Color.valueOf("895841")) {{
            flammability = 0.5f;
            cost = 0.6f;
            healthScaling = 0.1f;
        }};
    }
}
