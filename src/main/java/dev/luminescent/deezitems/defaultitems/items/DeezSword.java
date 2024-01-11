package dev.luminescent.deezitems.defaultitems.items;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.ItemAbility;
import dev.luminescent.deezitems.utils.ItemUtils;

import java.util.List;

public class DeezSword extends DeezItem {
    public static final ItemAbility fireball = new ItemAbility("fireball", "Fireball", "Shoots a fireball", ItemAbility.AbilityTypes.LEFT_CLICK, 30);
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

    public void shootFireBall(Player player) {
        Fireball thrown = player.launchProjectile(Fireball.class);
        Vector v = player.getEyeLocation().getDirection().multiply(2.0);
        thrown.setVelocity(v);
        thrown.setYield(5);

        thrown.setCustomName("DeezNut");
    }

}