package ren.rymc.renyuancore.feature.bedrock.tpa;

import me.zimzaza4.LForm;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.api.RenYuanAPI;
import ren.rymc.renyuancore.api.event.SendTpaMenuEvent;
import ren.rymc.renyuancore.event.bedrock.tpa.CMIListener;
import ren.rymc.renyuancore.event.bedrock.tpa.EssentialsXListener;
import ren.rymc.renyuancore.event.bedrock.tpa.MenuListener;
import ren.rymc.renyuancore.main.RenYuanCore;


public class Tpa {

    private static final RenYuanAPI api = RenYuanCore.getAPI();
    private static final FileConfiguration config = api.getInstance().getConfig();
    private static final PluginManager pm = Bukkit.getPluginManager();
    private static boolean menuListener = false;

    static {
        // 如果基岩版菜单未启用或者未安装floodgate则不执行
        if (pm.getPlugin("floodgate") != null && config.getBoolean("bedrock.tpa.enable",true)) {

            // 检测ESS插件
            if (pm.getPlugin("Essentials") != null) pm.registerEvents(new EssentialsXListener(), api.getInstance());

            // 检测CMI插件
            if (pm.getPlugin("CMI") != null) pm.registerEvents(new CMIListener(), api.getInstance());

            if (menuListener) pm.registerEvents(new MenuListener(), api.getInstance());
        }
    }

    /**
     * 决定是否监听基岩版Tpa菜单
     *
     * 如果没有任何可用支持Tpa的插件
     * 那么enableMenuListener将为false
     *
     */
    public static void enableMenuListener(){
        menuListener = true;
    }

    /**
     * 发送tpa菜单
     *
     * @param player 收到Tpa请求的玩家
     * @param fromPlayer 发送Tpa请求的玩家
     * @param teleportMode Tp的类型可为 Tpa, TpaHere, Unknown
     *
     */
    public static void sendTpaMenu(@NotNull Player player, @NotNull Player fromPlayer, @NotNull TeleportMode teleportMode) {

        // 排除非基岩版玩家
        if (!FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) return;

        // 触发事件
        SendTpaMenuEvent event = new SendTpaMenuEvent(player, fromPlayer, teleportMode);
        // 如果事件被取消则停止执行
        if (event.isCancelled()) return;

        // 获取传送方式和传送信息
        String teleportMessage = String.format(config.getString("bedrock.tpa.contents.unknown", "未知的传送方式,来自 %s"), fromPlayer.getName());
        if (teleportMode == TeleportMode.Tpa) {
            teleportMessage = String.format(config.getString("bedrock.tpa.contents.tpa", "%s 想要让他传送到你这里"), fromPlayer.getName());
        } else if (teleportMode == TeleportMode.TpaHere) {
            teleportMessage = String.format(config.getString("bedrock.tpa.contents.tpahere", "%s 想要让你传送到他那里"), fromPlayer.getName());
        }

        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());

        // 创建菜单
        SimpleForm.Builder tpaMenu = SimpleForm.builder();
        tpaMenu.title(config.getString("bedrock.tpa.form.title", "传送菜单"));
        tpaMenu.content(teleportMessage);
        tpaMenu.button(config.getString("bedrock.tpa.form.accept", "同意传送"), FormImage.Type.PATH, config.getString("bedrock.tpa.form.accept-icon", "textures/ui/realms_green_check.png"));
        tpaMenu.button(config.getString("bedrock.tpa.form.deny", "拒绝传送"), FormImage.Type.PATH, config.getString("bedrock.tpa.form.deny-icon", "textures/ui/realms_red_x.png"));

        // 添加至监听器饼发送
        LForm.SimpleListener(player, tpaMenu, "RenYuan-Core_Tpa-Menu");
        floodgatePlayer.sendForm(tpaMenu);
    }



}
