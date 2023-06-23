//package xyz.akiradev.deezitems.manager;
//
//import com.google.common.collect.ObjectArrays;
//import dev.rosewood.rosegarden.RosePlugin;
//import dev.rosewood.rosegarden.config.CommentedFileConfiguration;
//import dev.rosewood.rosegarden.manager.Manager;
//import dev.rosewood.rosegarden.utils.RoseGardenUtils;
//import xyz.akiradev.deezitems.DeezItems;
//import xyz.akiradev.deezitems.utils.DeezMaterial;
//
//import java.io.File;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@SuppressWarnings("unused")
//public class MaterialManager extends Manager {
//
//    private final Map<String, DeezMaterial> materials = new HashMap<>();
//    private final Map<Integer, DeezMaterial> materialIDs = new HashMap<>();
//    private CommentedFileConfiguration config;
//
//    public MaterialManager(RosePlugin rosePlugin) {
//        super(rosePlugin);
//    }
//
//    private void setDefaultSettings(DeezMaterial material) {
//        File directory = new File(DeezItems.getInstance().getDataFolder(), "Materials");
//        //noinspection ResultOfMethodCallIgnored
//        directory.mkdirs();
//
//        File file = new File(directory, material.getID() + ".yml");
//        this.config = CommentedFileConfiguration.loadConfiguration(file);
//
//        boolean changed = false;
//        changed |= this.setIfNotExists("name", material.getDefaultName());
//        changed |= this.setIfNotExists("material", material.getDefaultMaterial().name());
//        changed |= this.setIfNotExists("rarity", material.getDefaultRarity());
//        changed |= this.setIfNotExists("description", material.getDefaultDescription());
//        changed |= this.setIfNotExists("custom-model-id", material.getDefaultCustomModelID());
//        changed |= this.setIfNotExists("enabled", true);
//
//        if (changed) this.config.save();
//
//    }
//
//    private boolean setIfNotExists(String setting, Object value, String... comments) {
//        if (this.config.get(setting) != null)
//            return false;
//
//        String defaultMessage = "Default: ";
//        if (value instanceof String && RoseGardenUtils.containsConfigSpecialCharacters((String) value)) {
//            defaultMessage += "'" + value + "'";
//        } else {
//            defaultMessage += value;
//        }
//
//        this.config.set(setting, value, ObjectArrays.concat(comments, new String[]{defaultMessage}, String.class));
//        return true;
//    }
//
//    public CommentedFileConfiguration loadSettings(DeezMaterial material) {
//        File directory = new File(DeezItems.getInstance().getDataFolder(), "Materials");
//        //noinspection ResultOfMethodCallIgnored
//        directory.mkdirs();
//
//        File file = new File(directory, material.getID() + ".yml");
//        this.config = CommentedFileConfiguration.loadConfiguration(file);
//        return this.config;
//    }
//
//    public void registerMaterial(DeezMaterial material) {
//        setDefaultSettings(material);
//        if (!this.loadSettings(material).getBoolean("enabled")) return;
//        materials.put(material.getID(), material);
//        materialIDs.put(material.getMaterialID(), material);
//        DeezItems.getInstance().getLogger().info("Registering material: " + material.getName());
//        material.registerRecipe();
//    }
//
//    public List<DeezMaterial> getMaterials() {
//        return this.materialIDs.values().stream()
//                .sorted(Comparator.comparing(DeezMaterial::getName))
//                .collect(Collectors.toList());
//    }
//
//    public Collection<String> getMaterialNames() {
//        return materials.keySet();
//    }
//
//    public DeezMaterial getMaterial(String name) {
//        return materials.get(name);
//    }
//
//    public DeezMaterial getMaterialFromID(int id) {
//        return materialIDs.get(id);
//    }
//
//    @Override
//    public void reload() {
//        for (DeezMaterial material : this.getMaterials()) {
//            File directory = new File(DeezItems.getInstance().getDataFolder(), "Materials");
//            //noinspection ResultOfMethodCallIgnored
//            directory.mkdirs();
//
//            File file = new File(directory, material.getID() + ".yml");
//            CommentedFileConfiguration newConfig = CommentedFileConfiguration.loadConfiguration(file);
//
//            if (!newConfig.equals(this.config)) {
//                this.config = newConfig;
//            }
//        }
//    }
//
//    @Override
//    public void disable() {
//
//    }
//}
