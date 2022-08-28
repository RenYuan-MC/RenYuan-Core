package ren.rymc.renyuancore.event.protect;

import com.bekvon.bukkit.residence.event.ResidenceCreationEvent;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ren.rymc.renyuancore.main.RenYuanCore;

public class ResCreateLimit implements Listener {

    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("领地圈地限制 已加载");
    }

    @EventHandler
    public void ResidenceCreationEvent(ResidenceCreationEvent event){
        Player player = event.getPlayer();
        if (player.isOp()) return;

        World world = event.getPhysicalArea().getWorld();
        FileConfiguration config = RenYuanCore.getAPI().getInstance().getConfig();

        for (String worldName : config.getStringList("protect.res-limit.worlds")){
            if (world.getName().equals(worldName)) {
                RenYuanCore.getAPI().sendMessage(player, "你不可以在这个世界圈地!");
                event.setCancelled(true);
            }
        }

    }
}
