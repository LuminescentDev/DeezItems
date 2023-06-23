package dev.luminescent.deezitems.events.other;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.ItemUtils;

public class EventProjectileHit implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player player)) {
            return;
        }

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (ItemUtils.isDeezItem(mainHandItem)) {
            useItem(event, mainHandItem);
        }
    }

    private void useItem(ProjectileHitEvent event, ItemStack item) {
        Player player = (Player) event.getEntity().getShooter();
        DeezItem deezItem = ItemUtils.getDeezItem(item);
        if (deezItem != null && deezItem.projectileHitAction(player, event, item)) {
            deezItem.onItemUse(player, item);
        }
    }
}
