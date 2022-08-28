package ren.rymc.renyuancore;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.feature.resource.PlayerResourceStatus;
import ren.rymc.renyuancore.main.RenYuanCore;

@Deprecated
public class RenYuanCoreAPI {

    @Deprecated
    public static RenYuanCore getPlugin() {
        return RenYuanCore.getAPI().getInstance();
    }

    @Deprecated
    public static String getPrefix(){
        return RenYuanCore.getAPI().getPrefix();
    }

    @Deprecated
    public static Location getSpawnLocation(){
        return RenYuanCore.getAPI().getSpawnAPI().getSpawnLocation();
    }

    @Deprecated
    public static void setSpawnLocation(@NotNull World world, double X, double Y, double Z) {
        RenYuanCore.getAPI().getSpawnAPI().setSpawnLocation(world,X,Y,Z,0.0F,0.0F);
    }

    @Deprecated
    public static void reloadPlugin(){
        RenYuanCore.getAPI().reloadPlugin(false);
    }

    @Deprecated
    public static void reloadPluginConfig(){
        RenYuanCore.getAPI().reloadPluginConfig();
    }

    @Deprecated
    public static void refreshSpawnLocation(){
        RenYuanCore.getAPI().getSpawnAPI().refreshSpawnLocation();
    }

    @Deprecated
    public static void sendMessage(CommandSender player, String message){
        RenYuanCore.getAPI().sendMessage(player,message);
    }

    @Deprecated
    public static void sendMessage(CommandSender player, String... messages){
        RenYuanCore.getAPI().sendMessage(player,messages);
    }

    @Deprecated
    public static PlayerResourceStatus getPlayerResourceStatus(Player player){
        return RenYuanCore.getAPI().getPlayerResourceStatus(player);
    }

    @Deprecated
    public static void refreshPrefix(){
        RenYuanCore.getAPI().refreshPrefix();
    }

    @Deprecated
    public static FileConfiguration getConfig(){
        return getPlugin().getConfig();
    }

    @Deprecated
    public static String getSpawnWorldName(){
        return RenYuanCore.getAPI().getSpawnAPI().getSpawnWorldName();
    }


}
