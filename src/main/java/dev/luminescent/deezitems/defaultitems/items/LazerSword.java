package dev.luminescent.deezitems.defaultitems.items;

import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.ItemAbility;
import dev.luminescent.deezitems.utils.ItemUtils;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class LazerSword extends DeezItem {

    public static final ItemAbility PEW = new ItemAbility("pew", "pew", "Shoots lazer when swung", ItemAbility.AbilityTypes.LEFT_CLICK, 3);

    public LazerSword() {
        super(
                "lazer_sword",
                Material.DIAMOND_SWORD,
                "Lazer Sword",
                "Unknown",
                null,
                -1,
                List.of(PEW),
                -1
        );

    }

    @Override
    public boolean leftClickAirAction(Player player, ItemStack item) {
        if (PEW.enforceCooldown(player, item, true)) return false;
        shootLazer(player);
        return true;
    }

    public void shootLazer(Player player) {
        //generate a line of particles between player and block or entity and damage it if entity
        Entity target = ItemUtils.getTargetInRange(player, 50);
        if (target == null) return;
        Location loc1 = player.getEyeLocation();
        Location loc2 = target.getLocation();
        Vector vector = ItemUtils.getDirectionBetweenLocations(loc1, loc2);
        for (double i = 1; i <= loc1.distance(loc2); i += 0.5) {
            vector.multiply(i);
            loc1.add(vector);
            player.getWorld().playEffect(loc1, Effect.REDSTONE_TORCH_BURNOUT, 1);
            loc1.subtract(vector);
            vector.normalize();
        }
        if (target instanceof Damageable) {
            ((Damageable) target).damage(Math.round(Math.random() * 10));
        }
    }
}

