package me.hieu.libraries.drink.command;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

@Getter
@Setter
public class DrinkAuthorizer {

    private String noPermissionMessage = ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact server administrators if you believe that this is in error.";

    public boolean isAuthorized(@Nonnull CommandSender sender, @Nonnull DrinkCommand command) {
        if (command.getPermission() != null && command.getPermission().length() > 0) {
            if (!sender.hasPermission(command.getPermission())) {
                sender.sendMessage(noPermissionMessage);
                return false;
            }
        }
        return true;
    }

}
