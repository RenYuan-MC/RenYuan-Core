package ren.rymc.renyuancore.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.api.shop.Shop;
import ren.rymc.renyuancore.feature.bedrock.tpa.TeleportMode;


/**
 * 基岩版菜单API
 * 可用于发送RenYuan-Core中的菜单
 */
public interface BedrockMenuAPI {

    /**
     * 发送Tpa菜单
     *
     * @param player 收到Tpa请求的玩家
     * @param fromPlayer 发送Tpa请求的玩家
     * @param teleportMode Tp的类型,可为Tpa, TpaHere, Unknown
     *
     */
    void sendBedrockTpaMenu(@NotNull Player player, @NotNull Player fromPlayer, @NotNull TeleportMode teleportMode);


    /**
     * 发送QuickShop商店设置菜单
     *
     * @param player 收到菜单的玩家
     * @param shop 设置的商店
     *
     */
    void sendShopSettingMenu(@NotNull Player player, @NotNull Shop shop);

    /**
     * 发送QuickShop商店价格设置菜单
     *
     * @param player 收到菜单的玩家
     * @param shop 设置的商店
     *
     */
    void sendShopPriceSetMenu(@NotNull Player player, @NotNull Shop shop);

    /**
     * 发送QuickShop商店删除确认菜单
     *
     * @param player 收到菜单的玩家
     * @param shop 删除的商店
     *
     */
    void sendShopConfirmMenu(@NotNull Player player, @NotNull Shop shop);

    /**
     * 发送死亡重生菜单
     *
     * @param player 收到菜单的玩家
     *
     */
    void sendRespawnMenu(@NotNull Player player);


}
