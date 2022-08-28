package ren.rymc.renyuancore.feature.resource;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import ren.rymc.renyuancore.api.RenYuanAPI;
import ren.rymc.renyuancore.command.ResourceCommand;
import ren.rymc.renyuancore.event.resource.ResourceSender;
import ren.rymc.renyuancore.main.RenYuanCore;

public class ResourceLoader {

    private static final RenYuanAPI api = RenYuanCore.getAPI();
    private static final FileConfiguration config = api.getInstance().getConfig();
    private static final PluginManager pm = Bukkit.getPluginManager();

    static {
        PluginCommand loadpack = api.getInstance().getCommand("loadpack");
        if(config.getBoolean("resource.enable") && api.hasPlugin("floodgate")){
            pm.registerEvents(new ResourceSender(),api.getInstance());
            if (loadpack != null) {
                loadpack.setExecutor(new ResourceCommand());
                loadpack.setTabCompleter(new ResourceCommand());
            }
        }else{
            api.registerEmptyCommand(loadpack);
        }
    }
}
