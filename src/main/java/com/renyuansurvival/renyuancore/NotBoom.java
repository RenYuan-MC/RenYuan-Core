package com.renyuansurvival.renyuancore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NotBoom implements Listener {

    private final FileConfiguration config = RenYuanCore.getPlugin().getConfig();

    @EventHandler
    private void NotBoomMain(EntityExplodeEvent e) {

        EntityType type = e.getEntity().getType();

        if (type == EntityType.CREEPER) {
            e.setCancelled(config.getBoolean("NotBoom.Creeper", true));
        } else if (type == EntityType.ENDER_CRYSTAL) {
            e.setCancelled(config.getBoolean("NotBoom.EnderCrystal", true));
        } else if (type == EntityType.PRIMED_TNT) {
            e.setCancelled(config.getBoolean("NotBoom.TNT", true));
        } else if (type == EntityType.WITHER_SKULL) {
            e.setCancelled(config.getBoolean("NotBoom.WitherSkull", true));
        } else if (type == EntityType.WITHER) {
            e.setCancelled(config.getBoolean("NotBoom.Wither", true));
        } else if (type == EntityType.SMALL_FIREBALL) {
            e.setCancelled(config.getBoolean("NotBoom.FireBall", true));
        } else if (type == EntityType.MINECART_TNT) {
            e.setCancelled(config.getBoolean("NotBoom.TNTMinecart", true));
        } else {
            e.setCancelled(config.getBoolean("NotBoom.Other", false));
        }
    }

    @EventHandler
    private void WitherDestroy(EntityChangeBlockEvent e) {
        if (e.getEntity().getType() == EntityType.WITHER) {
            e.setCancelled(config.getBoolean("NotBoom.WitherDestroy",true));
        }
    }

    @EventHandler
    public void BlockExplode(BlockExplodeEvent e){
        e.setCancelled(config.getBoolean("NotBoom.BlockExplode",true));
    }
}
