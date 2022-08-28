package ren.rymc.renyuancore.event.spawn;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import ren.rymc.renyuancore.main.RenYuanCore;

public class NoSpawnDamage implements Listener {

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("主城禁止伤害 已加载");
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void EntityDamageEvent(EntityDamageEvent event) {
        RenYuanCore.getAPI().getInstance().getLogger().info(event.getCause().toString());
        if (event.getEntity() instanceof Player && event.getEntity().getWorld().getName().contains(RenYuanCore.getAPI().getInstance().getConfig().getString("Spawn.World", "spawn")) && event.getCause().equals(EntityDamageEvent.DamageCause.CONTACT)) {
            event.setCancelled(true);
        }
    }
}
