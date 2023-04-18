package ren.rymc.renyuancore.folia;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public class FoliaUtil {
    public static boolean folia = isFolia();

    public static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            Class.forName("io.papermc.paper.threadedregions.RegionizedServerInitEvent");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static Object globalRegionScheduler;
    private static Method runDelayed;
    private static Method runMethod;
    private static Method teleportAsync;

    static {
        // init reflect for folia
        if (folia) {
            try {
                String globalRegionSchedulerName = "io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler";
                Method getGlobalRegionScheduler = Bukkit.class.getMethod("getGlobalRegionScheduler");
                globalRegionScheduler = getGlobalRegionScheduler.invoke(Bukkit.class);
                runMethod = Class.forName(globalRegionSchedulerName).getMethod("run", Plugin.class, Consumer.class);
                runDelayed = Class.forName(globalRegionSchedulerName).getMethod("runDelayed", Plugin.class, Consumer.class, long.class);

                // folia async teleport
                teleportAsync = Player.class.getMethod("teleportAsync", Location.class);

            } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace();
                folia = false;
            }
        }
    }

    // just teleport (for folia support)
    public static void teleport(Player player, Location location) {
        if (folia) {
            try {
                teleportAsync.invoke(player, location);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else player.teleport(location);
    }

    // for all codes that use org.bukkit.scheduler.BukkitScheduler#runTask
    public static void runTask(Plugin plugin, Runnable runnable) {
        if (folia) {
            try {
                runMethod.invoke(globalRegionScheduler, plugin, (Consumer<?>) task -> runnable.run());
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            Bukkit.getScheduler().runTask(plugin, runnable);
        }
    }

    // for all codes that use org.bukkit.scheduler.BukkitScheduler#runTaskLater
    public static void runTaskLater(Plugin plugin, Runnable runnable, long l) {
        if (folia) {
            try {
                runDelayed.invoke(globalRegionScheduler, plugin, (Consumer<?>) task -> runnable.run(), l);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            Bukkit.getScheduler().runTaskLater(plugin, runnable, l);
        }
    }
}

