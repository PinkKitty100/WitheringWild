package witheringwild.world.draw;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.draw.*;

public class DrawTree extends DrawDefault {

    public float shadowOffset = -4f;

    @Override
    public void draw(Building b){

        float
        x = b.tile.worldx(), y = b.tile.worldy(),
        rot = Mathf.randomSeed(b.tile.pos(), 0, 4) * 90 + Mathf.sin(Time.time + x, 50f, 0.5f) + Mathf.sin(Time.time - y, 65f, 0.9f) + Mathf.sin(Time.time + y - x, 85f, 0.9f),
        w = b.block.region.width * b.block.region.scl(), h = b.block.region.height * b.block.region.scl(),
        scl = 30f, mag = 0.2f;

        TextureRegion shad = b.block.variants == 0 ? b.block.customShadowRegion : b.block.variantShadowRegions[Mathf.randomSeed(b.tile.pos(), 0, Math.max(0, b.block.variantShadowRegions.length - 1))];

        if(shad.found()){
            Draw.z(Layer.power - 1);
            Draw.rect(shad, b.tile.worldx() + shadowOffset, b.tile.worldy() + shadowOffset, rot);
        }

        TextureRegion reg = b.block.variants == 0 ? b.block.region : b.block.variantRegions[Mathf.randomSeed(b.tile.pos(), 0, Math.max(0, b.block.variantRegions.length - 1))];
        
        Draw.z(Layer.power + 1);
        Draw.rectv(reg, x, y, w, h, rot, vec -> vec.add(
            Mathf.sin(vec.y*3 + Time.time, scl, mag) + Mathf.sin(vec.x*3 - Time.time, 70, 0.8f),
            Mathf.cos(vec.x*3 + Time.time + 8, scl + 6f, mag * 1.1f) + Mathf.sin(vec.y*3 - Time.time, 50, 0.2f)
        ));
    }

    
}
