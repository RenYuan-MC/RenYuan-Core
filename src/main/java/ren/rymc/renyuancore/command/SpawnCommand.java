package ren.rymc.renyuancore.command;

import ren.rymc.renyuancore.RenYuanCore;
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
            ((Player) sender).teleport(RenYuanCore.getSpawnLocation());
            sender.sendMessage(RenYuanCore.getPrefix() + config.getString("Message.PlayerSpawn","已将你传送到主城"));
        }else{
            sender.sendMessage(RenYuanCore.getPrefix() + config.getString("Message.PlayerCommand","仅玩家可执行该命令"));
        }
        return true;
    }
}
