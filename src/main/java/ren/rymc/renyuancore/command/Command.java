package ren.rymc.renyuancore.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.RenYuanCoreAPI;

import java.util.*;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")){
            RenYuanCoreAPI.sendMessage(sender,
                    "任渊生存服务端-帮助(RenYuan-Core 7)",
                    "/RenYuanCore help - 获取帮助",
                    "/RenYuanCore reload - 重载插件",
                    "/RenYuanCore ui <玩家名> - 获取基岩版玩家UI模式"
            );
        }else if (args[0].equalsIgnoreCase("reload")){
            if (!sender.hasPermission("renyuancore.admin")) return false;
            RenYuanCoreAPI.reloadPluginConfig();
            RenYuanCoreAPI.sendMessage(sender, "已重载!");
        }else if(args.length > 1 && args[0].equalsIgnoreCase("ui")) {
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) return false;
            UUID uuid = player.getUniqueId();
            FloodgateApi floodgate = FloodgateApi.getInstance();
            if(!floodgate.isFloodgatePlayer(uuid)) return false;
            RenYuanCoreAPI.sendMessage(sender, "基岩版玩家" + args[1] + "的UI模式为:" + floodgate.getPlayer(uuid).getUiProfile().toString());
        } else return false;

        return true;
    }
}
