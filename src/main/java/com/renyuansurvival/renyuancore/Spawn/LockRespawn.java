package com.renyuansurvival.renyuancore.Spawn;

import com.renyuansurvival.renyuancore.RenYuanCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class LockRespawn implements Listener {

    private final FileConfiguration config = RenYuanCore.getPlugin().getConfig();

    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        event.setRespawnLocation(new Location(Bukkit.getWorld(config.getString("Spawn.World", "spawn")),config.getDouble("Spawn.X",59.5),config.getDouble("Spawn.Y",105.0),config.getDouble("Spawn.Z",-149.5),0.0F,0.0F));
    }
}
