package witheringwild;

import arc.assets.Loadable;
import mindustry.mod.Mod;
import witheringwild.content.*;

public class WWMod extends Mod {

    @Override
    public void loadContent() {
        WWPlanets.load();
    }
}