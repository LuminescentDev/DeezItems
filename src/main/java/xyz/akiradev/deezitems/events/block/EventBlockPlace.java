package xyz.akiradev.deezitems.events.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.akiradev.deezitems.utils.ItemUtils;

public class EventBlockPlace implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    private void onBlockPlace(BlockPlaceEvent event) {
        if(ItemUtils.isDeez(event.getItemInHand())){
            event.setCancelled(true);
        }
    }
}
