package com.renyuansurvival.renyuancore;

import com.renyuansurvival.renyuancore.Metrics.Metrics;
import com.renyuansurvival.renyuancore.Spawn.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RenYuanCore extends JavaPlugin {

    public static RenYuanCore Plugin;
    public static String Prefix;
    public static Location SpawnLocation;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        Plugin = this;

        Prefix = getConfig().getString("Message.Prefix", "§f[§6服务器§f] ");
        SpawnLocation = new Location(Bukkit.getWorld(getConfig().getString("Spawn.World", "spawn")),getConfig().getDouble("Spawn.X",59.5),getConfig().getDouble("Spawn.Y",105.0),getConfig().getDouble("Spawn.Z",-148.5),0.0F,0.0F);


        if (getConfig().getBoolean("NotBoom.Enable", true)) {
            Bukkit.getPluginManager().registerEvents(new NotBoom(), this);
            getLogger().info("防爆模块已加载,魔改自Coby_Cola的NotBoom插件");
        }

        if (getConfig().getBoolean("AntiUserName.Enable", true)) {
            Bukkit.getPluginManager().registerEvents(new AntiUserName(), this);
            getLogger().info("基岩版玩家名称检测模块已加载");
        }

        if (getConfig().getBoolean("Metrics.Enable", true)) {
            Metrics metrics = new Metrics(this);
            metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
            getLogger().info("服务端数据统计模块已加载");
        }

        if (getConfig().getBoolean("LecternCrashFix.Enable", true)) {
            Bukkit.getPluginManager().registerEvents(new LecternCrashFix(), this);
            getLogger().info("讲台崩服修复模块已开启");
        }

        if (getConfig().getBoolean("Spawn.Enable", true)) {
            if (getConfig().getBoolean("Spawn.Protect", true)) {
                Bukkit.getPluginManager().registerEvents(new SpawnProtect(), this);
                getLogger().info("主城保护模块已开启");
            }
            if (getConfig().getBoolean("Spawn.LockSpawn", true)) {
                Bukkit.getPluginManager().registerEvents(new LockRespawn(), this);
                getLogger().info("出生点锁定模块已开启");
            }
            if (getConfig().getBoolean("Spawn.NoDamage", true)) {
                Bukkit.getPluginManager().registerEvents(new NoSpawnDamage(), this);
                getLogger().info("主城伤害关闭模块已开启");
            }
            Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
            getLogger().info("主城指令已注册");

        }

        getLogger().info("任渊生存服务端功能插件加载完毕,作者:RENaa_FD");

    }

    @Override
    public void onDisable() {
        getLogger().info("任渊生存服务端功能插件已关闭,作者:RENaa_FD");
    }

    public static RenYuanCore getPlugin() {
        return Plugin;
    }

    public static String getPrefix() {
        return Prefix;
    }

    public static Location getSpawnLocation() {
        return SpawnLocation;
    }
}
