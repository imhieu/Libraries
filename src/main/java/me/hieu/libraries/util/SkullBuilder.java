package me.hieu.libraries.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Le Thanh Hieu
 * Date: 17/10/2024
 */

public class SkullBuilder {

    private final ItemStack stack;
    private final SkullMeta meta;

    public SkullBuilder(String name) {
        this.stack = new ItemStack(Material.PLAYER_HEAD, 1);
        this.meta = (SkullMeta) stack.getItemMeta();
        this.meta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
    }

    public SkullBuilder name(String name) {
        this.meta.setDisplayName(CC.translate(name));
        return this;
    }

    public SkullBuilder lore(List<String> lore) {
        this.meta.setLore(CC.translate(lore));
        return this;
    }

    public SkullBuilder setLore(String[] lore) {
        this.meta.setLore(Arrays.asList(lore));
        return this;
    }

    public SkullBuilder addLore(String input) {
        List<String> lore = this.meta.getLore();
        if(lore == null) lore = new ArrayList<>();
        lore.add(input);
        this.meta.setLore(lore);
        return this;
    }

    private void uM() {
        this.stack.setItemMeta(this.meta);
    }

    public ItemStack build() {
        this.uM();
        return this.stack;
    }

}
