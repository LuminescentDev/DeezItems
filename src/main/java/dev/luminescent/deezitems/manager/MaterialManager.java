package dev.luminescent.deezitems.manager;

import com.google.common.collect.ObjectArrays;
import dev.luminescent.deezitems.DeezItems;
import dev.luminescent.deezitems.utils.DeezMaterial;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.config.CommentedFileConfiguration;
import dev.rosewood.rosegarden.manager.Manager;
import dev.rosewood.rosegarden.utils.RoseGardenUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class MaterialManager extends Manager {

    private final Map<String, DeezMaterial> materials = new HashMap<>();
    private final Map<Integer, DeezMaterial> materialIDs = new HashMap<>();
    private CommentedFileConfiguration config;

    public MaterialManager(RosePlugin rosePlugin) {
        super(rosePlugin);
    }

    private void setDefaultSettings(DeezMaterial material) {
        File directory = new File(DeezItems.getInstance().getDataFolder(), "Materials");
        //noinspection ResultOfMethodCallIgnored
        directory.mkdirs();

        File file = new File(directory, material.getID() + ".yml");
        this.config = CommentedFileConfiguration.loadConfiguration(file);

        boolean changed = false;
        changed |= setIfNotExists("name", material.getDefaultName());
        changed |= setIfNotExists("material", material.getDefaultMaterial().name());
        changed |= setIfNotExists("rarity", material.getDefaultRarity());
        changed |= setIfNotExists("lore", material.getDefaultLore());
        changed |= setIfNotExists("custom-model-id", material.getDefaultCustomModelID());
        changed |= setIfNotExists("enabled", true);
        changed |= setIfNotExists("can-craft", true);

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
        DeezItems.getInstance().getLogger().info("Setting: " + setting);
        this.config.set(setting, value, ObjectArrays.concat(comments, new String[]{defaultMessage}, String.class));
        return true;
    }

    public CommentedFileConfiguration loadSettings(DeezMaterial material) {
        File directory = new File(DeezItems.getInstance().getDataFolder(), "Materials");
        //noinspection ResultOfMethodCallIgnored
        directory.mkdirs();

        File file = new File(directory, material.getID() + ".yml");
        this.config = CommentedFileConfiguration.loadConfiguration(file);
        return this.config;
    }

    public void registerMaterial(DeezMaterial material) {
        setDefaultSettings(material);
        if (!this.loadSettings(material).getBoolean("enabled")) return;
        materials.put(material.getID(), material);
        materialIDs.put(material.getItemID(), material);
        DeezItems.getInstance().getLogger().info("Registering material: " + material.getName());
        material.tryRegisterRecipe();
    }

    public List<DeezMaterial> getMaterials() {
        return this.materials.values().stream()
                .sorted(Comparator.comparing(DeezMaterial::getRarityName))
                .collect(Collectors.toList());
    }

    public Collection<String> getMaterialNames() {
        return materials.keySet();
    }

    public DeezMaterial getMaterial(String id) {
        return materials.get(id);
    }

    public DeezMaterial getMaterialFromID(int id) {
        return materialIDs.get(id);
    }

    @Override
    public void reload() {
        for (DeezMaterial material : this.getMaterials()) {
            File directory = new File(DeezItems.getInstance().getDataFolder(), "Materials");
            //noinspection ResultOfMethodCallIgnored
            directory.mkdirs();

            File file = new File(directory, material.getID() + ".yml");
            CommentedFileConfiguration newConfig = CommentedFileConfiguration.loadConfiguration(file);

            if (!newConfig.equals(this.config)) {
                this.config = newConfig;
            }

            material.tryRegisterRecipe();
        }
    }


    @Override
    public void disable() {

    }
}