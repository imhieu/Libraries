package me.hieu.libraries;

import com.google.common.base.Preconditions;
import lombok.Getter;
import me.hieu.libraries.drink.CommandService;
import me.hieu.libraries.drink.command.DrinkCommandService;
import me.hieu.libraries.menu.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Libraries extends JavaPlugin {

    @Getter
    public static Libraries instance;

    private static final ConcurrentMap<String, CommandService> services = new ConcurrentHashMap<>();

    public static CommandService get(@Nonnull JavaPlugin javaPlugin) {
        Preconditions.checkNotNull(javaPlugin, "JavaPlugin cannot be null");
        return services.computeIfAbsent(javaPlugin.getName(), name -> new DrinkCommandService(javaPlugin));
    }

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

}
