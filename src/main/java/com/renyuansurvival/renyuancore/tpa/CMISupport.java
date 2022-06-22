package com.renyuansurvival.renyuancore.tpa;

import com.Zrips.CMI.events.CMIPlayerTeleportRequestEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class CMISupport implements Listener {

    @EventHandler
    public void onPlayerTpa(CMIPlayerTeleportRequestEvent event) {
        TeleportMode teleportMode = TeleportMode.Unknown;
        switch (event.getAction()){
            case tpa:
                teleportMode = TeleportMode.Tpa;
                break;
            case tpahere:
                teleportMode = TeleportMode.TpaHere;
                break;
        }
        TpaMenuSend.sendTpaMenu(event.getWhoAccepts(), event.getWhoOffers(), teleportMode);
    }
}
