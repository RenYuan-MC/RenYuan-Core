package com.renyuansurvival.renyuancore.spawn;

import com.renyuansurvival.renyuancore.RenYuanCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoSpawnDamage implements Listener {

    @EventHandler( priority = EventPriority.HIGHEST )
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getEntity().getWorld().getName().contains(RenYuanCore.getPlugin().getConfig().getString("Spawn.World", "spawn")) && event.getDamage() < 3E38) {
            event.setCancelled(true);
        }
    }
}
