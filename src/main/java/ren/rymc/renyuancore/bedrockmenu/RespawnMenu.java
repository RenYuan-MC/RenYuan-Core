package ren.rymc.renyuancore.bedrockmenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import ren.rymc.renyuancore.RenYuanCoreAPI;
import ren.rymc.renyuancore.folia.FoliaUtil;

import java.util.UUID;

public class RespawnMenu implements Listener {

    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event){
        FoliaUtil.runTaskLater(RenYuanCoreAPI.getPlugin(),() -> SendRespawnMenu(event.getPlayer()),20L);
    }

    private void SendRespawnMenu(Player player) {

        UUID uuid = player.getUniqueId();
        if (!FloodgateApi.getInstance().isFloodgatePlayer(uuid)) return;
        SimpleForm.Builder builder = SimpleForm.builder();
        builder.title("菜单");
        builder.content("你死亡了，下一步你想要");
        builder.button("回到死亡点", FormImage.Type.PATH, "textures/ui/arrow_dark_right_stretch.png");
        builder.button("回家", FormImage.Type.PATH, "textures/ui/store_home_icon.png");
        builder.button("留在主城", FormImage.Type.PATH, "textures/ui/realms_red_x.png");
        builder.responseHandler((f, r) -> {

            SimpleFormResponse response = f.parseResponse(r);
            if (!response.isCorrect()) return;

            int id = response.getClickedButtonId();
            if (id == 0) Bukkit.dispatchCommand(player, "back");
            if (id == 1) Bukkit.dispatchCommand(player, "home");

        });

        FloodgateApi.getInstance().getPlayer(uuid).sendForm(builder);

    }

}
