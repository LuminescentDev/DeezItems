package dev.luminescent.deezitems.manager;

import dev.luminescent.deezitems.DeezItems;
import dev.rosewood.rosegarden.config.RoseSetting;
import dev.rosewood.rosegarden.config.SettingHolder;
import dev.rosewood.rosegarden.config.SettingSerializer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static dev.rosewood.rosegarden.config.SettingSerializers.*;

public class Settings implements SettingHolder {

    public static final SettingHolder INSTANCE = new Settings();
    private static final List<RoseSetting<?>> SETTINGS = new ArrayList<>();

    public static final RoseSetting<Boolean> CAN_CRAFT_WITH_ITEMS = create("can-craft-with-items", BOOLEAN, true, "Can users use deez items as regular items in crafting?");
    public static final RoseSetting<Boolean> USE_ACTIONBAR = create("use-actionbar", BOOLEAN, true, "use actionbar instead of chat for certain things?");
    public static final RoseSetting<Boolean> USE_GUI_FOR_LIST = create("use-gui-for-list", BOOLEAN, false, "Use gui for item list instead of chat?");
    public static final RoseSetting<Integer> GUI_ITEM_MODELS_SHOW_ITEMS = create("gui-item-models.items", INTEGER, 100);
    public static final RoseSetting<Integer> GUI_ITEM_MODELS_SHOW_MATERIALS = create("gui-item-models.materials", INTEGER, 200);
    public static final RoseSetting<String> RARITY_COLOR_COMMON = create("rarity-colors.common", STRING, "#04ff00", "Rarity color for common items");
    public static final RoseSetting<String> RARITY_COLOR_UNCOMMON = create("rarity-colors.uncommon", STRING, "#009b02", "Rarity color for uncommon items");
    public static final RoseSetting<String> RARITY_COLOR_RARE = create("rarity-colors.rare", STRING, "#00b0ff", "Rarity color for rare items");
    public static final RoseSetting<String> RARITY_COLOR_EPIC = create("rarity-colors.epic", STRING, "#9b00ff", "Rarity color for epic items");
    public static final RoseSetting<String> RARITY_COLOR_LEGENDARY = create("rarity-colors.legendary", STRING, "#ffbe39", "Rarity color for legendary items");
    public static final RoseSetting<String> RARITY_COLOR_UNKNOWN = create("rarity-colors.unknown", STRING, "#393939", "Rarity color for unknown items");
    public static final RoseSetting<String> RARITY_COLOR_UNFINISHED = create("rarity-colors.unfinished", STRING, "#ff0000", "Rarity color for unfinished items");

    private Settings() {}

    private static <T> RoseSetting<T> create(String key, SettingSerializer<T> serializer, T defaultValue, String... comments) {
        RoseSetting<T> setting = RoseSetting.ofBackedValue(key, DeezItems.getInstance(), serializer, defaultValue, comments);
        SETTINGS.add(setting);
        return setting;
    }

    @Override
    public List<RoseSetting<?>> get() {
        return Collections.unmodifiableList(SETTINGS);
    }

}