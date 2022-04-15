package com.renyuansurvival.renyuancore;

import com.renyuansurvival.renyuancore.Metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RenYuanCore extends JavaPlugin {

    public static RenYuanCore Plugin;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        Plugin = this;

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
            getLogger().info("讲台崩服修复已开启");
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
}
