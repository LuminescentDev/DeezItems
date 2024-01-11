package dev.luminescent.deezitems.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.ItemUtils;

public class EventPlayerUseDeezItem implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (ItemUtils.isDeezItem(mainHandItem)) {
            useItem(event, mainHandItem);
        }
    }

    private void useItem(PlayerInteractEvent event, ItemStack item) {
        Player player = event.getPlayer();
        DeezItem deezItem = ItemUtils.getDeezItem(item);

        if (deezItem == null || ItemUtils.hasPermission(player, deezItem)) {
            return;
        }

        Action action = event.getAction();

        switch (action) {
            case LEFT_CLICK_AIR -> {
                if (!player.isSneaking()) {
                    if (deezItem.leftClickAirAction(player, item)) {
                        deezItem.onItemUse(player, item);
                    }
                } else if (deezItem.shiftLeftClickAirAction(player, item)) {
                    deezItem.onItemUse(player, item);
                }
            }
            case LEFT_CLICK_BLOCK -> {
                if (!player.isSneaking()) {
                    if (deezItem.leftClickBlockAction(player, event, event.getClickedBlock(), item)) {
                        deezItem.onItemUse(player, item);
                    }
                } else if (deezItem.shiftLeftClickBlockAction(player, event, event.getClickedBlock(), item)) {
                    deezItem.onItemUse(player, item);
                }
            }
            case RIGHT_CLICK_AIR -> {
                if (!player.isSneaking()) {
                    if (deezItem.rightClickAirAction(player, item)) {
                        deezItem.onItemUse(player, item);
                    }
                } else if (deezItem.shiftRightClickAirAction(player, item)) {
                    deezItem.onItemUse(player, item);
                }
            }
            case RIGHT_CLICK_BLOCK -> {
                if (!player.isSneaking()) {
                    if (deezItem.rightClickBlockAction(player, event, event.getClickedBlock(), item)) {
                        deezItem.onItemUse(player, item);
                    }
                } else if (deezItem.shiftRightClickBlockAction(player, event, event.getClickedBlock(), item)) {
                    deezItem.onItemUse(player, item);
                }
            }
        }
    }
}
