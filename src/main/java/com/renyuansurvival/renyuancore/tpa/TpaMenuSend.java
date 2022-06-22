package com.renyuansurvival.renyuancore.tpa;

import me.zimzaza4.LForm;
import me.zimzaza4.SimpleFormClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.jetbrains.annotations.NotNull;

public class TpaMenuSend implements Listener {

    public static void sendTpaMenu(@NotNull Player player,@NotNull Player fromPlayer,@NotNull TeleportMode teleportMode) {
        if(FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())){
            String teleportMessage = "未知的传送方式,来自" + fromPlayer.getName();
            switch (teleportMode){
                case Tpa:
                    teleportMessage = fromPlayer.getName() + " 想要传送到你这里";
                    break;
                case TpaHere:
                    teleportMessage = fromPlayer.getName() + " 想要传送到他那里";
                    break;
            }
            FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
            SimpleForm.Builder tpaMenu = SimpleForm.builder()
                    .title("传送菜单")
                    .content(teleportMessage)
                    .button("同意传送", FormImage.Type.PATH, "textures/ui/realms_red_x.png")
                    .button("拒绝传送", FormImage.Type.PATH, "textures/ui/realms_green_check.png");
            LForm.SimpleListener(player, tpaMenu, "RenYuan-Core_Tpa-Menu");
            floodgatePlayer.sendForm(tpaMenu);
        }
    }

    @EventHandler
    public void onTpaMenuClick(SimpleFormClickEvent event){
        Player player = event.getPlayer();
        if (event.getFormId().equals("RenYuan-Core_Tpa-Menu")){
            switch (event.getButtonID()){
                case 0:
                    Bukkit.dispatchCommand(player,"/tpaccept");
                    break;
                case 1:
                    Bukkit.dispatchCommand(player,"/tpdeny");
                    break;
            }
        }
    }
}
