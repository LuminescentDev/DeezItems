package xyz.akiradev.deezitems.manager;

import com.google.common.collect.ObjectArrays;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.config.CommentedFileConfiguration;
import dev.rosewood.rosegarden.manager.Manager;
import dev.rosewood.rosegarden.utils.RoseGardenUtils;
import xyz.akiradev.deezitems.DeezItems;
import xyz.akiradev.deezitems.utils.DeezItem;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ItemManager extends Manager {

    private final Map<String, DeezItem> items = new HashMap<>();
    private final Map<Integer, DeezItem> itemIDs = new HashMap<>();
    private CommentedFileConfiguration config;

    public ItemManager(RosePlugin rosePlugin) {
        super(rosePlugin);
    }

    private void setDefaultSettings(DeezItem item) {
        File directory = new File(DeezItems.getInstance().getDataFolder(), "Items");
        //noinspection ResultOfMethodCallIgnored
        directory.mkdirs();

        File file = new File(directory, item.getID() + ".yml");
        this.config = CommentedFileConfiguration.loadConfiguration(file);

        boolean changed = false;
        changed |= setIfNotExists("name", item.getDefaultName());
        changed |= setIfNotExists("material", item.getDefaultMaterial().name());
        changed |= setIfNotExists("rarity", item.getDefaultRarity());
        changed |= setIfNotExists("lore", item.getDefaultLore());
        changed |= setIfNotExists("custom-model-id", item.getDefaultCustomModelID());
        changed |= setIfNotExists("enabled", true);

        if (changed) {
            this.config.save();
        }
    }


    private boolean setIfNotExists(String setting, Object value, String... comments) {
        if (this.config.get(setting) != null)
            return false;

        String defaultMessage = "Default: ";
        if (value instanceof String && RoseGardenUtils.containsConfigSpecialCharacters((String) value)) {
            defaultMessage += "'" + value + "'";
        } else {
            defaultMessage += value;
        }

        this.config.set(setting, value, ObjectArrays.concat(comments, new String[]{defaultMessage}, String.class));
        return true;
    }

    public CommentedFileConfiguration loadSettings(DeezItem item) {
        File directory = new File(DeezItems.getInstance().getDataFolder(), "Items");
        //noinspection ResultOfMethodCallIgnored
        directory.mkdirs();

        File file = new File(directory, item.getID() + ".yml");
        this.config = CommentedFileConfiguration.loadConfiguration(file);
        return this.config;
    }

    public void registerItem(DeezItem item) {
        setDefaultSettings(item);
        if (!this.loadSettings(item).getBoolean("enabled")) return;
        items.put(item.getID(), item);
        itemIDs.put(item.getItemID(), item);
        DeezItems.getInstance().getLogger().info("Registering item: " + item.getName());
        item.registerRecipe();
    }

    public List<DeezItem> getItems() {
        return this.items.values().stream()
                .sorted(Comparator.comparing(DeezItem::getRarityName))
                .collect(Collectors.toList());
    }

    public Collection<String> getItemNames() {
        return items.keySet();
    }

    public DeezItem getItem(String name) {
        return items.get(name);
    }

    public DeezItem getItemFromID(int id) {
        return itemIDs.get(id);
    }

    @Override
    public void reload() {
        for (DeezItem item : this.getItems()) {
            File directory = new File(DeezItems.getInstance().getDataFolder(), "Items");
            //noinspection ResultOfMethodCallIgnored
            directory.mkdirs();

            File file = new File(directory, item.getID() + ".yml");
            CommentedFileConfiguration newConfig = CommentedFileConfiguration.loadConfiguration(file);

            if (!newConfig.equals(this.config)) {
                this.config = newConfig;
            }
        }
    }


    @Override
    public void disable() {

    }
}