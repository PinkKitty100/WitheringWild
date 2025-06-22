package witheringwild;

import arc.assets.Loadable;
import witheringwild.content.*;

public class WWMod implements Loadable{

    public static void loadContent() {
        WWPlanets.load();
    }
}