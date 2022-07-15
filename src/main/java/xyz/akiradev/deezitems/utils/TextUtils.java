package xyz.akiradev.deezitems.utils;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class TextUtils {

    public static void warnPlayer(CommandSender sender, List<String> messages) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
        }

        for(String message : messages) {
            HexUtils.sendMessage(sender, message);
        }

    }

    public static void warnPlayer(CommandSender sender, String message) {
        warnPlayer(sender, Collections.singletonList(message));
    }

    public static void multilineReply(CommandSender sender, String header, String footer, List<String> lines) {
        sender.sendMessage(HexUtils.colorify(header));
        for(String line : lines) {
            HexUtils.sendMessage(sender, HexUtils.colorify(line));
        }
        sender.sendMessage(HexUtils.colorify(footer));
    }
}
