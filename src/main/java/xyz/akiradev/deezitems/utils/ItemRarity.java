package xyz.akiradev.deezitems.utils;

import xyz.akiradev.deezitems.manager.ConfigurationManager.Setting;

import java.util.HashMap;
import java.util.Map;

public class ItemRarity {

    public static Map<String, String> Raritys = new HashMap<>();

    public static void loadRaritys() {
        Raritys.put("Common", Setting.RARITY_COLOR_COMMON.getString());
        Raritys.put("Uncommon", Setting.RARITY_COLOR_UNCOMMON.getString());
        Raritys.put("Rare", Setting.RARITY_COLOR_RARE.getString());
        Raritys.put("Epic", Setting.RARITY_COLOR_EPIC.getString());
        Raritys.put("Legendary", Setting.RARITY_COLOR_LEGENDARY.getString());
        Raritys.put("Unknown", Setting.RARITY_COLOR_UNKNOWN.getString());
        Raritys.put("Unfinished", Setting.RARITY_COLOR_UNFINISHED.getString());
    }

    public static String getColor(String rarity) {
        return Raritys.get(rarity);
    }



}
