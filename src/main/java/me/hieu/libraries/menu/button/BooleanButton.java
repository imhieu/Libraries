package me.hieu.libraries.menu.button;

import me.hieu.libraries.menu.Button;
import me.hieu.libraries.menu.callback.Callback;
import me.hieu.libraries.util.CC;
import me.hieu.libraries.util.ItemBuilder;
import me.hieu.libraries.util.TaskUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

public class BooleanButton extends Button {

    private final boolean confirm;
    private final Callback<Boolean> callback;

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        if (this.confirm){
            lore.add(CC.translate("&aClick here to confirm"));
            lore.add(CC.translate("&athe procedure action."));
        } else {
            lore.add(CC.translate("&cClick here to cancel"));
            lore.add(CC.translate("&cthe procedure action."));
        }
        return new ItemBuilder(Material.LEGACY_WOOL).name(this.confirm ? CC.translate("&a&lConfirm") : CC.translate("&c&lCancel")).lore(lore).durability(this.confirm ? (byte) 5 : 14).build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (this.confirm) {
            playSuccess(player);
        } else {
            playFail(player);
        }
        TaskUtil.runTask(player::closeInventory);
        this.callback.callback(this.confirm);
    }

    @ConstructorProperties(value={"confirm", "callback"})
    public BooleanButton(boolean confirm, Callback<Boolean> callback) {
        this.confirm = confirm;
        this.callback = callback;
    }
}

