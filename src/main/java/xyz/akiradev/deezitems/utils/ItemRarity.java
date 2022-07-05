package xyz.akiradev.deezitems.utils;

import org.bukkit.ChatColor;

public enum ItemRarity {
    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.GREEN),
    RARE(ChatColor.AQUA),
    EPIC(ChatColor.LIGHT_PURPLE),
    LEGENDARY(ChatColor.GOLD),
    UNKNOWN(ChatColor.DARK_GRAY),
    UNFINISHED(ChatColor.DARK_RED);

    private ChatColor color;

    ItemRarity(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() {
        return color;
    }



}
