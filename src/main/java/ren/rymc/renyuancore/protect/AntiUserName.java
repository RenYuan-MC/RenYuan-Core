package ren.rymc.renyuancore.protect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.geysermc.floodgate.api.FloodgateApi;


public class AntiUserName implements Listener {

    @EventHandler
    public void onPlayerLogin(AsyncPlayerPreLoginEvent event) {
        String Prefix = FloodgateApi.getInstance().getPlayerPrefix();
        if (event.getName().startsWith(Prefix) && !FloodgateApi.getInstance().isFloodgatePlayer(event.getUniqueId())) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "[AntiUserName]\n非法的基岩版玩家名称\n请非基岩版玩家名称不要以" + Prefix + "开头");
        }
    }
}