package ren.rymc.renyuancore.spawn;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ren.rymc.renyuancore.RenYuanCoreAPI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SpawnLocationGetter {

    private static boolean essStatus = false;
    private static boolean cmiStatus = false;

    private static final Plugin essSpawnPlugin;
    private static final Plugin essPlugin;
    private static final Plugin cmiLibPlugin;
    private static final Plugin cmiPlugin;

    private static Object ess;
    private static Object essSpawn;

    private static Class<?> cmiSpawnClass;
    private static Method essGetUserMethod;
    private static Method essSpawnGetSpawnMethod;
    private static Method essGetGroupMethod;
    private static Method cmiGetSpawnMethod;


    static {
        String essName = "com.earth2me.essentials.IEssentials";
        String essSpawnName = "com.earth2me.essentials.spawn.EssentialsSpawn";
        String essUser = "com.earth2me.essentials.User";
        String cmiSpawnName = "com.Zrips.CMI.utils.SpawnUtil";

        essPlugin = getPlugin("Essentials");
        essSpawnPlugin = getPlugin("EssentialsSpawn");

        cmiPlugin = getPlugin("CMI");
        cmiLibPlugin = getPlugin("CMILib");

        if (essSpawnPlugin != null && essPlugin != null) essStatus = true;
        if (cmiLibPlugin != null && cmiPlugin != null) cmiStatus = true;

        try {

            essSpawnGetSpawnMethod = Class.forName(essSpawnName).getMethod("getSpawn", String.class);
            essGetUserMethod = Class.forName(essName).getMethod("getUser", Player.class);
            essGetGroupMethod = Class.forName(essUser).getMethod("getGroup");

            ess = Class.forName(essName).cast(essPlugin);
            essSpawn = Class.forName(essSpawnName).cast(essSpawnPlugin);

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            essStatus = false;
        }

        try {

            cmiSpawnClass = Class.forName(cmiSpawnName);
            cmiGetSpawnMethod = Class.forName(cmiSpawnName).getMethod("getSpawnPoint", Player.class);

        } catch (NoSuchMethodException | ClassNotFoundException e) {
            cmiStatus = false;
        }

    }

    public static Location getESSSpawnLocation(Player player) {

        // 如不使用反射,则代码是这样的:
        //     if (getPlugin("EssentialsSpawn") == null || getPlugin("Essentials") == null) return null;
        //     EssentialsSpawn essSpawn = (EssentialsSpawn) getPlugin("EssentialsSpawn");
        //     IEssentials ess = (IEssentials) getPlugin("Essentials");
        //     if (essSpawn == null || ess == null) return null;
        //     return essSpawn.getSpawn(ess.getUser(player).getGroup());

        if (!essStatus) return null;
        try {
            return (Location) essSpawnGetSpawnMethod.invoke(essSpawn,essGetGroupMethod.invoke(essGetUserMethod.invoke(ess,player)));
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }

    }


    public static Location getCMISpawnLocation(Player player){

        // 如不使用反射,则代码是这样的:
        //     if (getPlugin("CMI") == null || getPlugin("CMILib") == null) return null;
        //     return SpawnUtil.getSpawnPoint(player);


        if (!cmiStatus) return null;
        try {
            return (Location) cmiGetSpawnMethod.invoke(cmiSpawnClass,player);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }

    }

    public static Plugin getPlugin(String plugin) {
        return RenYuanCoreAPI.getPlugin(plugin);
    }
}
