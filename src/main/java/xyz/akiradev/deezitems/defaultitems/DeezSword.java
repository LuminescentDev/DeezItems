package xyz.akiradev.deezitems.defaultitems;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import xyz.akiradev.deezitems.builders.DeezItem;
import xyz.akiradev.deezitems.utils.AbilityTypes;
import xyz.akiradev.deezitems.utils.ItemAbility;
import xyz.akiradev.deezitems.utils.ItemRarity;
import xyz.akiradev.deezitems.utils.ItemUtils;

import java.util.Arrays;

public class DeezSword extends DeezItem {
    public DeezSword() {
        super(
                Material.DIAMOND_SWORD,
                "Deez Sword",
                ItemRarity.UNCOMMON,
                1,
                null,
                false,
                Arrays.asList(new ItemAbility("Nuts", "Lmao my nuts itch", AbilityTypes.LEFT_CLICK, 30))
        );

    }

    @Override
    public boolean leftClickAirAction(Player player, ItemStack item) {
        // enforce the 30 second cooldown of the fireball ability
        if (ItemAbility.enforceCooldown(player, "fireball", 30, item, true)) return false;

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
    public boolean breakBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    public void shootFireBall(Player player) {
        Fireball thrown = player.launchProjectile(Fireball.class);
        Vector v = player.getEyeLocation().getDirection().multiply(2.0);
        thrown.setVelocity(v);
        thrown.setYield(5);

        thrown.setCustomName("DeezNut");
    }

}