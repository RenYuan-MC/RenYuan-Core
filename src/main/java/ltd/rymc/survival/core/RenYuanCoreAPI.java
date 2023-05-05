package ltd.rymc.survival.core;

import ltd.rymc.survival.core.spawn.SpawnLocationGetter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RenYuanCoreAPI {

    private static String prefix;

    public static RenYuanCore getPlugin() {
        return RenYuanCore.getPlugin();
    }

    public static String getPrefix() {
        return prefix;
    }

    public static Plugin getPlugin(String plugin) {
        return Bukkit.getPluginManager().getPlugin(plugin);
    }

    public static Location getSpawnLocation(Player player) {
        Location spawn = SpawnLocationGetter.getESSSpawnLocation(player);
        if(spawn == null) spawn = SpawnLocationGetter.getCMISpawnLocation(player);
        return spawn;
    }
    @Deprecated
    public static void reloadPlugin(){
        reloadPluginConfig();
    }

    public static void reloadPluginConfig(){
        getPlugin().reloadConfig();
        refreshPrefix();
    }


    public static void sendMessage(CommandSender player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + message));
    }

    public static void sendMessage(CommandSender player, String... messages){
        for (String message : messages ) sendMessage(player,message);
    }

    public static void refreshPrefix(){
        prefix = getConfig().getString("Prefix", "§f[§6服务器§f] ");
    }

    public static FileConfiguration getConfig(){
        return getPlugin().getConfig();
    }

    @Deprecated
    public static String getSpawnWorldName(Player player){
        return getSpawnLocation(player).getWorld().getName();
    }

    @Deprecated
    public static void refreshSpawnLocation(){}

}
