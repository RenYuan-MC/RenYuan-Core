package ren.rymc.renyuancore;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ren.rymc.renyuancore.bedrockmenu.RespawnMenu;
import ren.rymc.renyuancore.command.Command;
import ren.rymc.renyuancore.folia.FoliaUtil;
import ren.rymc.renyuancore.protect.ResCreateLimit;
import ren.rymc.renyuancore.spawn.SpawnExtension;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

public final class RenYuanCore extends JavaPlugin {

    private static RenYuanCore plugin;
    private static final boolean folia = FoliaUtil.folia;
    private final List<String> list = Arrays.asList("help", "reload", "ui");

    @Override
    public void onEnable() {

        saveDefaultConfig();

        plugin = this;
        FileConfiguration config = getPlugin().getConfig();

        if (folia) getLogger().info("Folia支持已加载");

        RenYuanCoreAPI.refreshPrefix();

        PluginCommand command = getCommand("renyuancore");
        if (command != null) {
            command.setExecutor(new Command());
            command.setTabCompleter((s, c, al, a) -> a.length == 1 ? list : null);
            getLogger().info("主指令已注册");
        }

        if (config.getBoolean("Metrics.Enable", true)) {
            new ren.rymc.renyuancore.cstats.Metrics(this);
            new ren.rymc.renyuancore.bstats.Metrics(this,16814);
            getLogger().info("服务端数据统计模块已加载");
        }

        if (config.getBoolean("SpawnExtension.Enable", true)) {
            Bukkit.getPluginManager().registerEvents(new SpawnExtension(), this);
            getLogger().info("主城扩展模块已加载");

        }
        if(Bukkit.getPluginManager().getPlugin("floodgate") != null){
            if(config.getBoolean("BedrockRespawnMenu.Enable", true)){
                Bukkit.getPluginManager().registerEvents(new RespawnMenu(), this);
                getLogger().info("基岩版死亡重生菜单模块已加载");
            }

        }
        if(Bukkit.getPluginManager().getPlugin("Residence") != null){
            Bukkit.getPluginManager().registerEvents(new ResCreateLimit(),this);
            getLogger().info("领地圈地限制模块已加载");
        }

        getLogger().info("任渊生存服务端功能插件加载完毕,作者:RENaa_FD");

    }

    @Override
    public void onDisable() {
        getLogger().info("任渊生存服务端功能插件已关闭,作者:RENaa_FD");
    }

    static RenYuanCore getPlugin() {
        return plugin;
    }

}
