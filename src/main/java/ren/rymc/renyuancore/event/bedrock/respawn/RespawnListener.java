package ren.rymc.renyuancore.event.bedrock.respawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ren.rymc.renyuancore.feature.bedrock.respawn.Respawn;
import ren.rymc.renyuancore.main.RenYuanCore;

public class RespawnListener implements Listener {

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("基岩版重生菜单 已加载");
    }

    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event){
        new BukkitRunnable(){
            @Override
            public void run(){
                Respawn.sendRespawnMenu(event.getPlayer());
            }
        }.runTaskLater(RenYuanCore.getAPI().getInstance(),20L);
    }
}
