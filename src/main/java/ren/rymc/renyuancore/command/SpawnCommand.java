package ren.rymc.renyuancore.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.RenYuanAPI;

public class SpawnCommand implements CommandExecutor {

    private final FileConfiguration config = RenYuanAPI.getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!(sender instanceof Player)) {
            RenYuanAPI.sendMessage(sender, config.getString("Message.PlayerCommand","仅玩家可执行该命令"));
            return true;
        }
        ((Player) sender).teleport(RenYuanAPI.getSpawnLocation());
        RenYuanAPI.sendMessage(sender, config.getString("Message.PlayerSpawn","已将你传送到主城"));

        return true;
    }
}
