package dev.luminescent.deezitems.defaultitems.items;

import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.ItemAbility;
import dev.luminescent.deezitems.utils.ItemUtils;
import dev.rosewood.rosegarden.utils.HexUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class FemboyStick extends DeezItem {

    public static final ItemAbility SMOOCH = new ItemAbility("smooch", "give someone a fat smooch", ItemAbility.AbilityTypes.LEFT_CLICK, 3);

    public FemboyStick() {
        super(
                "femboy_stick",
                Material.STICK,
                "Femboy Stick",
                "Unknown",
                null,
                -1,
                List.of(SMOOCH),
                -1
        );

    }

    @Override
    public boolean leftClickAirAction(Player player, ItemStack item) {
        if (SMOOCH.enforceCooldown(player, item, true)) return false;
        return shootHeart(player);
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

    @Override
    public boolean projectileHitAction(Player player, ProjectileHitEvent event, ItemStack item) {
        return false;
    }

    @Override
    public void registerRecipe() {

    }

    public boolean shootHeart(Player player) {

        Entity target = ItemUtils.getTargetInRange(player, 50);
        Player targetPlayer = null;
        if (target instanceof Player) {
            targetPlayer = (Player) target;
        }
        if (targetPlayer == null) return false;
        targetPlayer.sendTitle("", HexUtils.colorify("#ffc0cb" + player.getDisplayName() + "#ffc0cb Gave you a fat smooch"), 20, 60, 10);
        Location loc1 = player.getEyeLocation();
        Location loc2 = target.getLocation();
        Vector vector = ItemUtils.getDirectionBetweenLocations(loc1, loc2);
        for (double i = 1; i <= loc1.distance(loc2); i += 0.5) {
            vector.multiply(i);
            loc1.add(vector);
            player.getWorld().spawnParticle(Particle.HEART, loc1.getX(), loc1.getY(), loc1.getZ(), 1);
            loc1.subtract(vector);
            vector.normalize();
        }
        return true;
    }

}
