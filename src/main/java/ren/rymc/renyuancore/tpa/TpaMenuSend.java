package ren.rymc.renyuancore.tpa;

import ren.rymc.renyuancore.RenYuanCore;
import me.zimzaza4.LForm;
import me.zimzaza4.SimpleFormClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.jetbrains.annotations.NotNull;

public class TpaMenuSend implements Listener {

    private static final FileConfiguration config = RenYuanCore.getPlugin().getConfig();

    public static void sendTpaMenu(@NotNull Player player,@NotNull Player fromPlayer,@NotNull TeleportMode teleportMode) {
        if(FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())){
            String teleportMessage = String.format(config.getString("BedrockTpaMenu.Message.UnknownTpInfo","未知的传送方式,来自 %s"), fromPlayer.getName());
            switch (teleportMode){
                case Tpa:
                    teleportMessage = String.format(config.getString("BedrockTpaMenu.Message.TpaInfo","%s 想要让他传送到你这里"), fromPlayer.getName());
                    break;
                case TpaHere:
                    teleportMessage = String.format(config.getString("BedrockTpaMenu.Message.TpahereInfo","%s 想要让你传送到他那里"), fromPlayer.getName());
                    break;
            }
            FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
            SimpleForm.Builder tpaMenu = SimpleForm.builder()
                    .title(config.getString("BedrockTpaMenu.Form.Title","传送菜单"))
                    .content(teleportMessage)
                    .button(config.getString("BedrockTpaMenu.Form.AcceptButton","同意传送"), FormImage.Type.PATH, config.getString("BedrockTpaMenu.Form.AcceptIcon","textures/ui/realms_green_check.png"))
                    .button(config.getString("BedrockTpaMenu.Form.DenyButton","拒绝传送"), FormImage.Type.PATH, config.getString("BedrockTpaMenu.Form.DenyIcon","textures/ui/realms_red_x.png"));
            LForm.SimpleListener(player, tpaMenu, "RenYuan-Core_Tpa-Menu");
            floodgatePlayer.sendForm(tpaMenu);
        }
    }

    @EventHandler
    public void onTpaMenuClick(SimpleFormClickEvent event){
        Player player = event.getPlayer();
        if (event.getFormId().equals("RenYuan-Core_Tpa-Menu")){
            switch (event.getButtonID()){
                case 0:
                    Bukkit.dispatchCommand(player,config.getString("BedrockTpaMenu.AcceptCommand","tpaccept"));
                    break;
                case 1:
                    Bukkit.dispatchCommand(player,config.getString("BedrockTpaMenu.DenyCommand","tpdeny"));
                    break;
            }
        }
    }
}
