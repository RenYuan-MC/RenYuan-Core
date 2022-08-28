package ren.rymc.renyuancore.feature.bedrock.respawn;

import me.zimzaza4.LForm;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import ren.rymc.renyuancore.api.RenYuanAPI;
import ren.rymc.renyuancore.event.bedrock.respawn.MenuListener;
import ren.rymc.renyuancore.event.bedrock.respawn.RespawnListener;
import ren.rymc.renyuancore.main.RenYuanCore;

import java.util.UUID;

public class Respawn {

    private static final RenYuanAPI api = RenYuanCore.getAPI();
    private static final FileConfiguration config = api.getInstance().getConfig();
    private static final PluginManager pm = Bukkit.getPluginManager();

    static {
        // 如果基岩版菜单未启用或者未安装floodgate和QuickShop则不执行
        if (api.hasPlugin("floodgate") && config.getBoolean("bedrock.respawn.enable",true)){
            // 注册事件
            pm.registerEvents(new RespawnListener(),api.getInstance());
            pm.registerEvents(new MenuListener(),api.getInstance());
        }
    }

    public static void sendRespawnMenu(Player player){

        UUID uuid = player.getUniqueId();
        if (!FloodgateApi.getInstance().isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(uuid);

        SimpleForm.Builder menu = SimpleForm.builder()
                .title(config.getString("bedrock.respawn.form.title", "菜单"))
                .content(config.getString("bedrock.respawn.form.content", "你死亡了，下一步你想要"))
                .button(config.getString("bedrock.respawn.form.back", "回到死亡点"), FormImage.Type.PATH, config.getString("bedrock.respawn.form.back-icon", "textures/ui/arrow_dark_right_stretch.png"))
                .button(config.getString("bedrock.respawn.form.home", "回家"), FormImage.Type.PATH, config.getString("bedrock.respawn.form.home-icon", "textures/ui/store_home_icon.png"))
                .button(config.getString("bedrock.respawn.form.spawn", "留在主城"), FormImage.Type.PATH, config.getString("bedrock.respawn.form.spawn-icon", "textures/ui/realms_red_x.png"));
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_Respawn-menu");
        floodgatePlayer.sendForm(menu);
    }
}
