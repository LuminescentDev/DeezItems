package xyz.akiradev.deezitems.events.block;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import xyz.akiradev.deezitems.utils.DeezItem;
import xyz.akiradev.deezitems.utils.ItemUtils;

public class EventBlockBreak implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        if (ItemUtils.isDeezItem(event.getPlayer().getInventory().getItemInMainHand())) {
            useItem(event, event.getPlayer().getInventory().getItemInMainHand());
        }
    }

    private void useItem(BlockBreakEvent event, ItemStack item) {
        Player player = event.getPlayer();
        DeezItem deezItem = ItemUtils.getDeezItem(item);
        if (deezItem != null && deezItem.breakBlockAction(player, event, event.getBlock(), item)) {
            deezItem.onItemUse(player, item);
        }
    }

}
