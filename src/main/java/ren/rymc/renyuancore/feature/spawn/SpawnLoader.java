package ren.rymc.renyuancore.feature.spawn;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import ren.rymc.renyuancore.api.RenYuanAPI;
import ren.rymc.renyuancore.api.SpawnAPI;
import ren.rymc.renyuancore.command.SpawnCommands;
import ren.rymc.renyuancore.event.spawn.NoSpawnDamage;
import ren.rymc.renyuancore.event.spawn.SpawnLocker;
import ren.rymc.renyuancore.event.spawn.SpawnProtect;
import ren.rymc.renyuancore.main.RenYuanCore;

public class SpawnLoader {

    private static final RenYuanAPI api = RenYuanCore.getAPI();
    private static final FileConfiguration config = api.getInstance().getConfig();
    private static final PluginManager pm = Bukkit.getPluginManager();
    private static SpawnAPI spawnAPI;

    static {
        PluginCommand spawn = api.getInstance().getCommand("spawn");
        PluginCommand setspawn = api.getInstance().getCommand("setspawn");
        if(config.getBoolean("spawn.enable")){
            spawnAPI = new Spawn();
            spawnAPI.refreshSpawnLocation();
            if (config.getBoolean("spawn.lock-spawn", true)) pm.registerEvents(new SpawnLocker(), api.getInstance());
            if (config.getBoolean("spawn.no-damage", true)) pm.registerEvents(new NoSpawnDamage(), api.getInstance());
            if (config.getBoolean("spawn.protect", true)) pm.registerEvents(new SpawnProtect(), api.getInstance());
            registerSpawnCommand(spawn);
            registerSpawnCommand(setspawn);
        }else{
            api.registerEmptyCommand(spawn);
            api.registerEmptyCommand(setspawn);
        }
    }

    public static SpawnAPI getSpawnAPI(){
        return spawnAPI;
    }

    private static void registerSpawnCommand(PluginCommand command){
        if (command == null) return;
        command.setExecutor(new SpawnCommands());
        command.setTabCompleter(new SpawnCommands());
    }

}
