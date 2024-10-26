package me.hieu.libraries.drink.provider.spigot;

import me.hieu.libraries.drink.argument.CommandArg;
import me.hieu.libraries.drink.exception.CommandExitMessage;
import me.hieu.libraries.drink.parametric.DrinkProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class PlayerProvider extends DrinkProvider<Player> {

    private final Plugin plugin;

    public PlayerProvider(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean doesConsumeArgument() {
        return true;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean allowNullArgument() {
        return false;
    }

    @Nullable
    @Override
    public Player defaultNullValue() {
        return null;
    }

    @Nullable
    @Override
    public Player provide(@Nonnull CommandArg arg, @Nonnull List<? extends Annotation> annotations) throws CommandExitMessage {
        String name = arg.get();
        Player p = plugin.getServer().getPlayer(name);
        if (p != null) {
            return p;
        }
        throw new CommandExitMessage("No player online with the name '" + name + "'.");
    }

    @Override
    public String argumentDescription() {
        return "player";
    }

    @Override
    public List<String> getSuggestions(@Nonnull String prefix) {
        List<String> toReturn = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()){
            if (player.getName().toLowerCase().startsWith(prefix.toLowerCase())) toReturn.add(player.getName());
        }
        return toReturn;
    }
}
