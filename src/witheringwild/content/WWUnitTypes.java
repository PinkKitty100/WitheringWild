package witheringwild.content;

import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.ai.types.BuilderAI;
import mindustry.entities.bullet.*;
import mindustry.gen.UnitEntity;

public class WWUnitTypes {
    public static UnitType 
        // core
            one

        // tank
        

        // support


        // naval


        ;

    public static void load() {
        one = new UnitType("one") {{

            health = 160;

            flying = true;
            speed = 3.5f;
            drag = 0.05f;
            accel = 0.1f;
            rotateSpeed = 20;
            strafePenalty = 0.9f;

            mineTier = 1; // Maybe more ? Depends on what ores will be added.
            mineSpeed = 1f; // I know this is the default value, but it serves as an indication.
            buildSpeed = 0.75f;
            payloadCapacity = 0;
            fogRadius = 0;
            aiController = BuilderAI::new;

            hitSize = 4;
            coreUnitDock = true;
            crashDamageMultiplier = 0;
            allowedInPayloads = false;
            isEnemy = false;

            buildBeamOffset = 3.8f;
            engineOffset = 4;
            engineSize = 1.2f;


            weapons.add(
                new Weapon("one-weapon") {{
                    bullet = new BasicBulletType(2.5f, 4) {{
                        width = 7;
                        height = 9;
                        lifetime = 1 * 60f;
                        buildingDamageMultiplier = 0;
                        hittable = false;
                    }};
                    
                    reload = 5;
                    recoil = 0;
                    x = 0;
                    shootY = -2;
                    shootCone = 180;

                    mirror = false;
                    useAmmo = false;
                    showStatSprite = false;
                }}
            );
            constructor = UnitEntity::create;
        }};
    }
}
