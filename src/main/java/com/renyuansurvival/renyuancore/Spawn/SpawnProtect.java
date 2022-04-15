package com.renyuansurvival.renyuancore.Spawn;

import com.renyuansurvival.renyuancore.RenYuanCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

import java.util.Objects;

public class SpawnProtect implements Listener {

    private final FileConfiguration config = RenYuanCore.getPlugin().getConfig();

    @EventHandler( priority = EventPriority.HIGHEST )
    public void HangingBreakByEntityEvent(HangingBreakByEntityEvent event) {
        if (event.getRemover() instanceof Player && event.getRemover().getWorld().getName().contains(config.getString("Spawn.World", "spawn")) && !event.getRemover().hasPermission("Spawn.Build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void BreakBlock(BlockBreakEvent event) {
        if (event.getPlayer().getWorld().getName().contains(config.getString("Spawn.World", "spawn")) && !event.getPlayer().hasPermission("Spawn.Build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.LOWEST )
    public void HangingPlaceEvent(HangingPlaceEvent event) {
        if (Objects.requireNonNull(event.getPlayer()).getWorld().getName().contains(config.getString("Spawn.World", "spawn")) && !event.getPlayer().hasPermission("Spawn.Build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void BlockPlaceEvent(BlockPlaceEvent event) {
        if (event.getPlayer().getWorld().getName().contains(config.getString("Spawn.World", "spawn")) && !event.getPlayer().hasPermission("Spawn.Build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerBucketFillEvent(PlayerBucketFillEvent event) {
        if (event.getPlayer().getWorld().getName().contains(config.getString("Spawn.World", "spawn")) && !event.getPlayer().hasPermission("Spawn.Build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) {
        if (event.getPlayer().getWorld().getName().contains(config.getString("Spawn.World", "spawn")) && !event.getPlayer().hasPermission("Spawn.Build")) {
            event.setCancelled(true);
        }
    }
}
