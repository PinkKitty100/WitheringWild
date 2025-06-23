package witheringwild.content;

import mindustry.world.*;
import mindustry.world.draw.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;

import mindustry.content.*;

import mindustry.type.*;

import static mindustry.type.ItemStack.*;

public class WWBlocks {
    public static Block

    // environment
    tree,

    // boulders

    // ores
    
    // wall ores

    // crafting

    // sandbox

    // walls

    // defense

    // transport

    // fluids

    // power

    // production

    grassCutter, harvester,

    // storage

    coreFirst

    // turrets

    // units

    // payloads

    // logic
    
    // campaign only


    ;

    public static void load() {
        Blocks.grass.attributes.set(WWAttributes.organicProduction, 1);
        Blocks.sand.playerUnmineable = false;

        tree = new TreeBlock("tree");

        grassCutter = new AttributeCrafter("grass-cutter") {{
            requirements(Category.production, with(Items.sand, 10));
            outputItem = new ItemStack(WWItems.weed, 2);
            craftTime = 300f;
            itemCapacity = 50;
            hasItems = true;
            hasLiquids = false;
            hasPower = false;

            attribute = WWAttributes.organicProduction;
            baseEfficiency = 0f;
            minEfficiency = 0.001f;
            boostScale = 0.25f;

            size = 2;
            fillsTile = false;
            drawer = new DrawMulti(
                new DrawRegion(),
                new DrawRegion("-rotator", 3.5f, true),
                new DrawRegion("-top")
            );
        }};

        harvester = new Drill("harvester") {{
            requirements(Category.production, with(Items.sand, 10, WWItems.weed, 2));
            tier = 1;
            drillTime = 250;
            size = 1;
            researchCostMultiplier = 0.05f;
          
            consumeLiquid(Liquids.water, 0.5f / 60f).boost();
        }};

        coreFirst = new CoreBlock("core-first") {{
            requirements(Category.effect, with());
            alwaysUnlocked = true;
            isFirstTier = true;

            size = 1;
            health = 600;
            itemCapacity = 2000;
            
            unitCapModifier = 5;
            
            unitType = WWUnitTypes.one;
        }};
    }
}