package me.zimzaza4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.ModalForm.Builder;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.cumulus.response.ModalFormResponse;
import org.geysermc.cumulus.response.SimpleFormResponse;

public class LForm {

    public static Builder ModalListener(Player p, Builder good, String formid) {
        return good.responseHandler((form, r) -> {
            ModalFormResponse response = form.parseResponse(r);
            if (response.isCorrect()) {
                ModalFormClickEvent event = new ModalFormClickEvent(p, response.getClickedButtonId(), response.getClickedButtonText(), form, response, formid);
                Bukkit.getPluginManager().callEvent(event);
            }
        });
    }

    public static org.geysermc.cumulus.SimpleForm.Builder SimpleListener(Player p, org.geysermc.cumulus.SimpleForm.Builder good, String formid) {
        return good.responseHandler((form, r) -> {
            SimpleFormResponse response = form.parseResponse(r);
            if (response.isCorrect()) {
                SimpleFormClickEvent event = new SimpleFormClickEvent(p, form.getTitle(), response.getClickedButtonId(), Objects.requireNonNull(response.getClickedButton()).getText(), form, response, formid);
                Bukkit.getPluginManager().callEvent(event);
            }
        });
    }

    public static org.geysermc.cumulus.CustomForm.Builder CustomListener(Player p, org.geysermc.cumulus.CustomForm.Builder good, String formid) {
        return good.responseHandler((form, r) -> {
            CustomFormResponse response = form.parseResponse(r);
            if (response.isCorrect()) {
                CustomFormClickEvent event = new CustomFormClickEvent(p, form.getTitle(), response, form, formid);
                Bukkit.getPluginManager().callEvent(event);
            }
        });
    }

    public static List<String> NewStrings() {
        return new ArrayList<>();
    }

    public static String[] setStringArray(List<String> list) {
        return list.toArray(new String[0]);
    }
}
