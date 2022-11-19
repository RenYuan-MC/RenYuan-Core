package ren.rymc.renyuancore.bedrockmenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import ren.rymc.renyuancore.RenYuanCoreAPI;

import java.util.UUID;

public class RespawnMenu implements Listener {

    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event){
        new BukkitRunnable(){
            @Override
            public void run(){
                SendRespawnMenu(event.getPlayer());
            }
        }.runTaskLater(RenYuanCoreAPI.getPlugin(),20L);
    }

    private void SendRespawnMenu(Player player) {

        UUID uuid = player.getUniqueId();
        if (!FloodgateApi.getInstance().isFloodgatePlayer(uuid)) return;

        FloodgateApi.getInstance().getPlayer(uuid).sendForm(
                SimpleForm.builder()
                        .title("菜单")
                        .content("你死亡了，下一步你想要")
                        .button("回到死亡点", FormImage.Type.PATH, "textures/ui/arrow_dark_right_stretch.png")
                        .button("回家", FormImage.Type.PATH, "textures/ui/store_home_icon.png")
                        .button("留在主城", FormImage.Type.PATH, "textures/ui/realms_red_x.png")
                        .responseHandler((f, r) -> {
                            SimpleFormResponse response = f.parseResponse(r);
                            if (response.isCorrect()) {
                                int id = response.getClickedButtonId();
                                if (id == 0) Bukkit.dispatchCommand(player, "back");
                                if (id == 1) Bukkit.dispatchCommand(player, "home");
                            }
                        })
        );
    }

}
