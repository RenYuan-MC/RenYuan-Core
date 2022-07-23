package ren.rymc.renyuancore.command;

import ren.rymc.renyuancore.RenYuanCore;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCommand implements CommandExecutor {

    private final FileConfiguration config = RenYuanCore.getPlugin().getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission("spawn.set")) {
                double X = ((Player) sender).getLocation().getBlockX() + 0.5;
                double Y = ((Player) sender).getLocation().getBlockY();
                double Z = ((Player) sender).getLocation().getBlockZ() + 0.5;
                World world = ((Player) sender).getWorld();
                sender.sendMessage(RenYuanCore.getPrefix() + "World:" + world.getName() + "  X:" + X + "  Y:" + Y + "  Z:" + Z);
                RenYuanCore.setSpawnLocation(world,X,Y,Z);
                sender.sendMessage(RenYuanCore.getPrefix() + config.getString("Message.SetSpawn", "已设置主城位置"));
            }else{
                sender.sendMessage(RenYuanCore.getPrefix() + config.getString("Message.NoPerMission","你没有权限"));
            }
        }else{
            sender.sendMessage(RenYuanCore.getPrefix() + config.getString("Message.PlayerCommand","仅玩家可执行该命令"));
        }
        return true;
    }
}
