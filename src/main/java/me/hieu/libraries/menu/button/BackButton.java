package me.hieu.libraries.menu.button;

import lombok.AllArgsConstructor;
import me.hieu.libraries.menu.Button;
import me.hieu.libraries.menu.Menu;
import me.hieu.libraries.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class BackButton extends Button {

	private Menu back;

	@Override
	public ItemStack getButtonItem(Player player) {
		return new ItemBuilder(Material.REDSTONE)
				.name("&c&lBack")
				.lore(Arrays.asList(
						"&cClick here to return to",
						"&cthe previous menu.")
				)
				.build();
	}

	@Override
	public void clicked(Player player, ClickType clickType) {
		back.openMenu(player);
	}

}
