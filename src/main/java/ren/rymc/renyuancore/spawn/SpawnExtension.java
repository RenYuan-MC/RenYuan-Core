package ren.rymc.renyuancore.spawn;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import ren.rymc.renyuancore.RenYuanCoreAPI;
import ren.rymc.renyuancore.folia.FoliaUtil;

public class SpawnExtension implements Listener {

    @EventHandler( priority = EventPriority.HIGHEST )
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        Location spawn = RenYuanCoreAPI.getSpawnLocation(player);
        if (spawn == null) return;
        if (!player.getWorld().getName().equals(spawn.getWorld().getName())) return;
        if (event.getDamage() < 3E38) event.setCancelled(true);
        if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) FoliaUtil.teleport(player, spawn);
    }
}
