package ren.rymc.renyuancore.spawn;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import ren.rymc.renyuancore.RenYuanCoreAPI;

public class NoSpawnDamage implements Listener {

    @EventHandler( priority = EventPriority.HIGHEST )
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getEntity().getWorld().getName().contains(RenYuanCoreAPI.getPlugin().getConfig().getString("Spawn.World", "spawn")) && event.getDamage() < 3E38) {
            event.setCancelled(true);
        }
    }
}
