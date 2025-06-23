package witheringwild.content;

import mindustry.world.meta.Attribute;

public class WWAttributes {
    public static Attribute
        /** How much this floor/block produces plants (weed) */
            organicProduction;
    
    public static void load() {
        organicProduction = Attribute.add("organicProduction");
    }
}