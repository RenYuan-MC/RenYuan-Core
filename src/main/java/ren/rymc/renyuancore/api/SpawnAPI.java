package ren.rymc.renyuancore.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public interface SpawnAPI {

    void refreshSpawnLocation();

    Location getSpawnLocation();

    String getSpawnWorldName();

    void setSpawnLocation(@NotNull World world, double X, double Y, double Z, float yaw, float pitch);
}
