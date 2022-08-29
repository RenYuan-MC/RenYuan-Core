package ren.rymc.renyuancore.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import ren.rymc.renyuancore.api.BedrockMenuAPI;
import ren.rymc.renyuancore.api.QuickShopExtendAPI;
import ren.rymc.renyuancore.api.RenYuanAPI;
import ren.rymc.renyuancore.api.SpawnAPI;
import ren.rymc.renyuancore.command.EmptyCommand;
import ren.rymc.renyuancore.command.MainCommand;
import ren.rymc.renyuancore.event.resource.ResourceSender;
import ren.rymc.renyuancore.feature.bedrock.Bedrock;
import ren.rymc.renyuancore.feature.bedrock.quickshop.QuickShop;
import ren.rymc.renyuancore.feature.bedrock.respawn.Respawn;
import ren.rymc.renyuancore.feature.bedrock.tpa.Tpa;
import ren.rymc.renyuancore.feature.protect.Protect;
import ren.rymc.renyuancore.feature.resource.PlayerResourceStatus;
import ren.rymc.renyuancore.feature.resource.ResourceLoader;
import ren.rymc.renyuancore.feature.spawn.SpawnLoader;
import ren.rymc.renyuancore.metrics.Metrics;

import java.io.File;

public final class RenYuanCore extends JavaPlugin implements RenYuanAPI {

    private static RenYuanCore instance;
    private static String prefix;
    private static String extendPrefix;
    private static QuickShopExtendAPI quickShopExtendAPI;
    private static BedrockMenuAPI bedrockMenuAPI;


    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        if (getConfig().getInt("config-version",-1) == -1){
            getLogger().severe("RenYuan-Core 6.0.0 与 5.2.2 及以前版本配置文件不兼容");
            getLogger().severe("请删除配置文件并重启服务器来生成全新的配置文件");
        } else {
            refreshPrefix();
            loadModules();
            registerMainCommand(getCommand("renyuancore"));
            registerMainCommand(getCommand("renyuan-core"));
        }
        getLogger().info("任渊生存服务端功能核心 RenYuan-Core 6.0.0 Dev 已完成加载 作者RENaa_FD");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(getInstance());
        getLogger().info("任渊生存服务端功能核心 RenYuan-Core 6.0.0 Dev 已完成卸载 作者RENaa_FD");
    }

    private void registerMainCommand(PluginCommand command){
        if (command == null) return;
        command.setExecutor(new MainCommand());
        command.setTabCompleter(new MainCommand());
    }

    private void loadModules(){
        new Tpa(){};
        new Respawn(){};
        bedrockMenuAPI = new Bedrock();
        quickShopExtendAPI = new QuickShop();
        new SpawnLoader(){};
        new ResourceLoader(){};
        new Protect();
        if (getInstance().getConfig().getBoolean("metrics.enable")) {
            Metrics metrics = new Metrics(this);
            metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
        }
    }

    public static RenYuanAPI getAPI(){
        return instance;
    }

    public RenYuanCore getInstance(){
        return instance;
    }

    public void sendMessage(CommandSender player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',getPrefix(player) + message));
    }

    public void sendMessage(CommandSender player, String... messages){
        for (String message : messages ) sendMessage(player,message);
    }

    public boolean hasPlugin(String... plugins){
        for (String plugin : plugins) if (hasPlugin(plugin)) return false;
        return true;
    }

    public boolean hasPlugin(String plugin){
        return Bukkit.getPluginManager().getPlugin(plugin) != null;
    }

    public void refreshPrefix(){
        prefix = getConfig().getString("prefix.normal", "§f[§6服务器§f] ");
        extendPrefix = getConfig().getBoolean("prefix.extend.enable") ? getConfig().getString("prefix.extend.prefix") : prefix;
    }

    public String getPrefix(CommandSender player){
        if (!(player instanceof Player)) return prefix;
        if (!getConfig().getBoolean("resource.enable")) return prefix;
        PlayerResourceStatus status = getPlayerResourceStatus((Player) player);
        if (status == null) return prefix;
        if (status.equals(PlayerResourceStatus.Normal)) return prefix;
        if (status.equals(PlayerResourceStatus.Legacy)) return prefix;
        return extendPrefix;
    }

    public String getPrefix(){
        return prefix;
    }


    public BedrockMenuAPI getBedrockMenuAPI(){
        if (!hasPlugin("floodgate")) return null;
        return bedrockMenuAPI;
    }

    public QuickShopExtendAPI getQuickShopExtendAPI(){
        if (!hasPlugin("QuickShop")) return null;
        return quickShopExtendAPI;
    }

    public SpawnAPI getSpawnAPI(){
        if (!getInstance().getConfig().getBoolean("spawn.enable",true)) return null;
        return SpawnLoader.getSpawnAPI();
    }

    public void reloadPlugin(boolean logger){
        if (logger) {
            getInstance().getLogger().warning("你正在重载整个插件,此操作不受支持,可能会出现意想不到的问题");
            getInstance().getLogger().warning("当然,你依然可以把重载插件后遇到的问题报告到此插件的GitHub");
            getInstance().getLogger().warning("但是,这些问题并不会是最优先级处理的,甚至可能不处理");
        }
        reloadPluginConfig();
        HandlerList.unregisterAll(getInstance());
        loadModules();
    }

    public void reloadPluginConfig(){
        getInstance().reloadConfig();
        if (getSpawnAPI() != null) getSpawnAPI().refreshSpawnLocation();
        refreshPrefix();
    }

    public PlayerResourceStatus getPlayerResourceStatus(Player player){
        return ResourceSender.getPlayerResourceStatus(player);
    }

    public FileConfiguration getYamlConfig(String yamlFileName){
        File file = new File(getInstance().getDataFolder(), yamlFileName);
        if (!file.exists()) {
            getInstance().saveResource(yamlFileName, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public void registerEmptyCommand(PluginCommand command){
        if (command == null) return;
        command.setExecutor(new EmptyCommand());
        command.setTabCompleter(new EmptyCommand());
    }
}
