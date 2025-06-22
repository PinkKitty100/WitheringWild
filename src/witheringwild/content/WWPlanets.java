package witheringwild.content;

import mindustry.content.Blocks;
import mindustry.game.Team;
import mindustry.type.Planet;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.graphics.g3d.SunMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.HexMesh;
import arc.graphics.Color;



public class WWPlanets {
    public static Planet lumen, bratus;

    public static void load() {
        lumen = new Planet("lumen", null, 13f) {{
            bloom = true;
            accessible = false;
            meshLoader = () -> new SunMesh(
                this, 4,
                4, 0.3, 1.7, 1.2, 1, 1.1f,
                Color.valueOf("dac296"),
                Color.valueOf("faf6f0"),
                Color.valueOf("caa869"),
                Color.valueOf("fff7cc"),
                Color.valueOf("ffef99"),
                Color.valueOf("ffeb7f")
            );
            cloudMeshLoader = () -> new MultiMesh(
                new HexSkyMesh(this, 1, 0.13f, 0.15f, 6, Color.valueOf("51432a").a(0.85f), 2, 0.25f, 2f, 0.51f),
                new HexSkyMesh(this, 1, 0.21f, 0.25f, 8, Color.valueOf("dac296").a(0.7f), 3, 0.3f, 0.8f, 0.1f)
            );
        }};

        bratus = new Planet("bratus", lumen, 1.5f, 1.4f) {{
            generator = new SerpuloPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            alwaysUnlocked = true;
            accessible = true;
            orbitRadius = 45;
            visible = true;
            iconColor = Color.valueOf("98ffa9");
            atmosphereColor = Color.valueOf("98ffa9");
            cloudMeshLoader = () -> new MultiMesh(
                new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.valueOf("73b3a3").a(0.85f), 2, 0.45f, 1f, 0.31f),
                new HexSkyMesh(this, 1, 1.21f, 0.2f, 6, Color.valueOf("f2fff4").a(0.7f), 3, 0.3f, 0.1f, 0.45f)
            );
            startSector = 14;
            orbitSpacing = 1;
            defaultCore = Blocks.coreShard;
            allowLaunchLoadout = false;
            clearSectorOnLose = true;
            allowLaunchToNumbered = false;
            ruleSetter = r -> {
                r.fire = true;
                r.waveTeam = Team.green;
                r.placeRangeCheck = true;
                r.showSpawns = true;
                r.coreDestroyClear = true;
                r.fog = false;
                r.staticFog = false;
                r.onlyDepositCore = false;
                r.deconstructRefundMultiplier = 1f;
            };
        }};
    }
}
