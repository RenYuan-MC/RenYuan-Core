package ren.rymc.renyuancore;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ren.rymc.renyuancore.bedrockmenu.QuickShopMenu;
import ren.rymc.renyuancore.bedrockmenu.RespawnMenu;
import ren.rymc.renyuancore.command.RenYuanCommand;
import ren.rymc.renyuancore.command.SetSpawnCommand;
import ren.rymc.renyuancore.command.SpawnCommand;
import ren.rymc.renyuancore.geyser.GeyserPocketUICheck;
import ren.rymc.renyuancore.metrics.Metrics;
import ren.rymc.renyuancore.protect.AntiUserName;
import ren.rymc.renyuancore.protect.LecternCrashFix;
import ren.rymc.renyuancore.protect.NotBoom;
import ren.rymc.renyuancore.protect.ResCreateLimit;
import ren.rymc.renyuancore.spawn.LockRespawn;
import ren.rymc.renyuancore.spawn.NoSpawnDamage;
import ren.rymc.renyuancore.spawn.SpawnProtect;
import ren.rymc.renyuancore.test.ResourceSender;
import ren.rymc.renyuancore.tpa.CMISupport;
import ren.rymc.renyuancore.tpa.EssentialsSupport;
import ren.rymc.renyuancore.tpa.TpaMenuSend;

import static java.util.Objects.requireNonNull;

public final class RenYuanCore extends JavaPlugin {

    public static RenYuanCore plugin;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        plugin = this;
        FileConfiguration config = getPlugin().getConfig();

        RenYuanCoreAPI.refreshPrefix();
        RenYuanCoreAPI.refreshSpawnLocation();

        requireNonNull(getCommand("renyuancore")).setExecutor(new RenYuanCommand());
        requireNonNull(getCommand("renyuancore")).setTabCompleter(new RenYuanCommand());
        getLogger().info("主指令已注册");

        if (config.getBoolean("NotBoom.Enable", true)) {
            Bukkit.getPluginManager().registerEvents(new NotBoom(), this);
            getLogger().info("防爆模块已加载,魔改自Coby_Cola的NotBoom插件");
        }

        if (config.getBoolean("Metrics.Enable", true)) {
            Metrics metrics = new Metrics(this);
            metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
            getLogger().info("服务端数据统计模块已加载");
        }

        if (config.getBoolean("LecternCrashFix.Enable", true)) {
            Bukkit.getPluginManager().registerEvents(new LecternCrashFix(), this);
            getLogger().info("讲台崩服修复模块已加载");
        }

        if (config.getBoolean("Spawn.Enable", true)) {
            if (config.getBoolean("Spawn.Protect", true)) {
                Bukkit.getPluginManager().registerEvents(new SpawnProtect(), this);
                getLogger().info("主城保护模块已开启");
            }
            if (config.getBoolean("Spawn.LockSpawn", true)) {
                Bukkit.getPluginManager().registerEvents(new LockRespawn(), this);
                getLogger().info("出生点锁定模块已加载");
            }
            if (config.getBoolean("Spawn.NoDamage", true)) {
                Bukkit.getPluginManager().registerEvents(new NoSpawnDamage(), this);
                getLogger().info("主城伤害关闭模块已加载");
            }
            requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
            requireNonNull(getCommand("setspawn")).setExecutor(new SetSpawnCommand());
            getLogger().info("主城指令已注册");

        }
        if(Bukkit.getPluginManager().getPlugin("floodgate") != null){

            if (config.getBoolean("GeyserPocketUICheck.Enable", false)) {
                Bukkit.getPluginManager().registerEvents(new GeyserPocketUICheck(), this);
                getLogger().info("基岩版PocketUI检测模块已加载");
            }

            if (config.getBoolean("AntiUserName.Enable", true)) {
                Bukkit.getPluginManager().registerEvents(new AntiUserName(), this);
                getLogger().info("基岩版玩家名称检测模块已加载");
            }

            if(config.getBoolean("BedrockTpaMenu.Enable", true)){
                Bukkit.getPluginManager().registerEvents(new TpaMenuSend(), this);
                getLogger().info("基岩版tpa菜单按钮监听模块已加载");
                if(Bukkit.getPluginManager().getPlugin("CMI") != null){
                    Bukkit.getPluginManager().registerEvents(new CMISupport(), this);
                    getLogger().info("基岩版tpa菜单-CMI兼容模块已加载");
                }
                if(Bukkit.getPluginManager().getPlugin("Essentials") != null){
                    Bukkit.getPluginManager().registerEvents(new EssentialsSupport(), this);
                    getLogger().info("基岩版tpa菜单-ESS兼容模块已加载");
                }
            }

            if(Bukkit.getPluginManager().getPlugin("QuickShop") != null){
                Bukkit.getPluginManager().registerEvents(new QuickShopMenu(),this);
                getLogger().info("基岩版QuickShop菜单模块已加载");
            }

            if(config.getBoolean("BedrockRespawnMenu.Enable", true)){
                Bukkit.getPluginManager().registerEvents(new RespawnMenu(), this);
                getLogger().info("基岩版死亡重生菜单模块已加载");
            }

        }
        if(Bukkit.getPluginManager().getPlugin("Residence") != null){
            Bukkit.getPluginManager().registerEvents(new ResCreateLimit(),this);
            getLogger().info("领地圈地限制模块已加载");
        }
        if(config.getBoolean("TestFeature.Enable",false)){
            getLogger().warning("你正在加载实验性内容,这些内容仍然在制作中,可能会出现未知的问题");
            getLogger().warning("实验性功能Bug报告请前往: https://github.com/lRENyaaa/RenYuan-Core/issues");
            if(config.getBoolean("TestFeature.ResourcePackSender",false) && Bukkit.getPluginManager().getPlugin("ViaVersion") != null && Bukkit.getPluginManager().getPlugin("floodgate") != null){
                if(Bukkit.getPluginManager().getPlugin("ItemsAdder") != null){
                    getLogger().warning("检测到ItemsAdder,为确保兼容性,请使用ItemsAdder的资源包功能而不是RenYuan-Core的");
                }
                requireNonNull(getCommand("loadpack")).setExecutor(new ResourceSender());
                requireNonNull(getCommand("loadpack")).setTabCompleter(new ResourceSender());
                getLogger().info("实验性内容-资源包加载指令已注册");
                Bukkit.getPluginManager().registerEvents(new ResourceSender(), this);
                getLogger().info("实验性内容-资源包监听模块已加载");
            }
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
