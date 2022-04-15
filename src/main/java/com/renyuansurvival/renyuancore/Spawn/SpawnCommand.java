package com.renyuansurvival.renyuancore.Spawn;

import com.renyuansurvival.renyuancore.RenYuanCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    private final FileConfiguration config = RenYuanCore.getPlugin().getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            ((Player) sender).teleport(new Location(Bukkit.getWorld(config.getString("Spawn.World", "spawn")),config.getDouble("Spawn.X",59.5),config.getDouble("Spawn.Y",105.0),config.getDouble("Spawn.Z",-149.5),0.0F,0.0F));
            sender.sendMessage(RenYuanCore.getPrefix() + config.getString("Message.PlayerSpawn","已将你传送到主城"));
        }else{
            sender.sendMessage(RenYuanCore.getPrefix() + config.getString("Message.ConsoleSpawn","仅玩家可执行该命令"));
        }
        return true;
    }
}
