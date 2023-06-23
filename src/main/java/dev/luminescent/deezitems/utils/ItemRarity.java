package dev.luminescent.deezitems.utils;

import dev.luminescent.deezitems.manager.ConfigurationManager;

import java.util.HashMap;
import java.util.Map;

public class ItemRarity {

    public static final Map<String, String> Raritys = new HashMap<>();

    public static void loadRaritys() {
        Raritys.put("Common", ConfigurationManager.Setting.RARITY_COLOR_COMMON.getString());
        Raritys.put("Uncommon", ConfigurationManager.Setting.RARITY_COLOR_UNCOMMON.getString());
        Raritys.put("Rare", ConfigurationManager.Setting.RARITY_COLOR_RARE.getString());
        Raritys.put("Epic", ConfigurationManager.Setting.RARITY_COLOR_EPIC.getString());
        Raritys.put("Legendary", ConfigurationManager.Setting.RARITY_COLOR_LEGENDARY.getString());
        Raritys.put("Unknown", ConfigurationManager.Setting.RARITY_COLOR_UNKNOWN.getString());
        Raritys.put("Unfinished", ConfigurationManager.Setting.RARITY_COLOR_UNFINISHED.getString());
    }

    public static String getColor(String rarity) {
        return Raritys.get(rarity);
    }

}
