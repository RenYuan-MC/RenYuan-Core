package ren.rymc.renyuancore.event.protect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import ren.rymc.renyuancore.main.RenYuanCore;

public class AntiUserName implements Listener {
    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("基岩版玩家名称检测 已加载");
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        String prefix = FloodgateApi.getInstance().getPlayerPrefix();
        if (event.getPlayer().getName().startsWith(prefix) && !FloodgateApi.getInstance().isFloodgatePlayer(event.getPlayer().getUniqueId())) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "[AntiUserName]\n非法的基岩版玩家名称\n请非基岩版玩家名称不要以" + prefix + "开头");
        }
    }
}
