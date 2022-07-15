package xyz.akiradev.deezitems.utils;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class ItemRarity {

    public static Map<String, String> Raritys = new HashMap();
    public String name;
    public String color;

    public static void loadRaritys() {
        Raritys.put("Common", "#FFFFFF");
        Raritys.put("Uncommon", "#009b02");
        Raritys.put("Rare", "#00b0ff");
        Raritys.put("Epic", "#9b00ff");
        Raritys.put("Legendary", "#ffbe39");
        Raritys.put("Unknown", "#393939");
        Raritys.put("Unfinished", "#ff0000");
    }

    public static String getColor(String rarity) {
        return Raritys.get(rarity);
    }



}
