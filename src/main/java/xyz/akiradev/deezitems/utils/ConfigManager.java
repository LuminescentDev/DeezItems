package xyz.akiradev.deezitems.utils;

import de.leonhard.storage.Config;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.ReloadSettings;
import xyz.akiradev.deezitems.DeezItems;

public class ConfigManager {
    private static Config config;

    public static void loadConfig() {
        config = new Config("config", "plugins/DeezItems");
        config.setReloadSettings(ReloadSettings.MANUALLY);
        config.setConfigSettings(ConfigSettings.PRESERVE_COMMENTS);
        config.addDefaultsFromInputStream(DeezItems.getInstance().getResource("config.yml"));
    }
    public static void reloadConfig() {
        config.forceReload();
    }

    public static Config getConfig() {
        return config;
    }
}
