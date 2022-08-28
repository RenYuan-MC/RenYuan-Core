package ren.rymc.renyuancore.event.protect;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import ren.rymc.renyuancore.main.RenYuanCore;

import java.util.List;

public class ReservedUsers implements Listener {

    private static final FileConfiguration config = RenYuanCore.getAPI().getInstance().getConfig();
    private static final List<String> playerList;

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("保留用户限制 已加载");
        playerList = config.getStringList("protect.reserved-users.list");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        for (String playerName : playerList){
            if (player.getName().equals(playerName)) player.kickPlayer("保留账号,禁止使用,请更换你的账号ID");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void AsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {
        for (String playerName : playerList){
            if (event.getName().equals(playerName)) event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER,"保留账号,禁止使用,请更换你的账号ID");
        }
    }



}
