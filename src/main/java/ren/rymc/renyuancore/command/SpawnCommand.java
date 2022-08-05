package ren.rymc.renyuancore.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.RenYuanCoreAPI;

public class SpawnCommand implements CommandExecutor {

    private final FileConfiguration config = RenYuanCoreAPI.getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!(sender instanceof Player)) {
            RenYuanCoreAPI.sendMessage(sender, config.getString("Message.PlayerCommand","仅玩家可执行该命令"));
            return true;
        }
        ((Player) sender).teleport(RenYuanCoreAPI.getSpawnLocation());
        RenYuanCoreAPI.sendMessage(sender, config.getString("Message.PlayerSpawn","已将你传送到主城"));

        return true;
    }
}
