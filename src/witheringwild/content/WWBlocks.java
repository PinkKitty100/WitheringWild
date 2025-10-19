package witheringwild.content;

import witheringwild.world.blocks.production.*;
import witheringwild.world.draw.*;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.draw.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;

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

    grassCutter, harvester, treeCutter,

    // storage

    coreRoot,

    // turrets

    // units

    // payloads

    // logic
    
    // campaign only


    ;

    public static void load() {
        
        Blocks.grass.attributes.set(WWAttributes.organicProduction, 1);
        Blocks.sand.playerUnmineable = false;

        tree = new ResourceCreator("tree") {{
            requirements(Category.production, with(WWItems.wood, 25));
            outputItem = new ItemStack(WWItems.wood, 2);
            craftTime = 8*60f;
            itemCapacity = 2;
            hasItems = true;
            hasLiquids = hasPower = false;
            drawer = new DrawTree();
        }};

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
 
        treeCutter = new ResourceCatalyst("tree-cutter") {{
            requirements(Category.production, with(Items.sand, 50, WWItems.weed, 10));
            size = 2;
            range = 30;

            condition = (b -> {
                return b.block.name.endsWith("tree");
            });
        }};

        coreRoot = new CoreBlock("core-root") {{
            requirements(Category.effect, with(), true);
            isFirstTier = true;

            size = 1;
            health = 600;
            itemCapacity = 2000;
            
            unitCapModifier = 5;
            
            unitType = WWUnitTypes.one;
        }};
    }
}