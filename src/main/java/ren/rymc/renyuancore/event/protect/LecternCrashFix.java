package ren.rymc.renyuancore.event.protect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import ren.rymc.renyuancore.main.RenYuanCore;

public class LecternCrashFix implements Listener {

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("防讲台崩服 已加载");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void InventoryClickEvent(InventoryClickEvent event){
        if(event.getInventory().getType().equals(InventoryType.LECTERN)) event.setCancelled(true);
    }
}
