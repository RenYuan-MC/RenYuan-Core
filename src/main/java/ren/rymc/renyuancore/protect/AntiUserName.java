package ren.rymc.renyuancore.protect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.geysermc.floodgate.api.FloodgateApi;


public class AntiUserName implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        String Prefix = FloodgateApi.getInstance().getPlayerPrefix();
        if (event.getPlayer().getName().startsWith(Prefix) && !FloodgateApi.getInstance().isFloodgatePlayer(event.getPlayer().getUniqueId())) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "[AntiUserName]\n非法的基岩版玩家名称\n请非基岩版玩家名称不要以" + Prefix + "开头");
        }
    }
}