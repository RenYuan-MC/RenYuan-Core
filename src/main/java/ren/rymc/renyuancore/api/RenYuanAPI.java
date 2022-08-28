package ren.rymc.renyuancore.api;

import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ren.rymc.renyuancore.feature.resource.PlayerResourceStatus;
import ren.rymc.renyuancore.main.RenYuanCore;

public interface RenYuanAPI {

    RenYuanCore getInstance();

    void sendMessage(CommandSender player, String message);

    void sendMessage(CommandSender player, String... message);

    boolean hasPlugin(String... plugin);

    boolean hasPlugin(String plugin);

    void refreshPrefix();

    String getPrefix();

    String getPrefix(CommandSender player);

    QuickShopExtendAPI getQuickShopExtendAPI();

    SpawnAPI getSpawnAPI();

    BedrockMenuAPI getBedrockMenuAPI();

    void reloadPlugin(boolean enableLogger);

    void reloadPluginConfig();

    PlayerResourceStatus getPlayerResourceStatus(Player player);

    FileConfiguration getYamlConfig(String yamlFileName);

    void registerEmptyCommand(PluginCommand command);
}
