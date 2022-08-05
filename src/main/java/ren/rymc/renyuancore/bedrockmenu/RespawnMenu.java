package ren.rymc.renyuancore.bedrockmenu;

import me.zimzaza4.LForm;
import me.zimzaza4.SimpleFormClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import ren.rymc.renyuancore.RenYuanAPI;

import java.util.UUID;

public class RespawnMenu implements Listener {

    private final FloodgateApi api = FloodgateApi.getInstance();

    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event){
        new BukkitRunnable(){
            @Override
            public void run(){
                SendRespawnMenu(event.getPlayer());
            }
        }.runTaskLater(RenYuanAPI.getPlugin(),20L);
    }

    private void SendRespawnMenu(Player player){

        UUID uuid = player.getUniqueId();
        if (!api.isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = api.getPlayer(uuid);

        SimpleForm.Builder menu = SimpleForm.builder()
                .title("菜单")
                .content("你死亡了，下一步你想要")
                .button("回到死亡点", FormImage.Type.PATH, "textures/ui/arrow_dark_right_stretch.png")
                .button("回家", FormImage.Type.PATH, "textures/ui/store_home_icon.png")
                .button("留在主城", FormImage.Type.PATH, "textures/ui/realms_red_x.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_Respawn-menu");
        floodgatePlayer.sendForm(menu);
    }

    @EventHandler
    public void SimpleFormClickEvent(SimpleFormClickEvent event) {
        Player player = event.getPlayer();
        String formId = event.getFormId();
        if ("RenYuan-BEMenu_Respawn-menu".equals(formId)) {
            int buttonID = event.getButtonID();
            if (buttonID == 0) {
                Bukkit.dispatchCommand(player,"back");
            } else if (buttonID == 1) {
                Bukkit.dispatchCommand(player,"home");
            }
        }
    }
}
