package dev.luminescent.deezitems.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventPlayerInteract implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    private void EventPlayerLeftClickEntity(PlayerInteractEvent event) {
        
    }

}
