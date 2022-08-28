package ren.rymc.renyuancore.feature.spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.api.RenYuanAPI;
import ren.rymc.renyuancore.api.SpawnAPI;
import ren.rymc.renyuancore.main.RenYuanCore;

import java.io.File;
import java.io.IOException;

public class Spawn implements SpawnAPI {

    private static Location spawnLocation;
    private static FileConfiguration spawnConfig = null;
    private static final RenYuanAPI api = RenYuanCore.getAPI();


    public void refreshSpawnLocation(){
        spawnConfig = api.getYamlConfig("spawn.yml");
        World world = Bukkit.getWorld(spawnConfig.getString("world", "spawn"));
        if(world == null){
            RenYuanCore.getAPI().getInstance().getLogger().warning("未检测到主城世界名称,已使用主世界代替");
            world = Bukkit.getWorld("world");
        }
        spawnLocation = new Location(world, spawnConfig.getDouble("x", 59.5), spawnConfig.getDouble("y", 105.0), spawnConfig.getDouble("z", -148.5), (float) spawnConfig.getDouble("yaw",0.0), (float) spawnConfig.getDouble("pitch",0.0));
    }

    public Location getSpawnLocation(){
        if (spawnLocation == null) refreshSpawnLocation();
        return spawnLocation;
    }

    public String getSpawnWorldName(){
        return getSpawnLocation().getWorld().getName();
    }

    public void setSpawnLocation(@NotNull World world, double X, double Y, double Z, float yaw, float pitch){
        if (spawnConfig == null) spawnConfig = api.getYamlConfig("spawn.yml");
        spawnConfig.set("world",world.getName());
        spawnConfig.set("x",X);
        spawnConfig.set("y",Y);
        spawnConfig.set("z",Z);
        spawnConfig.set("yaw",yaw);
        spawnConfig.set("pitch",pitch);
        try {
            spawnConfig.save(new File(api.getInstance().getDataFolder(), "spawn.yml"));
        } catch (IOException ignored) {
        }
        refreshSpawnLocation();
    }
}
