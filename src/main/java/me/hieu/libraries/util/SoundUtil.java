package me.hieu.libraries.util;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Author: Le Thanh Hieu
 * Date: 22/10/2024
 */

public class SoundUtil {

    public static void playSound(Player player, Sound sound){
        player.getLocation().getWorld().playSound(player.getLocation(), sound, 1F, 1F);
    }

    public static void playSound(Entity entity, Sound sound){
        entity.getLocation().getWorld().playSound(entity.getLocation(), sound, 0.1F, 0.1F);
    }

}
