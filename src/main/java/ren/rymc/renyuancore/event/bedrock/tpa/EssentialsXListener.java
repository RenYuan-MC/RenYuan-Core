package ren.rymc.renyuancore.event.bedrock.tpa;

import net.ess3.api.events.TPARequestEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ren.rymc.renyuancore.feature.bedrock.tpa.TeleportMode;
import ren.rymc.renyuancore.feature.bedrock.tpa.Tpa;
import ren.rymc.renyuancore.main.RenYuanCore;

public class EssentialsXListener implements Listener {

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("基岩版Tpa菜单ESS支持 已加载");
        Tpa.enableMenuListener();
    }

    @EventHandler
    public void onPlayerTpa(TPARequestEvent event){
        Player player = Bukkit.getPlayer(event.getTarget().getUUID());
        TeleportMode teleportMode = event.isTeleportHere() ? TeleportMode.TpaHere : TeleportMode.Tpa;
        if (player != null) Tpa.sendTpaMenu(player, event.getRequester().getPlayer(), teleportMode);
    }


}
