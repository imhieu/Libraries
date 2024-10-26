package me.hieu.libraries.util;

/**
 * Author: Le Thanh Hieu
 * Date: 22/10/2024
 */

public class TimeUtil {

    public static String convertToMMSS(long totalSeconds) {
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

}
