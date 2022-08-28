package ren.rymc.renyuancore.api;

import org.bukkit.block.Block;
import org.maxgamer.quickshop.api.shop.Shop;

/**
 * 用于扩展QuickShop的API功能
 * 没办法,QuickShop本体没这些玩意
 */
public interface QuickShopExtendAPI {

    /**
     * 用于从方块位置获取商店
     * 如果不是商店则返回null
     *
     * @param block 商店对应的方块,可为告示牌
     *
     */
    Shop getQuickShop(Block block);

    /**
     * 用于检测商店是否为空
     *
     * @param shop QuickShop商店
     *
     */
    Boolean isQuickShopEmpty(Shop shop);

}
