package ren.rymc.renyuancore;

import com.Zrips.CMI.utils.SpawnUtil;
import com.earth2me.essentials.IEssentials;
import com.earth2me.essentials.spawn.EssentialsSpawn;
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
        Location spawn = getESSSpawnLocation(player);
        if(spawn == null) spawn = getCMISpawnLocation(player);
        return spawn;
    }

    public static Location getESSSpawnLocation(Player player) {
        if (getPlugin("EssentialsSpawn") == null || getPlugin("Essentials") == null) return null;
        EssentialsSpawn essSpawn = (EssentialsSpawn) getPlugin("EssentialsSpawn");
        IEssentials ess = (IEssentials) getPlugin("Essentials");
        if (essSpawn == null || ess == null) return null;
        return essSpawn.getSpawn(ess.getUser(player).getGroup());
    }

    public static Location getCMISpawnLocation(Player player){
        if (getPlugin("CMI") == null || getPlugin("CMILib") == null) return null;
        return SpawnUtil.getSpawnPoint(player);
    }


    public static void reloadPlugin(){
        reloadPluginConfig();
        getPlugin().onDisable();
        getPlugin().onLoad();
        getPlugin().onEnable();
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
        prefix = getConfig().getString("Message.Prefix", "§f[§6服务器§f] ");
    }

    public static FileConfiguration getConfig(){
        return getPlugin().getConfig();
    }

}
