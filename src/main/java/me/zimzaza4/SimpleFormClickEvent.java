package me.zimzaza4;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.jetbrains.annotations.NotNull;

public class SimpleFormClickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final String title;
    private final String buttonName;
    private final int buttonID;
    private final SimpleFormResponse response;
    private final SimpleForm form;
    private final String formid;

    public SimpleFormClickEvent(Player player, String title, int id, String bottonname, SimpleForm sf, SimpleFormResponse res, String formid) {
        this.player = player;
        this.title = title;
        this.buttonID = id;
        this.buttonName = bottonname;
        this.response = res;
        this.form = sf;
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

    public String getButtonName() {
        return this.buttonName;
    }

    public String getTitle() {
        return this.title;
    }

    public int getButtonID() {
        return this.buttonID;
    }

    public String getFormId() {
        return this.formid;
    }

    public SimpleForm getForm() {
        return this.form;
    }

    public SimpleFormResponse getResponse() {
        return this.response;
    }
}
