package ren.rymc.renyuancore.event.spawn;

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
import ren.rymc.renyuancore.main.RenYuanCore;

import java.util.Objects;

public class SpawnProtect implements Listener {

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("主城破坏保护 已加载");
    }

    private final String worldName = RenYuanCore.getAPI().getSpawnAPI().getSpawnWorldName();

    @EventHandler( priority = EventPriority.HIGHEST )
    public void HangingBreakByEntityEvent(HangingBreakByEntityEvent event) {
        if (event.getRemover() instanceof Player && event.getRemover().getWorld().getName().contains(worldName) && !event.getRemover().hasPermission("spawn.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void BreakBlock(BlockBreakEvent event) {
        if (event.getPlayer().getWorld().getName().contains(worldName) && !event.getPlayer().hasPermission("spawn.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.LOWEST )
    public void HangingPlaceEvent(HangingPlaceEvent event) {
        if (Objects.requireNonNull(event.getPlayer()).getWorld().getName().contains(worldName) && !event.getPlayer().hasPermission("spawn.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void BlockPlaceEvent(BlockPlaceEvent event) {
        if (event.getPlayer().getWorld().getName().contains(worldName) && !event.getPlayer().hasPermission("spawn.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerBucketFillEvent(PlayerBucketFillEvent event) {
        if (event.getPlayer().getWorld().getName().contains(worldName) && !event.getPlayer().hasPermission("spawn.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) {
        if (event.getPlayer().getWorld().getName().contains(worldName) && !event.getPlayer().hasPermission("spawn.build")) {
            event.setCancelled(true);
        }
    }
}
