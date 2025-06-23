package witheringwild.content;

import mindustry.type.SectorPreset;

import static witheringwild.content.WWPlanets.*;

public class WWSectorPresets {
    public static SectorPreset spawn;

    public static void load() {
        spawn = new SectorPreset("spawn", bratus, 14) {{
            alwaysUnlocked = true;
            difficulty = 1;
            captureWave = 9;
            
            addStartingItems = true;
        }};
    }
}
