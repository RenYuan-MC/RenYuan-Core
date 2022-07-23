package ren.rymc.renyuancore.tpa;

import net.ess3.api.events.TPARequestEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class EssentialsSupport implements Listener {

    @EventHandler
    public void onPlayerTpa(TPARequestEvent event){
        Player player = Bukkit.getPlayer(event.getTarget().getUUID());
        TeleportMode teleportMode;
        if (event.isTeleportHere()){
            teleportMode = TeleportMode.TpaHere;
        }else{
            teleportMode = TeleportMode.Tpa;
        }
        if (player != null) TpaMenuSend.sendTpaMenu(player, event.getRequester().getPlayer(), teleportMode);
    }
}
