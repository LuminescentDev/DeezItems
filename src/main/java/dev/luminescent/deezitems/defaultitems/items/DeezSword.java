package dev.luminescent.deezitems.defaultitems.items;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.ItemAbility;
import dev.luminescent.deezitems.utils.ItemUtils;

import java.util.List;

public class DeezSword extends DeezItem {
    public static final ItemAbility fireball = new ItemAbility("fireball", "Shoots a fireball", ItemAbility.AbilityTypes.LEFT_CLICK, 30);
    public DeezSword() {
        super(
                "deez_sword",
                Material.DIAMOND_SWORD,
                "Deez Sword",
                "Uncommon",
                null,
                5,
                List.of(fireball),
                -1
        );

    }

    @Override
    public boolean leftClickAirAction(Player player, ItemStack item) {
        // enforce the 30-second cooldown of the fireball ability
        if (fireball.enforceCooldown(player, item, true)) return false;

        // shoot 3 fireballs
        int amount = 3; // minimum 1
        shootFireBall(player);
        for (int counter = 1; counter < amount; counter++) {
            ItemUtils.delayedTask(() -> shootFireBall(player), 10 * counter);
        }
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

    @Override
    public boolean projectileHitAction(Player player, ProjectileHitEvent event, ItemStack item) {
        return false;
    }

    @Override
    public void registerRecipe() {

    }

    public void shootFireBall(Player player) {
        Fireball thrown = player.launchProjectile(Fireball.class);
        Vector v = player.getEyeLocation().getDirection().multiply(2.0);
        thrown.setVelocity(v);
        thrown.setYield(5);

        thrown.setCustomName("DeezNut");
    }

}