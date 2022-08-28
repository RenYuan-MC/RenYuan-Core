package ren.rymc.renyuancore.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.api.shop.Shop;

public class QuickShopClickExtendEvent extends Event {

    private final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Shop shop;

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public QuickShopClickExtendEvent(Player player, Shop shop) {
        this.player = player;
        this.shop = shop;
    }

    public Player getPlayer(){
        return player;
    }

    public Shop getShop(){
        return shop;
    }


}
