package dev.luminescent.deezitems.locale;

import dev.rosewood.rosegarden.locale.Locale;

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
        return new LinkedHashMap<>() {{
            this.put("#0", "Plugin Message Prefix");
            this.put("prefix", "&8&l[<g:#ffaaaa:#d96dff>DeezItems&8&l] ");

            this.put("#1", "base command");
            this.put("base-command-color", "&a");
            this.put("base-command-help", "&3/%cmd% help &7- &aFor a list of commands");

            this.put("#2", "Command Messages");
            this.put("command-no-permission", "You do not have permission to use this command.");
            this.put("command-unknown", "Unknown command. Use /deezitems help for help.");
            this.put("command-help-description", "&8&l/%cmd% help - Shows this help info.");
            this.put("command-list-description", "&8&l/%cmd% list - Lists all items.");
            this.put("command-give-description", "&8&l/%cmd% give <player> <item> [amount] - Gives a player an item.");
            this.put("command-givematerial-description", "&8&l/%cmd% givematerial <item> [amount] - Gives yourself an item.");
            this.put("help-list", "&8&l/deezitems list - Lists all items.");
            this.put("help-info", "&8&lUse/deezitems info to get info about the plugin.");
            this.put("help-give", "&8&l/deezitems give <item> - Gives yourself an item.");
            this.put("help-reload", "&8&l/deezitems reload - Reloads the plugin.");

            this.put("#3", "List Command");
            this.put("command-list-none", "&eThere are no items currently registered.");
            this.put("command-list-header", "&eThere are &b%amount% &eitems currently registered:");
            this.put("command-list-item", "%rarityColor%%rarityName%&e - %item%");

            this.put("#4", "Other Messages");
            this.put("no-permission-item", "You do not have permission to use this item.");
            this.put("reload-success", "Plugin reloaded successfully.");
            this.put("must-be-player", "You must be a player to use this command.");
            this.put("error-occurred", "An error occurred.");
            this.put("cooldown-message", "&c&lYou must wait %time% seconds before using this ability again.");
            this.put("gui-title", "&e&lDeezItems");

            this.put("#5", "Ability Types (Due to reasons i do not know, the server must be restart for these values to change)");
            this.put("left-click", "Left Click");
            this.put("right-click", "Right Click");
            this.put("middle-click", "Middle Click");
            this.put("block-break", "Break Block");
            this.put("projectile-hit", "Projectile Hit");

            this.put("#6", "Item Messages");
            this.put("no-item-found", "%item% could not be found.");
            this.put("no-material-found", "%material% could not be found.");
            this.put("item-given", "Given %item%.");
            this.put("item-uses-remaining", "Uses remaining: %uses%.");
        }};
    }
}
