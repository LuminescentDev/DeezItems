package xyz.akiradev.deezitems.utils;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.akiradev.deezitems.utils.ItemAbility;
import xyz.akiradev.deezitems.utils.ItemRarity;
import xyz.akiradev.deezitems.utils.ItemUtils;
import xyz.akiradev.deezitems.utils.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DeezItem {
    private Material material;
    private String name;
    private ItemRarity rarity;
    private int amount;
    private List<String> lore;
    private int itemID;
    private boolean oneUse;
    private List<ItemAbility> abilities;


    public DeezItem(Material material ,String name, ItemRarity rarity, int amount, List<String> lore, boolean oneUse, List<ItemAbility> abilities) {
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.amount = amount;
        this.lore = lore;
        this.itemID = ItemUtils.stringToSeed(material.name() + name + rarity.name());
        this.oneUse = oneUse;
        this.abilities = abilities;
    }

    public ItemStack Generate(int Amount) {
        ItemStack item = ItemUtils.nameItem(this.material, this.rarity.getColor() + this.name);
        ItemUtils.storeIntInItem(item, itemID, "itemID");
        ItemUtils.setItemLore(item, getLore(item));
        item.setAmount(Amount);
        return item;
    }

    private List<String> getLore(ItemStack item){
        ArrayList list = new ArrayList();
        if(rarity.equals(ItemRarity.UNFINISHED)){
            list.add("Item Unfinished");
            list.add("Likely to be broken");
        }

        list.add("");

        Iterator i = abilities.iterator();
        while (i.hasNext()){
            ItemAbility ability = (ItemAbility)i.next();
            list.addAll(ability.setLore());
            list.add("");
        }
        if(oneUse){
            list.add(TextUtils.colorize("&b&l(One time use item)"));
        }
        list.add(TextUtils.colorize(rarity.getColor() + "&l" + rarity));
        return list;
    }

    public void onItemUse(Player player, ItemStack item) {
        if(this.oneUse && player.getGameMode() != GameMode.CREATIVE){
            destroyItem(item, 1);
        }
    }

    public static void destroyItem(ItemStack item, int amount){
        if(item.getAmount() <= amount){
            item.setAmount(0);
        } else {
            item.setAmount(item.getAmount() - amount);
        }
    }

    /**
     * event when player left clicks air
     * @param player
     * @param item
     * @return
     */
    public abstract boolean leftClickAirAction(Player player, ItemStack item);
    /**
     * event when player left clicks block
     * @param player
     * @param event
     * @param block
     * @param item
     * @return
     */
    public abstract boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item);
    /**
     * event when player shift left clicks air
     * @param player
     * @param item
     * @return
     */
    public abstract boolean shiftleftClickAirAction(Player player, ItemStack item);
    /**
     * event when player shift left clicks block
     * @param player
     * @param event
     * @param block
     * @param item
     * @return
     */
    public abstract boolean shiftleftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item);
    /**
     * event when player right clicks air
     * @param player
     * @param item
     * @return
     */
    public abstract boolean rightClickAirAction(Player player, ItemStack item);
    /**
     * event when player right clicks block
     * @param player
     * @param event
     * @param block
     * @param item
     * @return
     */
    public abstract boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item);
    /**
     * event when player shift right clicks air
     * @param player
     * @param item
     * @return
     */
    public abstract boolean shiftrightClickAirAction(Player player, ItemStack item);
    /**
     * event when player shift right clicks block
     * @param player
     * @param event
     * @param block
     * @param item
     * @return
     */
    public abstract boolean shiftrightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item);
    /**
     * event when player middle clicks
     * @param player
     * @param item
     * @return
     */
    public abstract boolean middleClickAction(Player player, ItemStack item);
    /**
     * event when player breaks block
     * @param player
     * @param event
     * @param block
     * @param item
     * @return
     */
    public abstract boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item);

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public ItemRarity getRarity() {
        return rarity;
    }

    public int getItemID() {
        return itemID;
    }

}
