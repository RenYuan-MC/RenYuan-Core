package ren.rymc.renyuancore.feature.protect;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import ren.rymc.renyuancore.api.RenYuanAPI;
import ren.rymc.renyuancore.event.protect.*;
import ren.rymc.renyuancore.main.RenYuanCore;

public class Protect {

    private static final RenYuanAPI api = RenYuanCore.getAPI();
    private static final FileConfiguration config = api.getInstance().getConfig();
    private static final PluginManager pm = Bukkit.getPluginManager();

    static {
        if (config.getBoolean("protect.enable", true)) {
            if (api.hasPlugin("floodgate") && config.getBoolean("protect.anti-user-name.enable")) pm.registerEvents(new AntiUserName(), api.getInstance());
            if (config.getBoolean("protect.not-boom.enable")) pm.registerEvents(new NotBoom(), api.getInstance());
            if (api.hasPlugin("Residence") && config.getBoolean("protect.res-limit.enable")) pm.registerEvents(new ResCreateLimit(), api.getInstance());
            if (config.getBoolean("protect.lectern-crash-fix.enable")) pm.registerEvents(new LecternCrashFix(), api.getInstance());
            if (config.getBoolean("protect.reserved-users.enable")) pm.registerEvents(new ReservedUsers(),api.getInstance());
        }
    }
}
