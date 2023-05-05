package ltd.rymc.survival.core.protect;

import com.bekvon.bukkit.residence.event.ResidenceCreationEvent;
import ltd.rymc.survival.core.RenYuanCoreAPI;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResCreateLimit implements Listener {

    @EventHandler
    public void ResidenceCreationEvent(ResidenceCreationEvent event){
        Player player = event.getPlayer();
        if (player.hasPermission("renyuancore.admin")) return;

        World world = event.getPhysicalArea().getWorld();
        FileConfiguration config = RenYuanCoreAPI.getPlugin().getConfig();

        for (String worldName : config.getStringList("ResCreateLimit")){
            if (world.getName().equals(worldName)) {
                RenYuanCoreAPI.sendMessage(player, "你不可以在这个世界圈地!");
                event.setCancelled(true);
            }
        }

    }
}
