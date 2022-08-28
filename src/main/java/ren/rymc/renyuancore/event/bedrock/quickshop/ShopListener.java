package ren.rymc.renyuancore.event.bedrock.quickshop;

import me.zimzaza4.CustomFormClickEvent;
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
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.api.QuickShopAPI;
import org.maxgamer.quickshop.api.event.ShopClickEvent;
import org.maxgamer.quickshop.api.shop.Shop;
import org.maxgamer.quickshop.api.shop.ShopType;
import ren.rymc.renyuancore.api.event.QuickShopClickExtendEvent;
import ren.rymc.renyuancore.feature.bedrock.quickshop.QuickShop;
import ren.rymc.renyuancore.main.RenYuanCore;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ShopListener implements Listener {

    /**
     * 用于排除无效的商店点击
     * 由于QuickShop在冒险模式下的谜之判定
     * 所以在冒险模式下格外有用
     */
    private Boolean shopClickStatus = false;

    /**
     * 用于在商店设置菜单里储存玩家对应的商店
     */
    private static final HashMap<Player, Shop> playerStorage = new HashMap<>();


    private final FloodgateApi FgAPI = FloodgateApi.getInstance();
    private static final QuickShopAPI QsAPI = (QuickShopAPI) Bukkit.getPluginManager().getPlugin("QuickShop");


    static {
        RenYuanCore.getAPI().getInstance().getLogger().info("基岩版QuickShop菜单 已加载");
    }

    /**
     * 一般情况下监听点击商店的方式
     * 监听冒险模式点击请往下翻找到 PlayerAnimationEvent
     */
    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerInteractEvent(PlayerInteractEvent event) {

        // 获取玩家和UUID
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        // 获取点击的方块并且保证方块不为空
        Block block = event.getClickedBlock();
        if (block == null) return;

        // 获取点击的方块是否为商店
        Shop shop = getQuickShop(block);
        if (shop == null) return;

        // 获取点击方式
        Action action = event.getAction();

        // 如果不是购买商店物品
        if (!shopClickStatus) {

            // 排除非基岩版玩家
            if (!FgAPI.isFloodgatePlayer(uuid)) return;

            // 如果玩家是潜行状态则打开商店物品浏览
            if (player.isSneaking() && action.equals(Action.LEFT_CLICK_BLOCK)){
                shop.openPreview(player);
                return;
            }

            // 排除非右键点击
            if (!action.equals(Action.RIGHT_CLICK_BLOCK)) return;

            // 排除箱子和陷阱箱子,不排除的话可能就打不开了
            if (block.getType().equals(Material.CHEST) || block.getType().equals(Material.TRAPPED_CHEST)) return;

            // 如果玩家是OP或者是这个商店的拥有者则弹出商店设置菜单
            if (player.isOp() || shop.getOwner().equals(player.getUniqueId())) {
                playerStorage.put(player,shop);
                QuickShop.sendShopSettingMenu(player);
            }
            return;
        }
        // 重置点击状态
        shopClickStatus = false;

        // 触发事件
        new QuickShopClickExtendEvent(player,shop);

        // 排除非基岩版玩家
        if (!FgAPI.isFloodgatePlayer(uuid)) return;

        // 如果商店是空的则跳过
        if (isQuickShopEmpty(shop)) return;

        // 发送商店购买菜单
        QuickShop.sendShopMenu(player,shop.getPrice());

    }

    /**
     * 监听冒险模式下点击商店
     * 这个其实是监听动作,所以其实你靠近商店丢物品也会触发
     * 由于冒险模式下右键是正常的,所以这里没有关于商店设置的逻辑
     */
    @EventHandler( priority = EventPriority.HIGHEST )
    public void PlayerAnimationEvent(PlayerAnimationEvent event) {

        // 获取玩家并且排除非冒险模式的玩家
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.ADVENTURE)) return;

        // 获取玩家UUID并且排除基岩版玩家
        UUID uuid = player.getUniqueId();
        if (!FgAPI.isFloodgatePlayer(uuid)) return;

        // 获取目标方块,从QuickShop代码里抄的
        Block block = player.getTargetBlock(null, 5);

        // 获取点击的方块是否为商店
        Shop shop = getQuickShop(block);
        if (shop == null) return;

        // 如果不是购买商店物品
        if (!shopClickStatus) {
            // 排除箱子和陷阱箱子,不排除的话可能就打不开了 并且 如果 玩家正在潜行 则打开商店物品浏览
            if (player.isSneaking() && (!block.getType().equals(Material.CHEST) || !block.getType().equals(Material.TRAPPED_CHEST))){
                shop.openPreview(player);
            }
            return;
        }
        // 重置点击状态
        shopClickStatus = false;

        // 触发事件
        new QuickShopClickExtendEvent(player,shop);

        // 发送商店购买菜单
        QuickShop.sendShopMenu(player,shop.getPrice());

    }

    /**
     * 用于排除无效的商店点击
     * 由于QuickShop在冒险模式下的谜之判定
     * 所以在冒险模式下格外有用
     */
    @EventHandler
    public void ShopClickEvent(ShopClickEvent event) {
        shopClickStatus = true;
    }

    /**
     * 商店购买菜单, 商店价格设置菜单
     */
    @EventHandler
    public void CustomFormClickEvent(CustomFormClickEvent event) {
        // 获取玩家
        Player player = event.getPlayer();

        // 保证输入的内容不为空
        String input = event.getResponse().getInput(0);
        if (input == null) return;

        // 获取基岩版表单ID
        String formID = event.getFormID();

        // 商店购买菜单
        if (formID.equals("RenYuan-BEMenu_QuickShop-Main")) {

            // 直接在聊天框输出玩家输入的内容,由QuickShop处理信息
            player.chat(input);

        // 商店价格设置菜单
        } else if (formID.equals("RenYuan-BEMenu_QuickShop-PriceSet")) {

            // 获取玩家对应的商店
            Shop shop = playerStorage.get(player);

            // 如果为空则跳过
            if (shop == null) return;

            // 如果这个玩家是OP或者是这个商店的拥有者
            if (player.isOp() || shop.getOwner().equals(player.getUniqueId())) {
                // 防止输入的不是数字
                try {
                    // 设置商店价格
                    shop.setPrice(Double.parseDouble(input));
                } catch (NumberFormatException ignored) {
                }
            }
        }
    }

    /**
     * 商店设置菜单, 删除商店前的确认菜单
     */
    @EventHandler
    public void SimpleFormClickEvent(SimpleFormClickEvent event) {
        // 获取玩家
        Player player = event.getPlayer();

        // 获取基岩版表单ID
        String formId = event.getFormId();

        // 商店设置菜单
        if (formId.equals("RenYuan-BEMenu_QuickShop-Setting")) {

            // 获取基岩版表单按钮ID
            int buttonID = event.getButtonID();

            // 0是切换商店模式，1是设置商店价格，2是删除商店
            if (buttonID == 0) {

                // 获取玩家对应的商店
                Shop shop = playerStorage.get(player);

                // 如果为空则跳过
                if (shop == null) return;

                // 如果这个玩家是OP或者是这个商店的拥有者
                if (player.isOp() || shop.getOwner().equals(player.getUniqueId())) {
                    // 如果是收购商店则设置为出售，反之亦然
                    shop.setShopType(shop.isBuying() ? ShopType.SELLING : ShopType.BUYING);
                }

            } else if (buttonID == 1) {

                // 发送商店价格设置菜单
                QuickShop.sendShopPriceSetMenu(player);

            } else if (buttonID == 2) {

                // 发送删除商店前的确认菜单
                QuickShop.sendShopConfirmMenu(player);

            }

        // 删除商店前的确认菜单
        } else if (formId.equals("RenYuan-BEMenu_QuickShop-Confirm")) {

            // 获取玩家对应的商店
            Shop shop = playerStorage.get(player);

            // 如果为空则跳过
            if (shop == null) return;

            // 如果这个玩家是OP或者是这个商店的拥有者
            if (player.isOp() || shop.getOwner().equals(player.getUniqueId())) {
                // 删除商店
                shop.delete();
            }
        }
    }

    /**
     * 用于从方块位置获取商店
     * 支持从告示牌获取
     */
    public static Shop getQuickShop(Block block){
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

    /**
     * 用于检测商店是否为空
     */
    public static Boolean isQuickShopEmpty(Shop shop){
        if ( shop == null ) return false;
        if ( shop.isUnlimited() ) return false;
        Inventory shopInventory = ((Chest) shop.getLocation().getBlock().getState()).getBlockInventory();
        if (shopInventory.isEmpty()) return true;
        for (ItemStack item : shopInventory.getContents()){
            if ( item != null && item.getType() == shop.getItem().getType() && String.valueOf(item.getItemMeta()).equals(String.valueOf(shop.getItem().getItemMeta()))) return false;
        }
        return true;
    }

    public static void addPlayerStorage(@NotNull Player player, @NotNull Shop shop){
        playerStorage.put(player,shop);
    }
}
