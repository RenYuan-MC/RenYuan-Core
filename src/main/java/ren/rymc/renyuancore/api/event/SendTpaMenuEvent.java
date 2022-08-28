package ren.rymc.renyuancore.api.event;


import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ren.rymc.renyuancore.feature.bedrock.tpa.TeleportMode;

public class SendTpaMenuEvent extends Event implements Cancellable {

    private final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Player fromPlayer;
    private final TeleportMode teleportMode;
    private boolean cancelled;

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public SendTpaMenuEvent(Player player, Player fromPlayer, TeleportMode teleportMode) {
        this.player = player;
        this.fromPlayer = fromPlayer;
        this.teleportMode = teleportMode;
    }

    public Player getPlayer(){
        return player;
    }

    public Player getFromPlayer(){
        return fromPlayer;
    }

    public TeleportMode getTeleportMode(){
        return teleportMode;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
