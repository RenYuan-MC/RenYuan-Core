package ren.rymc.renyuancore.bedrockmenu;

import me.zimzaza4.CustomFormClickEvent;
import me.zimzaza4.LForm;
import me.zimzaza4.SimpleFormClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.maxgamer.quickshop.api.QuickShopAPI;
import org.maxgamer.quickshop.api.event.ShopClickEvent;
import org.maxgamer.quickshop.api.shop.Shop;
import org.maxgamer.quickshop.api.shop.ShopType;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class QuickShopMenu implements Listener {

    private Boolean shopClickStatus = false;
    private final HashMap<Player, Shop> playerStorage = new HashMap<>();
    private final FloodgateApi api = FloodgateApi.getInstance();
    private final QuickShopAPI QsAPI = (QuickShopAPI) Bukkit.getPluginManager().getPlugin("QuickShop");

    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerInteractEvent(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        UUID uuid = player.getUniqueId();


        Block block = event.getClickedBlock();
        if (block == null) return;

        Shop shop = getQuickShop(block);
        if (shop == null) return;

        Action action = event.getAction();

        if (!shopClickStatus) {
            if (!api.isFloodgatePlayer(uuid)) return;
            if (action.equals(Action.RIGHT_CLICK_BLOCK)){
                if (block.getType().equals(Material.CHEST) || block.getType().equals(Material.TRAPPED_CHEST)) return;

                if (player.isOp() || shop.getOwner().equals(player.getUniqueId())) {
                    playerStorage.put(player,shop);
                    BEQuickShopSetting(player);
                }

            }
            return;
        }
        shopClickStatus = false;

        if (!api.isFloodgatePlayer(uuid)) return;
        if(action.equals(Action.LEFT_CLICK_BLOCK)){

            if (player.isSneaking()){
                shop.openPreview(player);
                return;
            }

            if (isQuickShopEmpty(shop)) return;

            BEQuickShopMain(player,shop.getPrice());

        }
    }
    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerAnimationEvent(PlayerAnimationEvent event) {



        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.ADVENTURE)) return;

        UUID uuid = player.getUniqueId();
        if (!api.isFloodgatePlayer(uuid)) return;

        Block block = player.getTargetBlock(null, 5);

        Shop shop = getQuickShop(block);
        if (shop == null) return;

        if (!shopClickStatus) {
            if (player.isSneaking() && (!block.getType().equals(Material.CHEST) || !block.getType().equals(Material.TRAPPED_CHEST))){
                shop.openPreview(player);
            }
            return;
        }
        shopClickStatus = false;

        BEQuickShopMain(player,shop.getPrice());

    }

    @EventHandler
    public void ShopClickEvent(ShopClickEvent event) {
        shopClickStatus = true;
    }

    private void BEQuickShopSetting(Player player){

        UUID uuid = player.getUniqueId();
        if (!api.isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = api.getPlayer(uuid);

        SimpleForm.Builder menu = SimpleForm.builder()
                .title("商店设置")
                .content("商店设置")
                .button("切换商店状态", FormImage.Type.PATH, "textures/ui/arrow_dark_right_stretch.png")
                .button("修改商店价格", FormImage.Type.PATH, "textures/ui/store_home_icon.png")
                .button("§c删除商店", FormImage.Type.PATH, "textures/ui/realms_red_x.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_QuickShop-Setting");
        floodgatePlayer.sendForm(menu);

    }

    private void BEQuickShopMain(Player player, double price){

        UUID uuid = player.getUniqueId();
        if (!api.isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = api.getPlayer(uuid);

        CustomForm.Builder menu = CustomForm.builder()
                .title("商店菜单")
                .input("请输入你要购买/出售的物品数量\n物品价格:"+ price +"金币", "§7123");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_QuickShop-Main");
        floodgatePlayer.sendForm(menu);

    }

    private void BEQuickShopConfirm(Player player){

        UUID uuid = player.getUniqueId();
        if (!api.isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = api.getPlayer(uuid);

        SimpleForm.Builder menu = SimpleForm.builder()
                .title("§l你确定?")
                .content("删除商店")
                .button("确定", FormImage.Type.PATH, "textures/ui/realms_green_check.png");
        LForm.SimpleListener(player, menu, "RenYuan-BEMenu_QuickShop-Confirm");
        floodgatePlayer.sendForm(menu);

    }

    private void BEQuickShopPriceSet(Player player){

        UUID uuid = player.getUniqueId();
        if (!api.isFloodgatePlayer(uuid)) return;
        FloodgatePlayer floodgatePlayer = api.getPlayer(uuid);

        CustomForm.Builder menu = CustomForm.builder()
                .title("商店菜单")
                .input("请输入你要修改的商店价格", "§7123");
        LForm.CustomListener(player, menu, "RenYuan-BEMenu_QuickShop-PriceSet");
        floodgatePlayer.sendForm(menu);

    }

    @EventHandler
    public void CustomFormClickEvent(CustomFormClickEvent event) {
        Player player = event.getPlayer();

        String input = event.getResponse().getInput(0);
        if (input == null) return;

        String formID = event.getFormID();
        if ("RenYuan-BEMenu_QuickShop-Main".equals(formID)) {
            player.chat(input);
        } else if ("RenYuan-BEMenu_QuickShop-PriceSet".equals(formID)) {
            Shop shop = playerStorage.get(player);
            if (shop == null) return;
            if (player.isOp() || shop.getOwner().equals(player.getUniqueId())) {
                try {
                    shop.setPrice(Double.parseDouble(input));
                } catch (NumberFormatException ignored) {
                }
            }
        }
    }

    @EventHandler
    public void SimpleFormClickEvent(SimpleFormClickEvent event) {
        Player player = event.getPlayer();

        String formId = event.getFormId();
        if ("RenYuan-BEMenu_QuickShop-Setting".equals(formId)) {
            int buttonID = event.getButtonID();
            if (buttonID == 0) {
                Shop shop = playerStorage.get(player);
                if (shop == null) return;
                if (player.isOp() || shop.getOwner().equals(player.getUniqueId())) {
                    if (shop.isBuying()) {
                        shop.setShopType(ShopType.SELLING);
                    } else {
                        shop.setShopType(ShopType.BUYING);
                    }
                }
            } else if (buttonID == 1) {
                BEQuickShopPriceSet(player);
            } else if (buttonID == 2) {
                BEQuickShopConfirm(player);
            }
        } else if ("RenYuan-BEMenu_QuickShop-Confirm".equals(formId)) {
            Shop shop = playerStorage.get(player);
            if (shop == null) return;
            if (player.isOp() || shop.getOwner().equals(player.getUniqueId())) {
                shop.delete();
            }
        }
    }

    private Shop getQuickShop(Block block){
        if(block != null) {
            Shop shop = Objects.requireNonNull(QsAPI).getShopManager().getShop(block.getLocation());
            if(shop != null){
                return shop;
            }
            BlockData Drops = block.getBlockData();
            if(Drops instanceof Directional) {
                return QsAPI.getShopManager().getShop(block.getRelative(((Directional) Drops).getFacing(), -1).getLocation());
            }
        }
        return null;
    }
    private Boolean isQuickShopEmpty(Shop shop){
        if ( shop == null ) return false;
        if ( shop.isUnlimited() ) return false;
        Inventory shopInventory = ((Chest) shop.getLocation().getBlock().getState()).getBlockInventory();
        if (shopInventory.isEmpty()) return true;
        for (ItemStack item : shopInventory.getContents()){
            if ( item != null && item.getType() == shop.getItem().getType() && String.valueOf(item.getItemMeta()).equals(String.valueOf(shop.getItem().getItemMeta()))) return false;
        }
        return true;
    }

}