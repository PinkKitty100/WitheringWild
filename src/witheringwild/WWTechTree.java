package witheringwild;

import witheringwild.content.*;
/**import mindustry.game.Objectives;
 * Will be useful soon.
*/

import static mindustry.content.TechTree.*;

import mindustry.content.Items;
import mindustry.type.Item;
import mindustry.type.ItemStack;

import static witheringwild.content.WWItems.*;
import static witheringwild.content.WWBlocks.*;
import static witheringwild.content.WWSectorPresets.*;

import static mindustry.Vars.*;
import arc.struct.ObjectFloatMap;

public class WWTechTree {

    public static void load() {
        
        // borrowed / stolen from mindustry's source code (mindustry.content.ErekirTechTree)
        // reduces research cost for Items that are hard to get.
        var costMultipliers = new ObjectFloatMap<Item>();
        for(var item : content.items()) costMultipliers.put(item, 0.9f);
        costMultipliers.put(Items.sand, 1.1f);

        WWPlanets.bratus.techTree = nodeRoot("Bratus", WWBlocks.coreRoot, () -> {
            context().researchCostMultipliers = costMultipliers;

            // Items
            nodeProduce(Items.sand, () -> {
                nodeProduce(weed, () -> {});
            });

            // Production
            node(grassCutter, ItemStack.with(Items.sand, 10), () -> { // Override Research cost because else it's too low
                node(harvester);
            });

            // Sectors
            node(spawn);
        });
    }
}
