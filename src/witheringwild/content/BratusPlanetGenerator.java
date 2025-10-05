package witheringwild.content;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Vec3;
import arc.util.noise.Simplex;
import mindustry.content.Blocks;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.world.Block;

public class BratusPlanetGenerator extends PlanetGenerator {

    @Override
    public float getHeight(Vec3 pos) {
        float height = rawHeight(pos);

        if (Math.abs(pos.z) < 0.15f) { // Mountains circle
            height = height/2 + Mathf.pow(0.15f-Math.abs(pos.z), 0.3f);
        }

        if (height < -0.2f) { // flatten sea
            height = -0.2f;
        }
        
        return height;
    }

    /** Values range between -0.5 and +0.5 */
    public float rawHeight(Vec3 pos) {
        return Simplex.noise3d("height".hashCode(), 2, 0.7, 1.5, pos.x+10, pos.y, pos.z)-0.5f;
    }

    @Override
    public void getColor(Vec3 pos, Color out) {
        Block block = getBlock(pos);
        out.set(block.mapColor).a(block.albedo);
    }

    public Block getBlock(Vec3 pos) {
        float h = getHeight(pos);
        if (h <= -0.2f)     return Blocks.water; // sea
        else if (h < -0.1f) return Blocks.sand; // beach
        else if (h > 0.4f) return Blocks.snow; // snow
        else if (h > 0.3f) return Blocks.stone; // mountains

        else
        if (Simplex.noise3d("color".hashCode(), 4, 1.4, 2.5, pos.x+10, pos.y, pos.z) + pos.z/2.5 > 0.5f) 
            return Blocks.darksand; // Dark plains, will change to darkgrass when it get implemented
        else 
            return Blocks.grass; // plains
    }
}
