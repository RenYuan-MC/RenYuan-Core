package ren.rymc.renyuancore;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ren.rymc.renyuancore.bedrockmenu.MainMenu;
import ren.rymc.renyuancore.bedrockmenu.RespawnMenu;
import ren.rymc.renyuancore.command.RenYuanCommand;
import ren.rymc.renyuancore.command.SetSpawnCommand;
import ren.rymc.renyuancore.command.SpawnCommand;
import ren.rymc.renyuancore.geyser.GeyserPocketUICheck;
import ren.rymc.renyuancore.protect.AntiUserName;
import ren.rymc.renyuancore.protect.LecternCrashFix;
import ren.rymc.renyuancore.protect.NotBoom;
import ren.rymc.renyuancore.protect.ResCreateLimit;
import ren.rymc.renyuancore.spawn.LockRespawn;
import ren.rymc.renyuancore.spawn.NoSpawnDamage;
import ren.rymc.renyuancore.spawn.SpawnProtect;

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
            new ren.rymc.renyuancore.cstats.Metrics(this);
            new ren.rymc.renyuancore.bstats.Metrics(this,16814);
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
            if(config.getBoolean("TestFeature.BEMainMenu",false) && Bukkit.getPluginManager().getPlugin("floodgate") != null){
                requireNonNull(getCommand("obemenu")).setExecutor(new MainMenu());
                Bukkit.getPluginManager().registerEvents(new MainMenu(), this);
                getLogger().info("实验性内容-基岩版玩家菜单已加载");
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
