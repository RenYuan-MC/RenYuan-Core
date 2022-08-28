package ren.rymc.renyuancore.event.bedrock.tpa;

import me.zimzaza4.SimpleFormClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ren.rymc.renyuancore.api.event.ClickTpaMenuEvent;
import ren.rymc.renyuancore.main.RenYuanCore;

public class MenuListener implements Listener {

    private static final RenYuanCore plugin = RenYuanCore.getAPI().getInstance();

    static {
        plugin.getLogger().info("基岩版Tpa菜单监听 已加载");
    }

    @EventHandler
    public void onTpaMenuClick(SimpleFormClickEvent event) {

        Player player = event.getPlayer();

        // 菜单ID检查
        if (!event.getFormId().equals("RenYuan-Core_Tpa-Menu")) return;

        int buttonID = event.getButtonID();

        // 触发事件
        ClickTpaMenuEvent clickEvent = new ClickTpaMenuEvent(player, buttonID);
        // 如果事件被取消则停止执行
        if (clickEvent.isCancelled()) return;

        // 按钮ID为0则同意，1则拒绝
        if (buttonID == 0) {
            Bukkit.dispatchCommand(player, plugin.getConfig().getString("bedrock.tpa.accept", "tpaccept"));
        } else if (buttonID == 1) {
            Bukkit.dispatchCommand(player, plugin.getConfig().getString("bedrock.tpa.deny", "tpdeny"));
        }

    }
}
