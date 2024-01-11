package dev.luminescent.deezitems.utils;

import dev.luminescent.deezitems.DeezItems;
import dev.luminescent.deezitems.manager.LocaleManager;
import dev.luminescent.deezitems.manager.MaterialManager;
import dev.rosewood.rosegarden.utils.HexUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeezMaterial {

    private final String ID;
    private final Material material;
    private final String name;
    private final String rarity;
    private final List<String> lore;
    private final int itemID;
    private final int customModelID;

    public DeezMaterial(String id, Material material, String name, String rarity, List<String> lore,  int customModelID) {
        this.ID = id;
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.lore = lore;
        this.itemID = ItemUtils.stringToSeed(material.name() + name + rarity);
        this.customModelID = customModelID;
    }

    public static void destroyItem(ItemStack item, int amount) {
        item.setAmount(Math.max(item.getAmount() - amount, 0));
    }

    public ItemStack generate(int amount) {
        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
        ItemStack item = ItemUtils.nameItem(material, ItemRarity.getColor(rarity) + name);
        ItemUtils.storeIntInItem(item, itemID, "materialID");
        ItemUtils.setItemLore(item, getLore(item));
        int modelID = materialManager.loadSettings(this).getInt("custom-model-id");
        if (modelID != -1) {
            ItemUtils.setCustomModelID(item, modelID);
        }

        item.setAmount(amount);
        return item;
    }

    private List<String> getLore(ItemStack item) {
        LocaleManager localeManager = DeezItems.getInstance().getManager(LocaleManager.class);
        List<String> list = new ArrayList<>();
        if (rarity.equalsIgnoreCase("unfinished")) {
            list.add("Item Unfinished");
            list.add("Likely to be broken");
        }

        list.add("");

        list.addAll(getLore());

        list.add(HexUtils.colorify(ItemRarity.getColor(rarity) + "&l" + rarity));
        return list;
    }

    public Material getDefaultMaterial() {
        return material;
    }

    public String getDefaultName() {
        return name;
    }

    public String getDefaultRarity() {
        return rarity;
    }

    public List<String> getDefaultLore() {
        return Objects.requireNonNullElseGet(lore, List::of);
    }

    public int getDefaultCustomModelID() {
        return customModelID;
    }

    public Material getMaterial() {
        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
        if (materialManager.loadSettings(this).getString("material") == null) {
            return material;
        }
        return Material.getMaterial(Objects.requireNonNull(materialManager.loadSettings(this).getString("material")));
    }

    public String getName() {
        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
        if (materialManager.loadSettings(this).getString("name") == null) {
            return name;
        }
        return materialManager.loadSettings(this).getString("name");
    }

    public String getRarity() {
        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
        if (materialManager.loadSettings(this).getString("rarity") == null) {
            return rarity;
        }
        return materialManager.loadSettings(this).getString("rarity");
    }

    public List<String> getLore() {
        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
        return materialManager.loadSettings(this).getStringList("lore");
    }


    public String getID() {
        return ID;
    }

    public int getItemID() {
        return itemID;
    }

    public int getCustomModelID() {
        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
        return materialManager.loadSettings(this).getInt("custom-model-id");
    }

    public void registerRecipe() {

    }

    private boolean shouldRegisterRecipe() {
        return DeezItems.getInstance().getManager(MaterialManager.class).loadSettings(this).getBoolean("can-craft", false);
    }

    public final void tryRegisterRecipe(){
        if(shouldRegisterRecipe()){
            registerRecipe();
        }else{
            Bukkit.getServer().removeRecipe(new NamespacedKey(DeezItems.getInstance(), getID()));
        }
    }

    public String getRarityName() {
        return rarity;
    }

    public String getRarityColor() {
        return ItemRarity.getColor(rarity);
    }
}
