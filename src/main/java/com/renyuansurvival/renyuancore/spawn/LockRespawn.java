package com.renyuansurvival.renyuancore.spawn;

import com.renyuansurvival.renyuancore.RenYuanCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class LockRespawn implements Listener {
    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        event.setRespawnLocation(RenYuanCore.getSpawnLocation());
    }
}
