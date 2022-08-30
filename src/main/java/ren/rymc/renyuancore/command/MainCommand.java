package ren.rymc.renyuancore.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.main.RenYuanCore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MainCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) return sendHelp(sender,"/" + command.getName());
        String arg0 = args[0].toLowerCase(Locale.ROOT);
        if (arg0.equals("help")) return sendHelp(sender,"/" + command.getName());
        if (arg0.equals("reload") && args.length > 1) return reloadPlugin(sender,args[1]);
        if (arg0.equals("ui") && args.length > 1) return sendUI(sender,args[1]);
        RenYuanCore.getAPI().sendMessage(sender, "请使用/RenYuanCore help获取帮助");
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
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("reload")) {
            List<String> list = new ArrayList<>();
            list.add("config");
            list.add("all");
            return list;
        }

        return null;
    }

    private boolean sendHelp(CommandSender sender,String command){
        RenYuanCore.getAPI().sendMessage(sender,
                "任渊生存服务端 RenYuan-Core 6.0.0 Dev 帮助",
                command + " help - 获取帮助",
                command + " reload config - 重载配置文件",
                command + " reload all - 重载插件",
                command + " ui <玩家名> - 获取基岩版玩家UI模式"
        );
        return true;
    }

    private boolean reloadPlugin(CommandSender sender, String arg1){
        if (!sender.hasPermission("renyuancore.reload")){
            RenYuanCore.getAPI().sendMessage(sender,"你没有权限");
            return true;
        }
        arg1 = arg1.toLowerCase(Locale.ROOT);

        if(arg1.equals("config")){
            RenYuanCore.getAPI().reloadPluginConfig();
            RenYuanCore.getAPI().sendMessage(sender, "配置文件已经重载(仅部分功能重载)");
            return true;
        }

        if(arg1.equals("all")) {
            RenYuanCore.getAPI().reloadPlugin(false);
            RenYuanCore.getAPI().sendMessage(sender,
                    "你正在重载整个插件,此操作不受支持,可能会出现意想不到的问题",
                    "当然,你依然可以把重载插件后遇到的问题报告到此插件的GitHub",
                    "但是,这些问题并不会是最优先级处理的,甚至可能不处理"
            );
        }
        return true;
    }

    private boolean sendUI(CommandSender sender,String arg1){
        if (!RenYuanCore.getAPI().hasPlugin("floodgate")) return true;
        Player player = Bukkit.getPlayer(arg1);
        if (player == null || !player.isOnline()) return true;
        UUID uuid = player.getUniqueId();
        FloodgateApi floodgate = FloodgateApi.getInstance();
        if(floodgate.isFloodgatePlayer(uuid)){
            RenYuanCore.getAPI().sendMessage(sender, "基岩版玩家" + arg1 + "的UI模式为:" + floodgate.getPlayer(uuid).getUiProfile().toString());
        }
        return true;
    }
}
