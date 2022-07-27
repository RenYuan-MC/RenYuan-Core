package ren.rymc.renyuancore;

import org.maxgamer.quickshop.api.QuickShopAPI;
import ren.rymc.renyuancore.bedrockmenu.QuickShopMenu;
import ren.rymc.renyuancore.command.RenYuanCommand;
import ren.rymc.renyuancore.command.SetSpawnCommand;
import ren.rymc.renyuancore.command.SpawnCommand;
import ren.rymc.renyuancore.geyser.GeyserPocketUICheck;
import ren.rymc.renyuancore.metrics.Metrics;
import ren.rymc.renyuancore.protect.AntiUserName;
import ren.rymc.renyuancore.protect.LecternCrashFix;
import ren.rymc.renyuancore.protect.NotBoom;
import ren.rymc.renyuancore.spawn.LockRespawn;
import ren.rymc.renyuancore.spawn.SpawnProtect;
import ren.rymc.renyuancore.tpa.CMISupport;
import ren.rymc.renyuancore.tpa.EssentialsSupport;
import ren.rymc.renyuancore.tpa.TpaMenuSend;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.spawn.NoSpawnDamage;

import static java.util.Objects.requireNonNull;

public final class RenYuanCore extends JavaPlugin {

    public static RenYuanCore Plugin;
    public static String Prefix;
    public static Location SpawnLocation;
    public static FileConfiguration Config;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        Plugin = this;
        Config = getPlugin().getConfig();

        refreshPrefix();
        refreshSpawnLocation();

        requireNonNull(getCommand("renyuancore")).setExecutor(new RenYuanCommand());
        requireNonNull(getCommand("renyuancore")).setTabCompleter(new RenYuanCommand());
        getLogger().info("主指令已注册");

        if (Config.getBoolean("NotBoom.Enable", true)) {
            Bukkit.getPluginManager().registerEvents(new NotBoom(), this);
            getLogger().info("防爆模块已加载,魔改自Coby_Cola的NotBoom插件");
        }

        if (Config.getBoolean("Metrics.Enable", true)) {
            Metrics metrics = new Metrics(this);
            metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
            getLogger().info("服务端数据统计模块已加载");
        }

        if (Config.getBoolean("LecternCrashFix.Enable", true)) {
            Bukkit.getPluginManager().registerEvents(new LecternCrashFix(), this);
            getLogger().info("讲台崩服修复模块已加载");
        }

        if (Config.getBoolean("Spawn.Enable", true)) {
            if (Config.getBoolean("Spawn.Protect", true)) {
                Bukkit.getPluginManager().registerEvents(new SpawnProtect(), this);
                getLogger().info("主城保护模块已开启");
            }
            if (Config.getBoolean("Spawn.LockSpawn", true)) {
                Bukkit.getPluginManager().registerEvents(new LockRespawn(), this);
                getLogger().info("出生点锁定模块已加载");
            }
            if (Config.getBoolean("Spawn.NoDamage", true)) {
                Bukkit.getPluginManager().registerEvents(new NoSpawnDamage(), this);
                getLogger().info("主城伤害关闭模块已加载");
            }
            requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
            requireNonNull(getCommand("setspawn")).setExecutor(new SetSpawnCommand());
            getLogger().info("主城指令已注册");

        }
        if(Bukkit.getPluginManager().getPlugin("floodgate") != null){

            if (Config.getBoolean("GeyserPocketUICheck.Enable", false)) {
                Bukkit.getPluginManager().registerEvents(new GeyserPocketUICheck(), this);
                getLogger().info("基岩版PocketUI检测模块已加载");
            }

            if (Config.getBoolean("AntiUserName.Enable", true)) {
                Bukkit.getPluginManager().registerEvents(new AntiUserName(), this);
                getLogger().info("基岩版玩家名称检测模块已加载");
            }

            if(Bukkit.getPluginManager().getPlugin("BEMenuAPI") != null && Config.getBoolean("BedrockTpaMenu.Enable", true)){
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

            if(Bukkit.getPluginManager().getPlugin("QuickShop") != null && Bukkit.getPluginManager().getPlugin("BEMenuAPI") != null){
                Bukkit.getPluginManager().registerEvents(new QuickShopMenu(),this);
                getLogger().info("基岩版QuickShop菜单模块已加载");
            }
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

    public static void setSpawnLocation(@NotNull World world, double X, double Y, double Z) {
        Config.set("Spawn.World",world.getName());
        Config.set("Spawn.X",X);
        Config.set("Spawn.Y",Y);
        Config.set("Spawn.Z",Z);
        getPlugin().saveConfig();
        refreshSpawnLocation();
    }

    public static void reloadPlugin(){
        reloadPluginConfig();
        getPlugin().onDisable();
        getPlugin().onLoad();
        getPlugin().onEnable();
    }

    public static void reloadPluginConfig(){
        getPlugin().reloadConfig();
        refreshSpawnLocation();
        refreshPrefix();
    }
    
    public static void refreshSpawnLocation(){
        World world = Bukkit.getWorld(Config.getString("Spawn.World", "spawn"));
        if(world == null){
            getPlugin().getLogger().warning("未检测到主城世界名称,已使用主世界代替");
            world = Bukkit.getWorld("world");
        }
        SpawnLocation = new Location(world, Config.getDouble("Spawn.X", 59.5), Config.getDouble("Spawn.Y", 105.0), Config.getDouble("Spawn.Z", -148.5), 0.0F, 0.0F);
    }

    public static void refreshPrefix(){
        Prefix = Config.getString("Message.Prefix", "§f[§6服务器§f] ");
    }

}