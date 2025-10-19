package witheringwild.world.blocks.production;

import static mindustry.Vars.*;

import arc.func.Boolf;
import arc.math.Mathf;
import arc.struct.*;
import arc.util.Time;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import witheringwild.world.blocks.production.ResourceCreator.*;

public class ResourceCatalyst extends Block {

    /** Can generate small issues causing Creators not to link if over 50 */
    public int range;
    public float cooldownTime = 1f * 60f;
    public float warmupSpeed = 0.019f;

    public Boolf<Building> condition = (b -> true);

    public ResourceCatalyst(String name) {
        super(name);
        update = true;
        solid = true;
        hasItems = true;
    }

    @Override
    public void setStats() {
        stats.timePeriod = cooldownTime;
        super.setStats();
        stats.add(Stat.cooldownTime, cooldownTime / 60f, StatUnit.seconds);
        stats.add(Stat.range, range);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashCircle(x*tilesize+offset, y*tilesize+offset, range, player.team().color);
    }

    public class ResourceCatalystBuild extends Building implements Ranged {

        public float progress;
        public float totalProgress;
        public float warmup;
        
        public Seq<ResourceCreatorBuild> links = new Seq<>(ResourceCreatorBuild.class);

        @Override
        public void placed() {
            super.placed();
            links.clear();
            indexer.eachBlock(null, x, y, range,
                this::canLinkTo,
                other -> ((ResourceCreatorBuild) other).link(this)
            );
        }
        public boolean canLinkTo(Building b) {
            return canLinkTo(b, true);
        }

        public boolean canLinkTo(Building building, boolean checkOther) {
            return (building instanceof ResourceCreatorBuild b) && (b.tile.within(this, range)) && (condition.get(b)) && (checkOther ? ((ResourceCreatorBuild)b).canLinkTo(this, false) : true);
        }

        @Override
        public void onDestroyed() {
            super.onDestroyed();
            unlink();
        }
        @Override
        public void onRemoved() {
            super.onRemoved();
            unlink();
        }
        @Override
        public void pickedUp() {
            super.pickedUp();
            unlink();
        }

        public void link(ResourceCreatorBuild b) {
            this.links.add(b);
            b.links.add(this);
        }

        /** Cleanly unlink both components. */
        public void unlink() {
            for (ResourceCreatorBuild b : links) {
                unlink(b);
            }
        }

        public void unlink(ResourceCreatorBuild b) {
            links.remove(b);
            b.links.remove(this);
        }
        
        @Override
        public float range() {
            return range;
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            if (source instanceof ResourceCreatorBuild) {
                return progress == 1 && this.canConsume() && this.items.get(item) < this.getMaximumAccepted(item);
            }
            return super.acceptItem(source, item);
        }

        @Override
        public void handleItem(Building source, Item item) {
            super.handleItem(source, item);
            if (!itemFilter[item.id]) { // if the catalyst doesn't want to keep it for further consuption
                dump(item);
            }
            if (source instanceof ResourceCreatorBuild) {
                consume();
                progress = 0;
            }
        }

        @Override
        public void updateTile() {
            if (efficiency > 0) {
                progress += getProgressIncrease(cooldownTime);
                warmup = Mathf.approachDelta(warmup, 1f, warmupSpeed);
                for (Item i : content.items()) {
                    if (!itemFilter[i.id]) {
                        dump(i);
                    }
                }
            } else {
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
            }

            totalProgress += warmup * Time.delta;

            progress = Mathf.clamp(progress);

            dump();
        }

        @Override
        public void drawSelect() {
            super.drawSelect();
            Drawf.dashCircle(x, y, range, team.color);
            links.each(b -> Drawf.square(b.tile.getX(), b.tile.getY(), b.block.size*2, Pal.accent));
        }

    }

}
