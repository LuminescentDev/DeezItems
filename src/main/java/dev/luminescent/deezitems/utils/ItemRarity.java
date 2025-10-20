package dev.luminescent.deezitems.utils;

import dev.luminescent.deezitems.manager.Settings;

import java.util.HashMap;
import java.util.Map;

public class ItemRarity {

    public static final Map<String, String> Raritys = new HashMap<>();

    public static void loadRaritys() {
        Raritys.put("Common", Settings.RARITY_COLOR_COMMON.get());
        Raritys.put("Uncommon", Settings.RARITY_COLOR_UNCOMMON.get());
        Raritys.put("Rare", Settings.RARITY_COLOR_RARE.get());
        Raritys.put("Epic", Settings.RARITY_COLOR_EPIC.get());
        Raritys.put("Legendary", Settings.RARITY_COLOR_LEGENDARY.get());
        Raritys.put("Unknown", Settings.RARITY_COLOR_UNKNOWN.get());
        Raritys.put("Unfinished", Settings.RARITY_COLOR_UNFINISHED.get());
    }

    public static String getColor(String rarity) {
        return Raritys.get(rarity);
    }

}
