package ren.rymc.renyuancore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.test.PlayerResourceStatus;
import ren.rymc.renyuancore.test.ResourceSender;

public class RenYuanCoreAPI {

    private static String prefix;
    private static Location spawnLocation;

    public static RenYuanCore getPlugin() {
        return RenYuanCore.getPlugin();
    }

    public static String getPrefix() {
        return prefix;
    }

    public static Location getSpawnLocation() {
        return spawnLocation;
    }

    public static void setSpawnLocation(@NotNull World world, double X, double Y, double Z) {
        getConfig().set("Spawn.World",world.getName());
        getConfig().set("Spawn.X",X);
        getConfig().set("Spawn.Y",Y);
        getConfig().set("Spawn.Z",Z);
        getPlugin().saveConfig();
        refreshSpawnLocation();
    }

    public static void reloadPlugin(){
        reloadPluginConfig();
        getPlugin().onDisable();
        getPlugin().onLoad();
        getPlugin().onEnable();
    }

    public static void reloadPluginConfig(){
        getPlugin().reloadConfig();
        refreshSpawnLocation();
        refreshPrefix();
    }

    public static void refreshSpawnLocation(){
        World world = Bukkit.getWorld(getConfig().getString("Spawn.World", "spawn"));
        if(world == null){
            getPlugin().getLogger().warning("未检测到主城世界名称,已使用主世界代替");
            world = Bukkit.getWorld("world");
        }
        spawnLocation = new Location(world, getConfig().getDouble("Spawn.X", 59.5), getConfig().getDouble("Spawn.Y", 105.0), getConfig().getDouble("Spawn.Z", -148.5), 0.0F, 0.0F);
    }

    public static void sendMessage(CommandSender player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + message));
    }

    public static void sendMessage(CommandSender player, String... messages){
        for (String message : messages ) sendMessage(player,message);
    }

    public static PlayerResourceStatus getPlayerResourceStatus(Player player){
        return ResourceSender.getPlayerResourceStatus(player);
    }

    public static void refreshPrefix(){
        prefix = getConfig().getString("Message.Prefix", "§f[§6服务器§f] ");
    }

    public static FileConfiguration getConfig(){
        return getPlugin().getConfig();
    }

    public static String getSpawnWorldName(){
        return spawnLocation.getWorld().getName();
    }
}
