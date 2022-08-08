package me.zimzaza4;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.jetbrains.annotations.NotNull;

public class CustomFormClickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final String title;
    private final CustomFormResponse res;
    private final String formid;

    public CustomFormClickEvent(Player player, String title, CustomFormResponse res, CustomForm form, String formid) {
        this.player = player;
        this.title = title;
        this.res = res;
        this.formid = formid;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getTitle() {
        return this.title;
    }

    public CustomFormResponse getResponse() {
        return this.res;
    }

    public String getFormID() {
        return this.formid;
    }
}
