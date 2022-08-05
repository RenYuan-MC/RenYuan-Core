package ren.rymc.renyuancore.test;

import com.viaversion.viaversion.api.Via;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.RenYuanCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ResourceSender implements CommandExecutor, TabExecutor, Listener {

    private final FloodgateApi floodgateApi = FloodgateApi.getInstance();
    private static final HashMap<Player,PlayerResourceStatus> ResStatus = new HashMap<>();
    private final FileConfiguration config = RenYuanCore.getPlugin().getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = ((Player) sender);
        UUID uuid = player.getUniqueId();
        if(floodgateApi.isFloodgatePlayer(uuid)) {
            player.sendMessage(RenYuanCore.getPrefix() + "基岩版玩家不能使用此功能");
            RenYuanCore.sendMessage(player,"基岩版玩家不能使用此功能");
            return true;
        }
        if(Via.getAPI().getPlayerVersion(uuid) < 498){
            RenYuanCore.sendMessage(player,"你的游戏版本过低,不支持此功能");
            return true;
        }
        sendResource(player);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            return list;
        }
        return null;
    }

    @EventHandler
    public void PlayerResourcePackStatusEvent(PlayerResourcePackStatusEvent event){
        Player player = event.getPlayer();
        PlayerResourcePackStatusEvent.Status status = event.getStatus();
        if(floodgateApi.isFloodgatePlayer(player.getUniqueId())) return;
        if(status.equals(PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED)){
            ResStatus.put(player,PlayerResourceStatus.Java);
        }else if(status.equals(PlayerResourcePackStatusEvent.Status.DECLINED)){
            RenYuanCore.sendMessage(player, "服务器资源包加载被禁用", "请将服务器列表中§c编辑§f页面的§c服务器资源包§f选项设置为启用");
        }else if(status.equals(PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD)){
            RenYuanCore.sendMessage(player, "§e服务器资源包下载失败,可能的解决方法如下:", "1.换一条线路", "2.检查你是否能访问hub.fastgit.xyz", "3.询问管理");
        }
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(floodgateApi.isFloodgatePlayer(player.getUniqueId())) {
            ResStatus.put(player,PlayerResourceStatus.Bedrock);
            return;
        }
        ResStatus.put(player,PlayerResourceStatus.Normal);
        if(Via.getAPI().getPlayerVersion(player.getUniqueId()) < 498) return;
        sendResource(player);
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
        ResStatus.remove(player);
    }

    private void sendResource(Player player){
        String url = config.getString("TestFeature.ResourceUrl","https://hub.fastgit.xyz/lRENyaaa/RYServerTexture/releases/download/v1.1/Pack.zip");
        String hash = config.getString("TestFeature.ResourceHash","2fa9b5326e064b24499edcf9569a3c466bd739ae");
        player.setResourcePack(url,hash);
    }

    public static PlayerResourceStatus getPlayerResourceStatus(Player player){
        return ResStatus.get(player);
    }

}
