package ren.rymc.renyuancore.bedrockmenu;

import me.zimzaza4.CustomFormClickEvent;
import me.zimzaza4.LForm;
import me.zimzaza4.SimpleFormClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.RenYuanCoreAPI;

public class MainMenu implements Listener, CommandExecutor {

    private void sendTeleportMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("传送")
                .input("请输入你要传送的玩家(不需要完整名称)", "&7RENaa");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_TeleportMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendTeleportHereMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("传送")
                .input("请输入你要传送的玩家(不需要完整名称)", "&7RENaa");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_TeleportHereMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendSquareSelectMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("领地选区(正方体)")
                .input("请输入领地选区半径", "&72");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_SquareSelectMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResEndCreateMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("创建领地")
                .input("请输入你要创建的领地名称", "&7abc123-_");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_ResEndCreateMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResPaddMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("添加领地信任玩家")
                .input("请输入你要添加的玩家(完整名称)", "&7BE_RENaaLR");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_ResPaddMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResPdelMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("删除领地信任玩家")
                .input("请输入你要删除的玩家(完整名称)", "&7BE_RENaaLR");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_ResPdelMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResExpandMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("扩展领地")
                .input("请面朝你要扩展的方向输入扩展大小", "&710");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_ResExpandMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResRemoveMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("删除领地")
                .input("请输入你要删除的领地名称(不填则是你所在的领地)", "&7abc123-_");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_ResRemoveMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResSetMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("设置领地公共权限")
                .input("请输入你要设置的领地名称(不填则是你所在的领地)", "&7abc123-_");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_ResSetMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResPsetMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("设置领地玩家权限")
                .input("请输入你要设置权限的玩家名称(完整名称)", "&7BE_RENaaLR");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_ResPsetMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResTeleportMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        CustomForm.Builder menu = CustomForm.builder()
                .title("领地传送")
                .input("请输入你要传送的领地(完整名称)", "&7abc123-_");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_ResTeleportMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendMainMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&e&l主菜单")
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
                .title("&l世界传送")
                .content("世界传送")
                .button("生存世界随机传送", FormImage.Type.PATH, "textures/blocks/grass_path_side.png")
                .button("地狱世界随机传送", FormImage.Type.PATH, "textures/blocks/netherrack.png")
                .button("末地世界传送", FormImage.Type.PATH, "textures/blocks/end_stone.png")
                .button("资源世界随机传送", FormImage.Type.PATH, "textures/blocks/emerald_block.png")
                .button("传送至主城", FormImage.Type.PATH, "textures/blocks/planks_oak.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_WorldTeleportMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendPlayerTeleportMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&l玩家传送")
                .content("玩家传送")
                .button("传送到别的玩家", FormImage.Type.PATH, "textures/ui/arrow_dark_right.png")
                .button("让别的玩家传送到这", FormImage.Type.PATH, "textures/ui/arrow_dark_left_stretch.png")
                .button("拒绝传送", FormImage.Type.PATH, "textures/ui/realms_red_x.png")
                .button("同意传送", FormImage.Type.PATH, "textures/ui/realms_green_check.png")
                .button("取消传送", FormImage.Type.PATH, "textures/blocks/barrier.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_PlayerTeleportMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendHomeMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&l家管理")
                .content("家管理")
                .button("传送到你的家", FormImage.Type.PATH, "textures/ui/arrow_dark_right_stretch.png")
                .button("将当前位置设置为家", FormImage.Type.PATH, "textures/blocks/planks_oak.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_HomeMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendSettingMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&l功能菜单")
                .content("功能菜单")
                .button("开关计分板", FormImage.Type.PATH, "textures/items/apple.png")
                .button("开关技能状态显示栏", FormImage.Type.PATH, "textures/items/apple_golden.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_SettingMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendPlayingMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&l玩法菜单")
                .content("玩法菜单")
                .button("获取粘液科技指南", FormImage.Type.PATH, "textures/items/book_enchanted.png")
                .button("打开技能菜单", FormImage.Type.PATH, "textures/items/diamond_axe.png")
                .button("签到", FormImage.Type.PATH, "textures/items/paper.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_PlayingMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResidenceMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&l领地菜单")
                .content("领地菜单")
                .button("领地创建菜单", FormImage.Type.PATH, "textures/items/clock_item.png")
                .button("领地管理菜单", FormImage.Type.PATH, "textures/ui/settings_glyph_color_2x.png")
                .button("领地传送", FormImage.Type.PATH, "textures/items/ender_pearl.png")
                .button("查看你周围的领地的信息", FormImage.Type.PATH, "textures/ui/infobulb.png")
                .button("查看你拥有权限的领地", FormImage.Type.PATH, "textures/ui/servers.png")
                .button("查看领地边界", FormImage.Type.PATH, "textures/blocks/barrier.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_ResidenceMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResCreateMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&l领地创建菜单")
                .content("领地创建菜单")
                .button("选区(正方体)", FormImage.Type.PATH, "textures/items/wood_hoe.png")
                .button("选区(自动)", FormImage.Type.PATH, "textures/items/stone_hoe.png")
                .button("创建领地", FormImage.Type.PATH, "textures/items/gold_hoe.png")
                .button("扩展领地", FormImage.Type.PATH, "textures/items/diamond_hoe.png")
                .button("删除领地", FormImage.Type.PATH, "textures/ui/realms_red_x.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_ResCreateMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResManagerMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&l领地管理菜单")
                .content("请站在你自己领地里使用")
                .button("添加信任玩家", FormImage.Type.PATH, "textures/ui/book_addpicture_hover.png")
                .button("删除信任玩家", FormImage.Type.PATH, "textures/ui/realms_red_x.png")
                .button("设置领地传送点", FormImage.Type.PATH, "textures/items/ender_pearl.png")
                .button("高级功能", FormImage.Type.PATH, "textures/items/diamond.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_ResManagerMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResConfirmMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&l你确定?")
                .content("删除后不可恢复")
                .button("确定", FormImage.Type.PATH, "textures/ui/realms_green_check.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_ResConfirmMenu");
        floodgatePlayer.sendForm(menu);
    }

    private void sendResExtraMenu(Player player){
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(player.getUniqueId());
        SimpleForm.Builder menu = SimpleForm.builder()
                .title("&l领地管理-高级功能").content("高级功能")
                .button("设置领地公共权限", FormImage.Type.PATH, "textures/ui/settings_glyph_color_2x.png")
                .button("设置领地玩家权限", FormImage.Type.PATH,  "textures/ui/settings_glyph_color_2x.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_ResExtraMenu");
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
    public void CustomFormClickEvent(CustomFormClickEvent event) {
        Player player = event.getPlayer();
        CustomFormResponse response = event.getResponse();
        if (response.getInput(0) == null) return;
        String input = response.getInput(0);

        switch (event.getFormID()) {
            case "RenYuan-BEMenu_TeleportMenu":
                Bukkit.dispatchCommand(player, "tpa " + input);
                break;
            case "RenYuan-BEMenu_TeleportHereMenu":
                Bukkit.dispatchCommand(player, "tpahere " + input);
                break;
            case "RenYuan-BEMenu_SquareSelectMenu":
                Bukkit.dispatchCommand(player, "res select " + input + " " + input + " " + input);
                break;
            case "RenYuan-BEMenu_ResEndCreateMenu":
                Bukkit.dispatchCommand(player, "res create " + input);
                break;
            case "RenYuan-BEMenu_ResTeleportMenu":
                Bukkit.dispatchCommand(player, "res tp " + input);
                break;
            case "RenYuan-BEMenu_ResPaddMenu":
                Bukkit.dispatchCommand(player, "res padd " + input);
                break;
            case "RenYuan-BEMenu_ResPdelMenu":
                Bukkit.dispatchCommand(player, "res pdel " + input);
                break;
            case "RenYuan-BEMenu_ResExpandMenu":
                Bukkit.dispatchCommand(player, "res expand " + input);
                break;
            case "RenYuan-BEMenu_ResRemoveMenu":
                Bukkit.dispatchCommand(player, "res remove " + input);
                sendResConfirmMenu(player);
                break;
            case "RenYuan-BEMenu_ResSetMenu":
                Bukkit.dispatchCommand(player, "res set " + input);
                break;
            case "RenYuan-BEMenu_ResPsetMenu":
                Bukkit.dispatchCommand(player, "res pset " + input);
                break;
        }
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
                    sendPlayerTeleportMenu(player);
                } else if (buttonID == 2) {
                    sendSettingMenu(player);
                } else if (buttonID == 3) {
                    sendPlayingMenu(player);
                } else if (buttonID == 4) {
                    sendHomeMenu(player);
                } else if (buttonID == 5) {
                    sendResidenceMenu(player);
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
            case "RenYuan-BEMenu_PlayerTeleportMenu":
                if (buttonID == 0) {
                    sendTeleportMenu(player);
                } else if (buttonID == 1) {
                    sendTeleportHereMenu(player);
                } else if (buttonID == 2) {
                    Bukkit.dispatchCommand(player, "tpdeny");
                } else if (buttonID == 3) {
                    Bukkit.dispatchCommand(player, "tpaccept");
                } else if (buttonID == 4) {
                    Bukkit.dispatchCommand(player, "tpacancel");
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
            case "RenYuan-BEMenu_ResidenceMenu":
                if (buttonID == 0) {
                    sendResCreateMenu(player);
                } else if (buttonID == 1) {
                    sendResManagerMenu(player);
                } else if (buttonID == 2) {
                    sendResTeleportMenu(player);
                } else if (buttonID == 3) {
                    Bukkit.dispatchCommand(player, "res info");
                } else if (buttonID == 4) {
                    Bukkit.dispatchCommand(player, "res list");
                } else if (buttonID == 5) {
                    Bukkit.dispatchCommand(player, "res show");
                }
                break;
            case "RenYuan-BEMenu_ResCreateMenu":
                if (buttonID == 0) {
                    sendSquareSelectMenu(player);
                } else if (buttonID == 1) {
                    Bukkit.dispatchCommand(player, "res select auto");
                } else if (buttonID == 2) {
                    sendResEndCreateMenu(player);
                } else if (buttonID == 3) {
                    sendResExpandMenu(player);
                } else if (buttonID == 4) {
                    sendResRemoveMenu(player);
                }
                break;
            case "RenYuan-BEMenu_ResManagerMenu":
                if (buttonID == 0) {
                    sendResPaddMenu(player);
                } else if (buttonID == 1) {
                    sendResPdelMenu(player);
                } else if (buttonID == 2) {
                    Bukkit.dispatchCommand(player, "res tpset");
                } else if (buttonID == 3) {
                    sendResExtraMenu(player);
                }
                break;
            case "RenYuan-BEMenu_ResConfirmMenu":
                Bukkit.dispatchCommand(player, "res confirm");
                break;
            case "RenYuan-BEMenu_ResExtraMenu":
                if (buttonID == 0) {
                    sendResSetMenu(player);
                } else if (buttonID == 1) {
                    sendResPsetMenu(player);
                }
                break;
        }
    }

}
