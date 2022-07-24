package xyz.akiradev.deezitems.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import xyz.akiradev.deezitems.manager.ConfigurationManager.Setting;
import xyz.akiradev.deezitems.utils.ItemUtils;

public class EventPlayerCraft implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCraft(PrepareItemCraftEvent event) {
        if(Setting.CAN_CRAFT_WITH_ITEMS.getBoolean()) return;
        ItemStack [] items = event.getInventory().getMatrix();
        for(ItemStack itemStack: items){
            if(itemStack != null && ItemUtils.isDeez(itemStack)){
                event.getInventory().setResult(null);
            }
        }
    }
}
