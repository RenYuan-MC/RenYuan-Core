package ren.rymc.renyuancore.event.bedrock.tpa;

import com.Zrips.CMI.Modules.tp.TpManager;
import com.Zrips.CMI.events.CMIPlayerTeleportRequestEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ren.rymc.renyuancore.feature.bedrock.tpa.TeleportMode;
import ren.rymc.renyuancore.feature.bedrock.tpa.Tpa;
import ren.rymc.renyuancore.main.RenYuanCore;

public class CMIListener implements Listener {

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("基岩版Tpa菜单CMI支持 已加载");
        Tpa.enableMenuListener();
    }

    @EventHandler
    public void onPlayerTpa(CMIPlayerTeleportRequestEvent event) {
        TeleportMode teleportMode = event.getAction().equals(TpManager.TpAction.tpahere) ? TeleportMode.TpaHere : (event.getAction().equals(TpManager.TpAction.tpa) ? TeleportMode.Tpa : TeleportMode.Unknown);
        Tpa.sendTpaMenu(event.getWhoAccepts(), event.getWhoOffers(), teleportMode);

    }
}
