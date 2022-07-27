package ren.rymc.renyuancore.tpa;

import com.Zrips.CMI.Modules.tp.TpManager;
import com.Zrips.CMI.events.CMIPlayerTeleportRequestEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class CMISupport implements Listener {

    @EventHandler
    public void onPlayerTpa(CMIPlayerTeleportRequestEvent event) {
        TeleportMode teleportMode = event.getAction().equals(TpManager.TpAction.tpahere) ? TeleportMode.TpaHere : (event.getAction().equals(TpManager.TpAction.tpa) ? TeleportMode.Tpa : TeleportMode.Unknown);
        TpaMenuSend.sendTpaMenu(event.getWhoAccepts(), event.getWhoOffers(), teleportMode);
    }
}
