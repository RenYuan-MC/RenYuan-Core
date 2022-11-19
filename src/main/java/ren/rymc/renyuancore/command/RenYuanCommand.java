package ren.rymc.renyuancore.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.RenYuanCoreAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class RenYuanCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (args.length == 0 || args[0].equalsIgnoreCase("help")){
            RenYuanCoreAPI.sendMessage(sender,
                    "任渊生存服务端-帮助(RenYuan-Core 7)",
                    "/RenYuanCore help - 获取帮助",
                    "/RenYuanCore reload config - 重载配置文件",
                    "/RenYuanCore reload all - 重载插件",
                    "/RenYuanCore ui <玩家名> - 获取基岩版玩家UI模式"
            );
        }else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("renyuancore.reload")){
            if(args.length != 2) return true;
            if(args[1].equalsIgnoreCase("config")){
                RenYuanCoreAPI.reloadPluginConfig();
                RenYuanCoreAPI.sendMessage(sender, "配置文件已经重载(仅部分功能重载)");
            }else if(args[1].equalsIgnoreCase("all")) {
                RenYuanCoreAPI.reloadPlugin();
                RenYuanCoreAPI.sendMessage(sender,
                        "插件已经完成重载",
                        "这个功能仍未完成,仅能开启部分未开启的模块,也不受支持",
                        "如需要彻底重载请重启服务器"
                );
            }
        }else if(args[0].equalsIgnoreCase("ui") && Bukkit.getPlayerExact(args[1]) != null && Objects.requireNonNull(Bukkit.getPlayerExact(args[1])).isOnline() ) {
            Player player = Objects.requireNonNull(Bukkit.getPlayer(args[1]));
            UUID uuid = player.getUniqueId();
            FloodgateApi floodgate = FloodgateApi.getInstance();
            if(floodgate.isFloodgatePlayer(uuid)){
                RenYuanCoreAPI.sendMessage(sender, "基岩版玩家" + args[1] + "的UI模式为:" + floodgate.getPlayer(uuid).getUiProfile().toString());
            }
        }else{
            RenYuanCoreAPI.sendMessage(sender, "请使用/RenYuanCore help获取帮助");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("help");
            list.add("reload");
            list.add("ui");
            return list;
        }else if (args.length == 2 && args[0].equalsIgnoreCase("reload")) {
            List<String> list = new ArrayList<>();
            list.add("config");
            list.add("all");
            return list;
        }
        return null;
    }
}
