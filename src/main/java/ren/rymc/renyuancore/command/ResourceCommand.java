package ren.rymc.renyuancore.command;

import com.viaversion.viaversion.api.Via;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.event.resource.ResourceSender;
import ren.rymc.renyuancore.main.RenYuanCore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResourceCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = ((Player) sender);
        UUID uuid = player.getUniqueId();
        if(FloodgateApi.getInstance().isFloodgatePlayer(uuid)) {
            RenYuanCore.getAPI().sendMessage(player,"基岩版玩家不能使用此功能");
            return true;
        }
        if(RenYuanCore.getAPI().hasPlugin("ViaVersion") && Via.getAPI().getPlayerVersion(uuid) < 498){
            RenYuanCore.getAPI().sendMessage(player,"你的游戏版本过低,不支持此功能");
            return true;
        }
        ResourceSender.sendResource(player);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            return list;
        }
        return null;
    }
}
