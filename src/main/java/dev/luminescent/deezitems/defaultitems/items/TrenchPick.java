package dev.luminescent.deezitems.defaultitems.items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.ItemAbility;
import dev.luminescent.deezitems.utils.ItemUtils;

import java.util.ArrayList;
import java.util.List;

public class TrenchPick extends DeezItem {

    public static final ItemAbility TRENCH = new ItemAbility("trench", "Break blocks", ItemAbility.AbilityTypes.BLOCK_BREAK, 1.5f);

    public TrenchPick() {
        super(
                "trench_pickaxe",
                Material.DIAMOND_PICKAXE,
                "Trench Pickaxe",
                "Legendary",
                null,
                500,
                List.of(TRENCH),
                -1
                );
    }

    @Override
    public boolean leftClickAirAction(Player player, ItemStack item) {
        return false;
    }

    @Override
    public boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    @Override
    public boolean shiftleftClickAirAction(Player player, ItemStack item) {
        return false;
    }

    @Override
    public boolean shiftleftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    @Override
    public boolean rightClickAirAction(Player player, ItemStack item) {
        return false;
    }

    @Override
    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    @Override
    public boolean shiftrightClickAirAction(Player player, ItemStack item) {
        return false;
    }

    @Override
    public boolean shiftrightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    @Override
    public boolean middleClickAction(Player player, ItemStack item) {
        return false;
    }

    @Override
    public boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item) {

        if (TRENCH.enforceCooldown(player, item, false)) return false;

        Direction direction = null;
        Vector dir = player.getLocation().getDirection().normalize();
        double x = dir.getX();
        double y = dir.getY();
        double z = dir.getZ();
        if(Math.abs(y)>0.5D)
        {
            if (y > 0.0D)
            {
                direction=Direction.UP;
            }
            else
            {
                direction=Direction.DOWN;
            }
        }
        else if (Math.abs(x) > 0.5D)
        {
            if (x > 0.0D)
            {
                direction=Direction.EAST;
            }
            else
            {
                direction=Direction.WEST;
            }
        }
        else if (Math.abs(z) > 0.5D)
        {
            if (z > 0.0D)
            {
                direction=Direction.SOUTH;
            }
            else
            {
                direction=Direction.NORTH;
            }
        }
        //get all locations
        assert direction != null;
        List<Location> toBreak = direction.getRadius(event.getBlock(), 1);

        //break all blocks
        for(Location loc : toBreak) {
            Block b = loc.getBlock();
            if(b.getType() != Material.AIR && !ItemUtils.UNBREAKABLE.contains(b.getType())) {
                b.breakNaturally(item);
            }
        }

        return true;
    }

    @Override
    public boolean projectileHitAction(Player player, ProjectileHitEvent event, ItemStack item) {
        return false;
    }

    @Override
    public void registerRecipe() {

    }

    enum Direction
    {
        NORTH(0,0,-1),
        SOUTH(0,0,1),
        EAST(1,0,0),
        WEST(-1,0,0),
        UP(0,1,0),
        DOWN(0,-1,0);
        private final Vector addition;
        private final boolean isX;
        private final boolean isY;
        private final boolean isZ;
        Direction(int x,int y,int z)
        {
            this.addition=new Vector(x,y,z);
            this.isX=x!=0;
            this.isY=y!=0;
            this.isZ=z!=0;
        }
        public List<Location> getRadius(Block start, int level)
        {
            List<Location> radius= new ArrayList<>();
            radius.add(start.getLocation().clone());
            Location location=start.getLocation();
            for(int i=0;i<level;i++)
            {
                for(int x=-1;x<=1;x++)
                    for(int y=-1;y<=1;y++)
                        for(int z=-1;z<=1;z++)
                        {
                            Location toadd = null;
                            if(isX)
                                toadd=location.clone().add(0,y,z);
                            if(isY)
                                toadd=location.clone().add(x,0,z);
                            if(isZ)
                                toadd=location.clone().add(x,y,0);
                            if(toadd!=null&&!radius.contains(toadd))
                                radius.add(toadd);
                        }
                location=location.add(addition);
            }
            return radius;
        }
    }

}
