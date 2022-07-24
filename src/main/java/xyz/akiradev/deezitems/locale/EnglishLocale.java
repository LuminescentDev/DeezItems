package xyz.akiradev.deezitems.locale;

import xyz.akiradev.pluginutils.locale.Locale;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnglishLocale implements Locale {

    @Override
    public String getLocaleName() {
        return "en_US";
    }

    @Override
    public String getTranslatorName() {
        return "AkiraDev";
    }

    @Override
    public Map<String, Object> getDefaultLocaleValues() {
        return new LinkedHashMap<String, Object>() {{
            this.put("#0", "Plugin Message Prefix");
            this.put("prefix", "&8&l[<g:#ffaaaa:#d96dff>DeezItems&8&l] ");

            this.put("#1", "Command Messages");
            this.put("no-permission-command", "You do not have permission to use this command.");
            this.put("unknown-command", "Unknown command. Use /deezitems help for help.");

            this.put("#2", "help Messages");
            this.put("help", "&8&l/deezitems help - Shows this help info.");
            this.put("help-list", "&8&l/deezitems list - Lists all items.");
            this.put("help-info", "&8&lUse/deezitems info to get info about the plugin.");
            this.put("help-give", "&8&l/deezitems give <item> - Gives yourself an item.");
            this.put("help-reload", "&8&l/deezitems reload - Reloads the plugin.");

            this.put("#3", "Other Messages");
            this.put("no-permission-item", "You do not have permission to use this item.");
            this.put("reload-success", "Plugin reloaded successfully.");
            this.put("must-be-player", "You must be a player to use this command.");
            this.put("error-occurred", "An error occurred.");
            this.put("cooldown-message", "&c&lYou must wait %time% seconds before using this ability again.");

            this.put("#4", "Ability Types (Due to reasons i do not know, the server must be restart for these values to change)");
            this.put("left-click", "Left Click");
            this.put("right-click", "Right Click");
            this.put("middle-click", "Middle Click");
            this.put("block-break", "Break Block");
            this.put("projectile-hit", "Projectile Hit");

            this.put("#5", "Item Messages");
            this.put("no-item-found", "No item found.");
            this.put("item-given", "Given %item%.");
            this.put("item-uses-remaining", "Uses remaining: %uses%.");
        }};
    }
}
