package me.hieu.libraries.util;

import me.hieu.libraries.Libraries;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskUtil {

    public static void runTaskAsync(Runnable runnable) {
        Libraries.getInstance().getServer().getScheduler().runTaskAsynchronously(Libraries.getInstance(), runnable);
    }

    public static void runTaskLater(Runnable runnable, long delay) {
        Libraries.getInstance().getServer().getScheduler().runTaskLater(Libraries.getInstance(), runnable, delay);
    }

    public static void runTaskTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(Libraries.getInstance(), delay, timer);
    }

    public static void runTaskTimer(Runnable runnable, long delay, long timer) {
        Libraries.getInstance().getServer().getScheduler().runTaskTimer(Libraries.getInstance(), runnable, delay, timer);
    }

    public static void runTask(Runnable runnable) {
        Libraries.getInstance().getServer().getScheduler().runTask(Libraries.getInstance(), runnable);
    }

}
