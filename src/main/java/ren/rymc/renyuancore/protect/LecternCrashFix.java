package ren.rymc.renyuancore.protect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class LecternCrashFix implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryClickEvent(InventoryClickEvent event){
        if(event.getInventory().getType().equals(InventoryType.LECTERN)) event.setCancelled(true);
    }
}
