package ren.rymc.renyuancore.feature.bedrock;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.api.shop.Shop;
import ren.rymc.renyuancore.api.BedrockMenuAPI;
import ren.rymc.renyuancore.event.bedrock.quickshop.ShopListener;
import ren.rymc.renyuancore.feature.bedrock.quickshop.QuickShop;
import ren.rymc.renyuancore.feature.bedrock.respawn.Respawn;
import ren.rymc.renyuancore.feature.bedrock.tpa.TeleportMode;
import ren.rymc.renyuancore.feature.bedrock.tpa.Tpa;

public class Bedrock implements BedrockMenuAPI {

    public void sendBedrockTpaMenu(@NotNull Player player, @NotNull Player fromPlayer, @NotNull TeleportMode teleportMode) {
        Tpa.sendTpaMenu(player,fromPlayer,teleportMode);
    }

    public void sendShopSettingMenu(@NotNull Player player, @NotNull Shop shop){
        ShopListener.addPlayerStorage(player,shop);
        QuickShop.sendShopSettingMenu(player);
    }

    public void sendShopPriceSetMenu(@NotNull Player player, @NotNull Shop shop){
        ShopListener.addPlayerStorage(player,shop);
        QuickShop.sendShopPriceSetMenu(player);
    }

    public void sendShopConfirmMenu(@NotNull Player player, @NotNull Shop shop){
        ShopListener.addPlayerStorage(player,shop);
        QuickShop.sendShopConfirmMenu(player);
    }

    public void sendRespawnMenu(@NotNull Player player) {
        Respawn.sendRespawnMenu(player);
    }

}
