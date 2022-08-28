package ren.rymc.renyuancore.command;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.main.RenYuanCore;

import java.util.List;

public class SpawnCommands implements CommandExecutor, TabExecutor {

    private final FileConfiguration config = RenYuanCore.getAPI().getInstance().getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (command.getName().equals("spawn")) return spawn(sender);
        if (command.getName().equals("setspawn")) return setspawn(sender);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        return null;
    }

    private boolean spawn(CommandSender sender){
        if(!(sender instanceof Player)) {
            RenYuanCore.getAPI().sendMessage(sender, config.getString("Message.PlayerCommand","仅玩家可执行该命令"));
            return true;
        }
        ((Player) sender).teleport(RenYuanCore.getAPI().getSpawnAPI().getSpawnLocation());
        RenYuanCore.getAPI().sendMessage(sender, config.getString("Message.PlayerSpawn","已将你传送到主城"));

        return true;
    }

    private boolean setspawn(CommandSender sender){
        if(!(sender instanceof Player)) {
            RenYuanCore.getAPI().sendMessage(sender, config.getString("Message.PlayerCommand","仅玩家可执行该命令"));
            return true;
        }

        if(!sender.hasPermission("spawn.set")) {
            RenYuanCore.getAPI().sendMessage(sender, config.getString("Message.NoPerMission","你没有权限"));
            return true;
        }

        Player player = ((Player) sender);
        Location location = player.getLocation();

        double X = location.getBlockX() + 0.5;
        double Y = location.getBlockY();
        double Z = location.getBlockZ() + 0.5;

        World world = player.getWorld();

        RenYuanCore.getAPI().sendMessage(sender, "World:" + world.getName() + "  X:" + X + "  Y:" + Y + "  Z:" + Z);
        RenYuanCore.getAPI().getSpawnAPI().setSpawnLocation(world, X, Y, Z,0.0F,0.0F);
        RenYuanCore.getAPI().sendMessage(sender, config.getString("Message.SetSpawn", "已设置主城位置"));

        return true;
    }
}
