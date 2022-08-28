package ren.rymc.renyuancore.event.spawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import ren.rymc.renyuancore.main.RenYuanCore;

public class SpawnLocker implements Listener {

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("主城出生点锁定 已加载");
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        event.setRespawnLocation(RenYuanCore.getAPI().getSpawnAPI().getSpawnLocation());
    }
}
