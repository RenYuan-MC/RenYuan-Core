package ren.rymc.renyuancore.feature.bedrock.quickshop;

import me.zimzaza4.LForm;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.maxgamer.quickshop.api.shop.Shop;
import ren.rymc.renyuancore.api.QuickShopExtendAPI;
import ren.rymc.renyuancore.api.RenYuanAPI;
import ren.rymc.renyuancore.event.bedrock.quickshop.ShopListener;
import ren.rymc.renyuancore.main.RenYuanCore;

import java.util.UUID;

public class QuickShop implements QuickShopExtendAPI {

    private static final RenYuanAPI api = RenYuanCore.getAPI();
    private static final FileConfiguration config = api.getInstance().getConfig();
    private static final PluginManager pm = Bukkit.getPluginManager();

    static {
        // 如果基岩版菜单未启用或者未安装floodgate和QuickShop则不执行
        if (api.hasPlugin("QuickShop","floodgate") && config.getBoolean("bedrock.quickshop.enable",true)){
            // 注册事件
            pm.registerEvents(new ShopListener(),api.getInstance());
        }
    }

    public static void sendShopSettingMenu(Player player){

        UUID uuid = player.getUniqueId();
        FloodgateApi api = FloodgateApi.getInstance();

        if (!api.isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = api.getPlayer(uuid);

        SimpleForm.Builder menu = SimpleForm.builder()
                .title(config.getString("bedrock.quickshop.forms.setting.title", "商店设置"))
                .content(config.getString("bedrock.quickshop.forms.setting.content", "商店设置"))
                .button(config.getString("bedrock.quickshop.forms.setting.change-type", "切换商店状态"), FormImage.Type.PATH, config.getString("bedrock.quickshop.forms.setting.change-type-icon", "textures/ui/arrow_dark_right_stretch.png"))
                .button(config.getString("bedrock.quickshop.forms.setting.set-price", "修改商店价格"), FormImage.Type.PATH, config.getString("bedrock.quickshop.forms.setting.set-price-icon", "textures/ui/store_home_icon.png"))
                .button(config.getString("bedrock.quickshop.forms.setting.delete", "§c删除商店"), FormImage.Type.PATH, config.getString("bedrock.quickshop.forms.setting.delete-icon", "textures/ui/realms_red_x.png"));
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_QuickShop-Setting");
        floodgatePlayer.sendForm(menu);

    }

    public static void sendShopMenu(Player player, double price){

        UUID uuid = player.getUniqueId();
        FloodgateApi api = FloodgateApi.getInstance();

        if (!api.isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = api.getPlayer(uuid);

        CustomForm.Builder menu = CustomForm.builder()
                .title(config.getString("bedrock.quickshop.forms.main.title", "商店菜单"))
                .input(String.format(config.getString("bedrock.quickshop.forms.main.content", "请输入你要购买/出售的物品数量\n物品价格: %s 金币"), price), config.getString("bedrock.quickshop.forms.main.input","§7123"));
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_QuickShop-Main");
        floodgatePlayer.sendForm(menu);

    }

    public static void sendShopConfirmMenu(Player player){

        UUID uuid = player.getUniqueId();
        FloodgateApi api = FloodgateApi.getInstance();

        if (!api.isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = api.getPlayer(uuid);

        SimpleForm.Builder menu = SimpleForm.builder()
                .title(config.getString("bedrock.quickshop.forms.delete-confirm.title", "§l你确定?"))
                .content(config.getString("bedrock.quickshop.forms.delete-confirm.content", "删除商店"))
                .button(config.getString("bedrock.quickshop.forms.delete-confirm.confirm", "确定"), FormImage.Type.PATH, config.getString("bedrock.quickshop.forms.delete-confirm.confirm-icon","textures/ui/realms_green_check.png"));
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_QuickShop-Confirm");
        floodgatePlayer.sendForm(menu);

    }

    public static void sendShopPriceSetMenu(Player player){

        UUID uuid = player.getUniqueId();
        FloodgateApi api = FloodgateApi.getInstance();

        if (!api.isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = api.getPlayer(uuid);

        CustomForm.Builder menu = CustomForm.builder()
                .title(config.getString("bedrock.quickshop.forms.set-price.title", "商店菜单"))
                .input(config.getString("bedrock.quickshop.forms.set-price.content", "请输入你要修改的商店价格"), config.getString("bedrock.quickshop.forms.set-price.input", "§7123"));
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_QuickShop-PriceSet");
        floodgatePlayer.sendForm(menu);

    }

    public Shop getQuickShop(Block block){
        return ShopListener.getQuickShop(block);
    }

    public Boolean isQuickShopEmpty(Shop shop){
        return ShopListener.isQuickShopEmpty(shop);
    }



}
