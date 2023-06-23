//package xyz.akiradev.deezitems.utils;
//
//import dev.rosewood.rosegarden.utils.HexUtils;
//import org.bukkit.ChatColor;
//import org.bukkit.Material;
//import org.bukkit.inventory.ItemFlag;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//import xyz.akiradev.deezitems.DeezItems;
//import xyz.akiradev.deezitems.manager.MaterialManager;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@SuppressWarnings("unused")
//public abstract class DeezMaterial {
//    private final String ID;
//    private final ItemStack itemStack;
//    private final String name;
//    private final String description;
//    private final String rarity;
//    private final int materialID;
//    private final boolean glowing;
//    private final boolean craftable;
//    private final boolean stackable;
//    private int customModelID;
//
//    public DeezMaterial(String id, Material material, String name, String description, String rarity, boolean glowing, boolean craftable, boolean stackable, int customModelID) {
//        this.ID = id;
//        this.itemStack = new ItemStack(material);
//        this.name = name;
//        this.description = description;
//        this.rarity = rarity;
//        this.materialID = ItemUtils.stringToSeed(material.name() + name + rarity);
//        this.glowing = glowing;
//        this.craftable = craftable;
//        this.stackable = stackable;;
//    }
//
//    public DeezMaterial(String id, ItemStack itemStack, String name, String description, String rarity, boolean glowing, boolean craftable, boolean stackable, int customModelID) {
//        this.ID = id;
//        this.itemStack = itemStack;
//        this.name = name;
//        this.description = description;
//        this.rarity = rarity;
//        this.materialID = ItemUtils.stringToSeed(itemStack.getType().name() + name + rarity);
//        this.glowing = glowing;
//        this.craftable = craftable;
//        this.stackable = stackable;
//    }
//
//    public ItemStack makeItem(int amount) {
//        ItemStack itemstack = this.itemStack.clone();
//        ItemMeta meta = itemstack.getItemMeta();
//        assert meta != null;
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        itemstack.setItemMeta(meta);
//        ItemUtils.nameItem(itemstack, ItemRarity.getColor(rarity) + name);
//        ItemUtils.storeIntInItem(itemstack, materialID, "materialID");
//        ItemUtils.setItemLore(itemstack, getLore(itemstack));
//        if (glowing) {
//            ItemUtils.setGlowing(itemstack);
//        }
//        if (!stackable)
//            ItemUtils.storeStringInItem(itemstack, UUID.randomUUID().toString(), "true");
//        return itemstack;
//    }
//
//    private List<String> getLore(ItemStack item) {
//        ArrayList<String> arrayList = new ArrayList<>();
//        if (!this.description.equals("")) {
//            arrayList.addAll(ItemUtils.convertStringToLore(this.description, 35, ChatColor.GRAY));
//            arrayList.add("");
//        }
//        DeezItems.getInstance().getLogger().info("Rarity: " + rarity + " | " + ItemRarity.getColor(rarity) + " | " + ItemRarity.getColor(rarity) + ChatColor.BOLD + this.rarity);
//        arrayList.add(HexUtils.colorify(ItemRarity.getColor(rarity) + ChatColor.BOLD + this.rarity));
//        return arrayList;
//    }
//
//    public ItemStack getItemStack() {
//        return itemStack;
//    }
//
//    public Material getDefaultMaterial() {
//        return itemStack.getType();
//    }
//
//    public String getDefaultName() {
//        return name;
//    }
//
//    public String getDefaultRarity() {
//        return rarity;
//    }
//
//    public String getDefaultDescription() {
//        return description;
//    }
//
//    public int getDefaultCustomModelID() {
//        return customModelID;
//    }
//
//    public Material getMaterial() {
//        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
//        if (materialManager.loadSettings(this).getString("material") == null) {
//            return itemStack.getType();
//        } else {
//            return Material.valueOf(Objects.requireNonNull(materialManager.loadSettings(this).getString("material")));
//        }
//    }
//
//    public String getName() {
//        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
//        if (materialManager.loadSettings(this).getString("name") == null) {
//            return name;
//        } else {
//            return materialManager.loadSettings(this).getString("name");
//        }
//    }
//
//    public String getRarity() {
//        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
//        if (materialManager.loadSettings(this).getString("rarity") == null) {
//            return rarity;
//        } else {
//            return materialManager.loadSettings(this).getString("rarity");
//        }
//    }
//
//    public String getDescription() {
//        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
//        if (materialManager.loadSettings(this).getString("description") == null) {
//            return description;
//        } else {
//            return materialManager.loadSettings(this).getString("description");
//        }
//    }
//
//    public int getCustomModelID() {
//        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
//        if (materialManager.loadSettings(this).getString("customModelID") == null) {
//            return customModelID;
//        } else {
//            return materialManager.loadSettings(this).getInt("customModelID");
//        }
//    }
//
//    public int getMaterialID() {
//        return materialID;
//    }
//
//    public String getID() {
//        return ID;
//    }
//
//    public String getRarityName() {
//        return rarity;
//    }
//
//    public String getRarityColor() {
//        return ItemRarity.getColor(rarity);
//    }
//
//    public abstract void registerRecipe();
//
//}
