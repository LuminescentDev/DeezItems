package dev.luminescent.deezitems.defaultitems.items;

import dev.luminescent.deezitems.DeezItems;
import dev.luminescent.deezitems.manager.MaterialManager;
import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.ItemAbility;
import dev.luminescent.deezitems.utils.ItemUtils;
import dev.rosewood.rosegarden.utils.HexUtils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.Vector;

import java.util.List;

public class FemboyStick extends DeezItem {

    public static final ItemAbility SMOOCH = new ItemAbility("smooch", "SMOOOOOCH", "give someone a fat smooch", ItemAbility.AbilityTypes.LEFT_CLICK, 3);

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
        return shootHeart(player, item);
    }

    @Override
    public void registerRecipe() {
        NamespacedKey key = new NamespacedKey(DeezItems.getInstance(), getID());

        ShapedRecipe recipe = new ShapedRecipe(key, generate(1));

        recipe.shape(" N ", " N ", " N ");

        recipe.setIngredient('N', new RecipeChoice.ExactChoice(DeezItems.getInstance().getManager(MaterialManager.class).getMaterial("example_material").generate(1)));

        Bukkit.addRecipe(recipe);
    }

    public boolean shootHeart(Player player, ItemStack item) {
        Entity target = ItemUtils.getTargetInRange(player, 50);
        Player targetPlayer = null;
        if (target instanceof Player) {
            targetPlayer = (Player) target;
        }
        if (targetPlayer == null) return false;
        if (SMOOCH.enforceCooldown(player, item, true)) return false;
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
