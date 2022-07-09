package xyz.akiradev.deezitems.defaultitems;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import xyz.akiradev.deezitems.utils.DeezItem;
import xyz.akiradev.deezitems.utils.ItemAbility;
import xyz.akiradev.deezitems.utils.ItemRarity;
import xyz.akiradev.deezitems.utils.ItemUtils;

import java.util.Arrays;

public class LazerSword extends DeezItem {
    public LazerSword() {
        super(
                Material.DIAMOND_SWORD,
                "Lazer Sword",
                ItemRarity.UNKNOWN,
                1,
                null,
                false,
                Arrays.asList(new ItemAbility("pew", "Shoots lazer when swung", ItemAbility.AbilityTypes.LEFT_CLICK, 3))
        );

    }

    @Override
    public boolean leftClickAirAction(Player player, ItemStack item) {
        if (ItemAbility.enforceCooldown(player, "lazer", 3, item, true)) return false;
        shootLazer(player);
        return true;
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
        return false;
    }

    public boolean shootLazer(Player player) {
        //generate a line of particles between player and block or entity and damage it if entity
        Entity target = ItemUtils.getTargetInRange(player, 50);
        if (target == null) return false;
        Location loc1 = player.getEyeLocation();
        Location loc2 = target.getLocation();
        Vector vector = ItemUtils.getDirectionBetweenLocations(loc1, loc2);
        for(double i = 1; i <= loc1.distance(loc2); i += 0.5) {
            vector.multiply(i);
            loc1.add(vector);
            player.getWorld().playEffect(loc1, Effect.REDSTONE_TORCH_BURNOUT, 1);
            loc1.subtract(vector);
            vector.normalize();
        }
        if(target instanceof Damageable) {
            ((Damageable) target).damage(Math.round(Math.random() * 10));
        }
        return true;
    }
}

