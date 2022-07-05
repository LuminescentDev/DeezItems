package xyz.akiradev.deezitems.utils;

import de.leonhard.storage.Config;
import de.leonhard.storage.internal.settings.ReloadSettings;

import java.io.File;

public class ConfigManager {
    private static Config config;

    public static void loadConfig() {
        config = new Config("config", "plugins/DeezItems");
        config.setReloadSettings(ReloadSettings.MANUALLY);
        config.setDefault("useActionBarForError", false);
    }
    public static void reloadConfig() {
        config.forceReload();
    }
}
