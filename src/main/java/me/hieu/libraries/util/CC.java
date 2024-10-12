package me.hieu.libraries.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CC {

    public static String translate(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> translate(List<String> lines) {
        List<String> toReturn = new ArrayList<>();

        for (String line : lines) {
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        return toReturn;
    }

    public static List<String> translate(String[] lines) {
        List<String> toReturn = new ArrayList<>();

        for (String line : lines) {
            if (line != null) {
                toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
            }
        }

        return toReturn;
    }

    public static String MENU_BAR = ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "------------------------";
    public static String CHAT_BAR = ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------";
    public static String SB_BAR = ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "----------------------";

}
