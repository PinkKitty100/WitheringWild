package witheringwild;

import mindustry.mod.Mod;
import witheringwild.content.*;

public class WWMod extends Mod {

    @Override
    public void loadContent() {
        WWPlanets.load();
        WWSectorPresets.load();
        
        WWAttributes.load();
        WWItems.load();
        WWUnitTypes.load();
        WWBlocks.load();

        WWTechTree.load();
    }
}