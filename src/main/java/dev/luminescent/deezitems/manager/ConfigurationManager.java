package dev.luminescent.deezitems.manager;

import dev.luminescent.deezitems.DeezItems;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.config.CommentedFileConfiguration;
import dev.rosewood.rosegarden.config.RoseSetting;
import dev.rosewood.rosegarden.manager.AbstractConfigurationManager;

public class ConfigurationManager extends AbstractConfigurationManager {

    public enum Setting implements RoseSetting {
        CAN_CRAFT_WITH_ITEMS("can-craft-with-items", true, "Can users use deez items as regular items in crafting?"),
        USE_ACTIONBAR("use-actionbar", true, "use actionbar instead of chat for certain things?"),
        USE_GUI_FOR_LIST("use-gui-for-list", false, "Use gui for item list instead of chat?"),
        GUI_ITEM_MODELS_SHOW_ITEMS("gui-item-models.items", 100),
        GUI_ITEM_MODELS_SHOW_MATERIALS("gui-item-models.materials", 200),
        RARITY_COLOR_COMMON("rarity-color-common", "#04ff00", "Rarity color for common items"),
        RARITY_COLOR_UNCOMMON("rarity-color-uncommon", "#009b02", "Rarity color for uncommon items"),
        RARITY_COLOR_RARE("rarity-color-rare", "#00b0ff", "Rarity color for rare items"),
        RARITY_COLOR_EPIC("rarity-color-epic", "#9b00ff", "Rarity color for epic items"),
        RARITY_COLOR_LEGENDARY("rarity-color-legendary", "#ffbe39", "Rarity color for legendary items"),
        RARITY_COLOR_UNKNOWN("rarity-color-unknown", "#393939", "Rarity color for unknown items"),
        RARITY_COLOR_UNFINISHED("rarity-color-unfinished", "#ff0000", "Rarity color for unfinished items");

        private final String key;
        private final Object defaultValue;
        private final String[] comments;
        private Object value = null;

        Setting(String key, Object defaultValue, String... comments) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.comments = comments != null ? comments : new String[0];
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public Object getDefaultValue() {
            return this.defaultValue;
        }

        @Override
        public String[] getComments() {
            return this.comments;
        }

        @Override
        public Object getCachedValue() {
            return this.value;
        }

        @Override
        public void setCachedValue(Object value) {
            this.value = value;
        }

        @Override
        public CommentedFileConfiguration getBaseConfig() {
            return DeezItems.getInstance().getManager(ConfigurationManager.class).getConfig();
        }
    }

    public ConfigurationManager(RosePlugin plugin) {
        super(plugin, Setting.class);
    }

    @Override
    protected String[] getHeader() {
        return new String[] {
                "This is the configuration file for the DeezItems plugin.",
                "",
                "You can change the settings here, but you should know that",
                "the changes will not take effect until the plugin is reloaded or the server is restarted.",
                "",
                "To reload the plugin, type /deezitems reload",
                "",
                "Due to how rarity's are setup you must restart the server to change the colors",
                "",
                "Enjoy the plugin!"
        };
    }

}