package com.renyuansurvival.renyuancore;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.util.UiProfile;

import java.util.UUID;

public class GeyserPocketUICheck implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerLoginEvent(PlayerLoginEvent event) {

        FileConfiguration config = RenYuanCore.getPlugin().getConfig();
        FloodgateApi floodgate = FloodgateApi.getInstance();
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (floodgate.isFloodgatePlayer(uuid) && floodgate.getPlayer(uuid).getUiProfile().equals(UiProfile.POCKET)) {
            if (config.getBoolean("GeyserPocketUICheck.Kick.Enable",false)) {
                Bukkit.getLogger().info("Debug: Kicking geyser pocketUI player");
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Component.text(config.getString("GeyserPocketUICheck.Kick.Message","请将设置选项视频中的UI档案设置为经典")));
            }else if (config.getBoolean("GeyserPocketUICheck.SendMessage.Enable",true)){
                Bukkit.getLogger().info("Debug: Sending waring message to geyser pocketUI player");
                player.sendMessage(RenYuanCore.getPrefix() + config.getString("GeyserPocketUICheck.SendMessage.Message","服务器不能完美兼容PocketUI,请将设置选项视频中的UI档案设置为经典以提升你的游戏体验"));
            }

        }
        if(config.getBoolean("GeyserFeature.SendClientName",false)){
            if (floodgate.isFloodgatePlayer(uuid)) {
                Bukkit.getLogger().info("基岩版玩家 " + player.getName() + " 的UI模式为: " + floodgate.getPlayer(uuid).getUiProfile().toString());
                Bukkit.getLogger().info("基岩版玩家 " + player.getName() + " 的客户端为: " + floodgate.getPlayer(uuid).getDeviceOs().toString());
            }
        }
    }
}