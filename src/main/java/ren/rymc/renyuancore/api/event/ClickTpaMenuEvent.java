package ren.rymc.renyuancore.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ClickTpaMenuEvent extends Event implements Cancellable {

    private final HandlerList handlers = new HandlerList();
    private final Player player;
    private final int buttonID;
    private boolean cancelled;

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public ClickTpaMenuEvent(Player player, int buttonID) {
        this.player = player;
        this.buttonID = buttonID;
    }

    public Player getPlayer(){
        return player;
    }

    public int getRawButtonID(){
        return buttonID;
    }

    public boolean isAccepted(){
        return buttonID == 0;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
