package dev.luminescent.deezitems.utils;

import dev.luminescent.deezitems.DeezItems;
import dev.luminescent.deezitems.manager.LocaleManager;
import dev.rosewood.rosegarden.utils.HexUtils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class TextUtils {

    private TextUtils() {}

    public static void warnPlayer(CommandSender sender, List<String> messages) {
        LocaleManager localeManager = DeezItems.getInstance().getManager(LocaleManager.class);
        if(sender instanceof Player player) {
            player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
        }

        for(String message : messages) {
            HexUtils.sendMessage(sender, localeManager.getLocaleMessage("prefix") + message);
        }

    }

    public static void warnPlayer(CommandSender sender, String message) {
        warnPlayer(sender, Collections.singletonList(message));
    }

    public static void multilineReply(CommandSender sender, String header, String footer, List<String> lines) {
        LocaleManager localeManager = DeezItems.getInstance().getManager(LocaleManager.class);
        sender.sendMessage(HexUtils.colorify(header));
        for(String line : lines) {
            HexUtils.sendMessage(sender, localeManager.getLocaleMessage("prefix") + line);
        }
        sender.sendMessage(HexUtils.colorify(footer));
    }
}
