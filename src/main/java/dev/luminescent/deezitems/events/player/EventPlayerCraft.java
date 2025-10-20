package dev.luminescent.deezitems.events.player;

import dev.luminescent.deezitems.manager.Settings;
import dev.luminescent.deezitems.utils.ItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class EventPlayerCraft implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCraft(PrepareItemCraftEvent event) {
        ItemStack[] items = event.getInventory().getMatrix();

        if (Settings.CAN_CRAFT_WITH_ITEMS.get()) return;

        for (ItemStack itemStack : items) {
            if (itemStack != null && ItemUtils.isDeezItem(itemStack)) {
                event.getInventory().setResult(null);
                return;
            }
        }
    }



}
