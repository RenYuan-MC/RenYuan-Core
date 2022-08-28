package ren.rymc.renyuancore.event.resource;

import com.viaversion.viaversion.api.Via;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import ren.rymc.renyuancore.feature.resource.PlayerResourceStatus;
import ren.rymc.renyuancore.main.RenYuanCore;

import java.util.HashMap;

public class ResourceSender implements Listener {

    private static final HashMap<Player, PlayerResourceStatus> ResStatus = new HashMap<>();
    private static final FileConfiguration config = RenYuanCore.getAPI().getInstance().getConfig();

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("资源包发送控制 已加载");
    }

    @EventHandler
    public void PlayerResourcePackStatusEvent(PlayerResourcePackStatusEvent event){
        Player player = event.getPlayer();
        PlayerResourcePackStatusEvent.Status status = event.getStatus();
        if(FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) return;
        if(status.equals(PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED)){
            ResStatus.put(player,PlayerResourceStatus.Java);
        }else if(status.equals(PlayerResourcePackStatusEvent.Status.DECLINED)){
            RenYuanCore.getAPI().sendMessage(player, "服务器资源包加载被禁用", "请将服务器列表中§c编辑§f页面的§c服务器资源包§f选项设置为启用");
        }else if(status.equals(PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD)){
            RenYuanCore.getAPI().sendMessage(player, "§e服务器资源包下载失败,可能的解决方法如下:", "1.换一条线路", "2.检查你是否能访问hub.fastgit.xyz", "3.询问管理");
        }
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
            ResStatus.put(player,PlayerResourceStatus.Bedrock);
            return;
        }
        ResStatus.put(player,PlayerResourceStatus.Normal);
        if(RenYuanCore.getAPI().hasPlugin("ViaVersion") && Via.getAPI().getPlayerVersion(player.getUniqueId()) < 498) return;
        sendResource(player);
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
        ResStatus.remove(player);
    }

    public static void sendResource(Player player){
        String url = config.getString("resource.url","https://hub.fastgit.xyz/lRENyaaa/RYServerTexture/releases/download/v1.1/Pack.zip");
        String hash = config.getString("resource.hash","2fa9b5326e064b24499edcf9569a3c466bd739ae");
        player.setResourcePack(url,hash);
    }

    public static PlayerResourceStatus getPlayerResourceStatus(Player player){
        return ResStatus.get(player);
    }

}
