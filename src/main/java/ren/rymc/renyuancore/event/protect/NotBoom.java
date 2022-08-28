package ren.rymc.renyuancore.event.protect;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import ren.rymc.renyuancore.main.RenYuanCore;

public class NotBoom implements Listener {

    private static final RenYuanCore plugin = RenYuanCore.getAPI().getInstance();
    private final FileConfiguration config = plugin.getConfig();

    static {
        plugin.getLogger().info("防爆(魔改自Coby_Cola的NotBoom插件) 已加载");
    }

    @EventHandler
    private void NotBoomMain(EntityExplodeEvent event) {

        EntityType type = event.getEntity().getType();

        if (type == EntityType.CREEPER) {
            event.setCancelled(config.getBoolean("protect.not-boom.creeper", true));
        } else if (type == EntityType.ENDER_CRYSTAL) {
            event.setCancelled(config.getBoolean("protect.not-boom.ender-crystal", true));
        } else if (type == EntityType.PRIMED_TNT) {
            event.setCancelled(config.getBoolean("protect.not-boom.tnt", true));
        } else if (type == EntityType.WITHER_SKULL) {
            event.setCancelled(config.getBoolean("protect.not-boom.wither-skull", true));
        } else if (type == EntityType.WITHER) {
            event.setCancelled(config.getBoolean("protect.not-boom.wither", true));
        } else if (type == EntityType.SMALL_FIREBALL) {
            event.setCancelled(config.getBoolean("protect.not-boom.fire-ball", true));
        } else if (type == EntityType.MINECART_TNT) {
            event.setCancelled(config.getBoolean("protect.not-boom.tnt-minecart", true));
        } else {
            event.setCancelled(config.getBoolean("protect.not-boom.other", false));
        }
    }

    @EventHandler
    private void WitherDestroy(EntityChangeBlockEvent event) {
        if (event.getEntity().getType() == EntityType.WITHER) {
            event.setCancelled(config.getBoolean("protect.not-boom.wither-destroy",true));
        }
    }

    @EventHandler
    public void BlockExplode(BlockExplodeEvent event){
        event.setCancelled(config.getBoolean("protect.not-boom.block-explode",true));
    }
}
