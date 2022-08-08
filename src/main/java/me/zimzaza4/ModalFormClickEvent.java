package me.zimzaza4;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.geysermc.cumulus.ModalForm;
import org.geysermc.cumulus.response.ModalFormResponse;
import org.jetbrains.annotations.NotNull;

public class ModalFormClickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final String buttonName;
    private final int buttonID;
    private final ModalFormResponse response;
    private final ModalForm form;
    private final String formid;

    public ModalFormClickEvent(Player player, int id, String bottonname, ModalForm sf, ModalFormResponse res, String FormId) {
        this.player = player;
        this.buttonID = id;
        this.buttonName = bottonname;
        this.response = res;
        this.form = sf;
        this.formid = FormId;
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

    public int getButtonID() {
        return this.buttonID;
    }

    public String getFormId() {
        return this.formid;
    }

    public ModalForm getForm() {
        return this.form;
    }

    public ModalFormResponse getResponse() {
        return this.response;
    }
}
