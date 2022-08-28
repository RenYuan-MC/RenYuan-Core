package ren.rymc.renyuancore.event.bedrock.respawn;

import me.zimzaza4.SimpleFormClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ren.rymc.renyuancore.main.RenYuanCore;

public class MenuListener implements Listener {
    @EventHandler
    public void SimpleFormClickEvent(SimpleFormClickEvent event) {
        FileConfiguration config = RenYuanCore.getAPI().getInstance().getConfig();
        Player player = event.getPlayer();
        String formId = event.getFormId();
        if ("RenYuan-BEMenu_Respawn-menu".equals(formId)) {
            int buttonID = event.getButtonID();
            if (buttonID == 0) {
                Bukkit.dispatchCommand(player,config.getString("bedrock.respawn.commands.back","back"));
            } else if (buttonID == 1) {
                Bukkit.dispatchCommand(player,config.getString("bedrock.respawn.commands.home","home"));
            }
        }
    }
}
