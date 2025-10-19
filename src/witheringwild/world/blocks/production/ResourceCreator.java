package witheringwild.world.blocks.production;

import static mindustry.Vars.*;

import arc.func.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.blocks.production.*;
import witheringwild.world.blocks.production.ResourceCatalyst.*;

/** Generates ressources. Outputs using a RessourceCatalyst */
public class ResourceCreator extends GenericCrafter {

    public ResourceCreator(String name) {
        super(name);
    }

    @Override
    public boolean outputsItems() {
        return false; // So that it doesn't distribute to conveyors
    }

    public class ResourceCreatorBuild extends GenericCrafterBuild {

        public Seq<ResourceCatalystBuild> links = new Seq<>(ResourceCatalystBuild.class);

        public Boolf<Building> condition = b -> true;

        @Override
        public void placed() {
            super.placed();
            links.clear();
            indexer.eachBlock(null, x, y, 50,
                this::canLinkTo,
                other -> ((ResourceCatalystBuild) other).link(this)
            );
        }
    
        public boolean canLinkTo(Building b) {
            return canLinkTo(b, true);
        }

        public boolean canLinkTo(Building b, boolean checkOther) {
            return b instanceof ResourceCatalystBuild && condition.get(b) && (checkOther ? ((ResourceCatalystBuild)b).canLinkTo(this, false) : true);
        }

        /** Override dump to act using links */
        @Override
        public boolean dump(Item todump){
            if(!block.hasItems || items.total() == 0 || links.size == 0 || (todump != null && !items.has(todump))) return false;

            Seq<Item> allItems = content.items();
            int itemSize = allItems.size;
            Object[] itemArray = allItems.items;

            if(todump == null){
                for(int i = 0; i < links.size; i++){
                    ResourceCatalystBuild other = links.get(i);

                    for(int ii = 0; ii < itemSize; ii++){
                        if(!items.has(ii)) continue;
                        Item item = (Item)itemArray[ii];

                        if(other.acceptItem(self(), item) && canDump(other, item)){
                            other.handleItem(self(), item);
                            items.remove(item, 1);
                            incrementDump(links.size);
                            return true;
                        }
                    }

                    incrementDump(links.size);
                }
            }else{
                for(int i = 0; i < links.size; i++){
                    ResourceCatalystBuild other = links.get(i);

                    if(other.acceptItem(self(), todump) && canDump(other, todump)){
                        other.handleItem(self(), todump);
                        items.remove(todump, 1);
                        incrementDump(links.size);
                        return true;
                    }

                    incrementDump(links.size);
                }
            }

            return false;
        }

        @Override
        public boolean canDump(Building to, Item item) {
            return (to instanceof ResourceCatalystBuild);
        }

        @Override
        public void drawSelect() {
            super.drawSelect();
            links.each(b -> Drawf.square(b.x, b.y, b.block.size*2, Pal.accent));
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

        public void link(ResourceCatalystBuild b) {
            this.links.add(b);
            b.links.add(this);
        }

        /** Cleanly unlink both components. */
        public void unlink() {
            for (ResourceCatalystBuild b : links) {
                unlink(b);
            }
        }

        public void unlink(ResourceCatalystBuild b) {
            this.links.remove(b);
            b.links.remove(this);
        }
    }
    
}
