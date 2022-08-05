package ren.rymc.renyuancore.command;

import org.bukkit.Location;
import ren.rymc.renyuancore.RenYuanAPI;
import ren.rymc.renyuancore.RenYuanCore;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCommand implements CommandExecutor {

    private final FileConfiguration config = RenYuanAPI.getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!(sender instanceof Player)) {
            RenYuanAPI.sendMessage(sender, config.getString("Message.PlayerCommand","仅玩家可执行该命令"));
            return true;
        }

        if(sender.hasPermission("spawn.set")) {
            RenYuanAPI.sendMessage(sender, config.getString("Message.NoPerMission","你没有权限"));
            return true;
        }

        Player player = ((Player) sender);
        Location location = player.getLocation();

        double X = location.getBlockX() + 0.5;
        double Y = location.getBlockY();
        double Z = location.getBlockZ() + 0.5;

        World world = player.getWorld();

        RenYuanAPI.sendMessage(sender, "World:" + world.getName() + "  X:" + X + "  Y:" + Y + "  Z:" + Z);
        RenYuanAPI.setSpawnLocation(world,X,Y,Z);
        RenYuanAPI.sendMessage(sender, config.getString("Message.SetSpawn", "已设置主城位置"));

        return true;
    }
}
