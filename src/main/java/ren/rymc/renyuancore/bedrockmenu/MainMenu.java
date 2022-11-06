package ren.rymc.renyuancore.bedrockmenu;

import me.zimzaza4.LForm;
import me.zimzaza4.SimpleFormClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.RenYuanCoreAPI;

public class MainMenu implements Listener, CommandExecutor {

    private void sendMainMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("§e§l主菜单")
                .content("主菜单")
                .button("世界传送菜单", FormImage.Type.PATH, "textures/ui/arrow_dark_right_stretch.png")
                .button("玩家传送菜单", FormImage.Type.PATH, "textures/items/ender_pearl.png")
                .button("功能菜单", FormImage.Type.PATH, "textures/ui/settings_glyph_color_2x.png")
                .button("玩法菜单", FormImage.Type.PATH, "textures/items/book_enchanted.png")
                .button("家管理", FormImage.Type.PATH, "textures/ui/store_home_icon.png")
                .button("领地菜单", FormImage.Type.PATH, "textures/items/clock_item.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_MainMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendWorldTeleportMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("§l世界传送")
                .content("世界传送")
                .button("生存世界随机传送", FormImage.Type.PATH, "textures/blocks/grass_path_side.png")
                .button("地狱世界随机传送", FormImage.Type.PATH, "textures/blocks/netherrack.png")
                .button("末地世界传送", FormImage.Type.PATH, "textures/blocks/end_stone.png")
                .button("资源世界随机传送", FormImage.Type.PATH, "textures/blocks/emerald_block.png")
                .button("传送至主城", FormImage.Type.PATH, "textures/blocks/planks_oak.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_WorldTeleportMenu");
        floodgatePlayer.sendForm(menu);
    }


    private void sendHomeMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("§l家管理")
                .content("家管理")
                .button("传送到你的家", FormImage.Type.PATH, "textures/ui/arrow_dark_right_stretch.png")
                .button("将当前位置设置为家", FormImage.Type.PATH, "textures/blocks/planks_oak.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_HomeMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendSettingMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("§l功能菜单")
                .content("功能菜单")
                .button("开关计分板", FormImage.Type.PATH, "textures/items/apple.png")
                .button("开关技能状态显示栏", FormImage.Type.PATH, "textures/items/apple_golden.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_SettingMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendPlayingMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("§l玩法菜单")
                .content("玩法菜单")
                .button("获取粘液科技指南", FormImage.Type.PATH, "textures/items/book_enchanted.png")
                .button("打开技能菜单", FormImage.Type.PATH, "textures/items/diamond_axe.png")
                .button("签到", FormImage.Type.PATH, "textures/items/paper.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_PlayingMenu");
        floodgatePlayer.sendForm(menu);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!(sender instanceof Player)) {
            RenYuanCoreAPI.sendMessage(sender, RenYuanCoreAPI.getConfig().getString("Message.PlayerCommand","仅玩家可执行该命令"));
            return true;
        }
        Player player = ((Player) sender);
        if(!FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) return true;
        sendMainMenu(player);
        return true;
    }



    @EventHandler
    public void SimpleFormClickEvent(SimpleFormClickEvent event) {
        Player player = event.getPlayer();
        int buttonID = event.getButtonID();
        String formId = event.getFormId();
        switch (formId) {
            case "RenYuan-BEMenu_MainMenu":
                if (buttonID == 0) {
                    sendWorldTeleportMenu(player);
                } else if (buttonID == 1) {
                    Bukkit.dispatchCommand(player,"tpaui");
                } else if (buttonID == 2) {
                    sendSettingMenu(player);
                } else if (buttonID == 3) {
                    sendPlayingMenu(player);
                } else if (buttonID == 4) {
                    sendHomeMenu(player);
                } else if (buttonID == 5) {
                    Bukkit.dispatchCommand(player,"rform");
                }
                break;
            case "RenYuan-BEMenu_WorldTeleportMenu":
                if (buttonID == 0) {
                    Bukkit.dispatchCommand(player, "rtp world world");
                } else if (buttonID == 1) {
                    Bukkit.dispatchCommand(player, "rtp world world_nether");
                } else if (buttonID == 2) {
                    Bukkit.dispatchCommand(player, "tpend");
                } else if (buttonID == 3) {
                    Bukkit.dispatchCommand(player, "rtp world otd_dungeon");
                } else if (buttonID == 4) {
                    Bukkit.dispatchCommand(player, "spawn");
                }
                break;
            case "RenYuan-BEMenu_HomeMenu":
                if (buttonID == 0) {
                    Bukkit.dispatchCommand(player, "home home");
                } else if (buttonID == 1) {
                    Bukkit.dispatchCommand(player, "sethome home");
                }
                break;
            case "RenYuan-BEMenu_SettingMenu":
                if (buttonID == 0) {
                    Bukkit.dispatchCommand(player, "sb toggle");
                } else if (buttonID == 1) {
                    Bukkit.dispatchCommand(player, "abtoggle");
                }
                break;
            case "RenYuan-BEMenu_PlayingMenu":
                if (buttonID == 0) {
                    Bukkit.dispatchCommand(player, "sf guide");
                } else if (buttonID == 1) {
                    Bukkit.dispatchCommand(player, "skills");
                } else if (buttonID == 2) {
                    Bukkit.dispatchCommand(player, "signin click");
                }
                break;
        }
    }

}
