package ren.rymc.renyuancore.protect;

import ren.rymc.renyuancore.RenYuanCoreAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NotBoom implements Listener {

    private final FileConfiguration config = RenYuanCoreAPI.getPlugin().getConfig();

    @EventHandler
    private void NotBoomMain(EntityExplodeEvent event) {

        EntityType type = event.getEntity().getType();

        if (type == EntityType.CREEPER) {
            event.setCancelled(config.getBoolean("NotBoom.Creeper", true));
        } else if (type == EntityType.ENDER_CRYSTAL) {
            event.setCancelled(config.getBoolean("NotBoom.EnderCrystal", true));
        } else if (type == EntityType.PRIMED_TNT) {
            event.setCancelled(config.getBoolean("NotBoom.TNT", true));
        } else if (type == EntityType.WITHER_SKULL) {
            event.setCancelled(config.getBoolean("NotBoom.WitherSkull", true));
        } else if (type == EntityType.WITHER) {
            event.setCancelled(config.getBoolean("NotBoom.Wither", true));
        } else if (type == EntityType.SMALL_FIREBALL) {
            event.setCancelled(config.getBoolean("NotBoom.FireBall", true));
        } else if (type == EntityType.MINECART_TNT) {
            event.setCancelled(config.getBoolean("NotBoom.TNTMinecart", true));
        } else {
            event.setCancelled(config.getBoolean("NotBoom.Other", false));
        }
    }

    @EventHandler
    private void WitherDestroy(EntityChangeBlockEvent event) {
        if (event.getEntity().getType() == EntityType.WITHER) {
            event.setCancelled(config.getBoolean("NotBoom.WitherDestroy",true));
        }
    }

    @EventHandler
    public void BlockExplode(BlockExplodeEvent event){
        event.setCancelled(config.getBoolean("NotBoom.BlockExplode",true));
    }
}
