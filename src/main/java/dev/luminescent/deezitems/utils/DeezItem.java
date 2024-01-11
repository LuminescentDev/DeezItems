package dev.luminescent.deezitems.utils;

import dev.luminescent.deezitems.DeezItems;
import dev.luminescent.deezitems.manager.ItemManager;
import dev.luminescent.deezitems.manager.LocaleManager;
import dev.rosewood.rosegarden.utils.HexUtils;
import dev.rosewood.rosegarden.utils.StringPlaceholders;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public abstract class DeezItem {
    private final String ID;
    private final Material material;
    private final String name;
    private final String rarity;
    private final List<String> lore;
    private final int itemID;
    private final int uses;
    private final List<ItemAbility> abilities;
    private final int customModelID;
    public final boolean hasActiveEffect = false;
    public final boolean stackable = true;

    public DeezItem(String id, Material material, String name, String rarity, List<String> lore, int uses, List<ItemAbility> abilities, int customModelID) {
        this.ID = id;
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.lore = lore;
        this.itemID = ItemUtils.stringToSeed(material.name() + name + rarity);
        this.uses = uses;
        this.abilities = abilities;
        this.customModelID = customModelID;
    }


    public static void destroyItem(ItemStack item, int amount) {
        item.setAmount(Math.max(item.getAmount() - amount, 0));
    }

    public ItemStack generate(int amount) {
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        ItemStack item = ItemUtils.nameItem(material, ItemRarity.getColor(rarity) + name);
        ItemUtils.storeIntInItem(item, itemID, "itemID");
        ItemUtils.storeIntInItem(item, uses, "uses");
        ItemUtils.setItemLore(item, getLore(item));
        int modelID = itemManager.loadSettings(this).getInt("custom-model-id");
        if (modelID != -1) {
            ItemUtils.setCustomModelID(item, modelID);
        }

        item.setAmount(amount);

        return item;
    }

    private List<String> getLore(ItemStack item) {
        LocaleManager localeManager = DeezItems.getInstance().getManager(LocaleManager.class);
        List<String> list = new ArrayList<>();
        int uses = ItemUtils.getIntFromItem(item, "uses");
        if (rarity.equalsIgnoreCase("unfinished")) {
            list.add("Item Unfinished");
            list.add("Likely to be broken");
        }

        list.add("");

        abilities.stream()
                .map(ItemAbility::setLore)
                .forEach(list::addAll);

        list.add("");

        if (uses >= 1) {
            list.add(localeManager.getLocaleMessage("item-uses-remaining", StringPlaceholders.single("uses", uses)));
            list.add("");
        }

        list.addAll(getLore());

        list.add(HexUtils.colorify(ItemRarity.getColor(rarity) + "&l" + rarity));
        return list;
    }

    public void setLore(ItemStack item) {
        ItemUtils.setItemLore(item, getLore(item));
    }

    public void onItemUse(Player player, ItemStack item) {
        int itemUses = ItemUtils.getIntFromItem(item, "uses");
        if(player.getGameMode() == GameMode.CREATIVE) return;
        if (item.getAmount() > 1 && itemUses != -1) {
            int remainingAmount = item.getAmount() - 1;
            item.setAmount(1);
            if(itemUses == 1){
                ItemStack savedStack = item.clone();
                item.setAmount(remainingAmount);
                return;
            }
            boolean couldMove = ItemUtils.moveItemsToFreeSlot(player.getInventory(), item, remainingAmount);

            if (!couldMove) {
                dropRemainingItems(player, item, remainingAmount);
            }
        }

        if (itemUses == 1) {
            destroyItem(item, 1);
        }else if (itemUses > 0) {
            itemUses--;
            ItemUtils.storeIntInItem(item, itemUses, "uses");
            ItemUtils.setItemLore(item, getLore(item));
        }
    }

    private void dropRemainingItems(Player player, ItemStack item, int amount) {
        Location playerLocation = player.getLocation();
        World world = player.getWorld();

        ItemStack remainingItems = item.clone();
        remainingItems.setAmount(amount);

        world.dropItem(playerLocation, remainingItems);
    }

    /**
     * event when player left clicks air
     */
    public boolean leftClickAirAction(Player player, ItemStack item) {
        return false;
    }


    public boolean leftClickEntityAction(Player player, PlayerInteractEvent event, Player target, ItemStack item) {
        return false;
    }

    /**
     * event when player left clicks block
     */
    public boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    /**
     * event when player shift left clicks air
     */
    public boolean shiftLeftClickAirAction(Player player, ItemStack item) {
        return false;
    }

    /**
     * event when player shift left clicks block
     */
    public boolean shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    /**
     * event when player right clicks air
     */
    public boolean rightClickAirAction(Player player, ItemStack item) {
        return false;
    }

    /**
     * event when player right clicks block
     */
    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    /**
     * event when player shift right clicks air
     */
    public boolean shiftRightClickAirAction(Player player, ItemStack item) {
        return false;
    }

    /**
     * event when player shift right clicks block
     */
    public boolean shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) {
        return false;
    }

    /**
     * event when player middle clicks
     */
    public boolean middleClickAction(Player player, ItemStack item) {
        return false;
    }

    /**
     * event when player breaks block
     */
    public boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item) {
        return false;
    }

    /**
     * event when projectile hits something
     */

    public boolean projectileHitAction(Player player, ProjectileHitEvent event, ItemStack item) {
        return false;
    }

    public void activeEffect(Player player, ItemStack item) {
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
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        if (itemManager.loadSettings(this).getString("material") == null) {
            return material;
        }
        return Material.getMaterial(Objects.requireNonNull(itemManager.loadSettings(this).getString("material")));
    }

    public String getName() {
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        if (itemManager.loadSettings(this).getString("name") == null) {
            return name;
        }
        return itemManager.loadSettings(this).getString("name");
    }

    public String getRarity() {
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        if (itemManager.loadSettings(this).getString("rarity") == null) {
            return rarity;
        }
        return itemManager.loadSettings(this).getString("rarity");
    }

    public List<String> getLore() {
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        return itemManager.loadSettings(this).getStringList("lore");
    }


    public String getID() {
        return ID;
    }

    public int getItemID() {
        return itemID;
    }

    public int getUses() {
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        if (itemManager.loadSettings(this).getInt("uses") == 0) {
            return uses;
        }
        return itemManager.loadSettings(this).getInt("uses");
    }

    public int getCustomModelID() {
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        return itemManager.loadSettings(this).getInt("custom-model-id");
    }

    public void registerRecipe() {

    }

    private boolean shouldRegisterRecipe() {
        NamespacedKey key = new NamespacedKey(DeezItems.getInstance(), getID());
        if(Bukkit.getServer().getRecipe(key) != null){
            return false;
        }
        return DeezItems.getInstance().getManager(ItemManager.class).loadSettings(this).getBoolean("can-craft", false);
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
