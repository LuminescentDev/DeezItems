package xyz.akiradev.deezitems.utils;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.akiradev.deezitems.DeezItems;

import java.util.Collections;
import java.util.List;

public class TextUtils {

    /**
     * convert a string with & to a valid ChatColor encoded string
     * @param text - text to be formatted
     */
    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * conver a list of string with & encoded colors to a ChatColor encoded list
     * @param textList - list of strings to be formatted
     */
    public static List<String> colorize(List<String> textList) {
        for (int i = 0; i < textList.size(); i++) {
            textList.set(i, colorize(textList.get(i)));
        }
        return textList;
    }

    /**
     * convert an array of strings with & encoded colors to a ChatColor encoded array
     * @param textArray - array of strings to be formatted
     */
    public static String[] colorize(String...textArray) {
        for (int i = 0; i < textArray.length; i++) {
            textArray[i] = colorize(textArray[i]);
        }
        return textArray;
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(colorize(DeezItems.prefix + message));
    }

    public static void warnPlayer(CommandSender sender, List<String> messages) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
        }

        for(String message : messages) {
            sendMessage(sender, message);
        }

    }

    public static void warnPlayer(CommandSender sender, String message) {
        warnPlayer(sender, Collections.singletonList(message));
    }

    public static void multilineReply(CommandSender sender, String header, String footer, List<String> lines) {
        sender.sendMessage(colorize(header));
        for(String line : lines) {
            sendMessage(sender, colorize(line));
        }
        sender.sendMessage(colorize(footer));
    }
}
