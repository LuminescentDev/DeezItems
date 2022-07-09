package xyz.akiradev.deezitems.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import xyz.akiradev.deezitems.DeezItems;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ItemUtils {

    public static final Set<Material> UNBREAKABLE;

    /**
     * set the name of an itemstack
     * @param item - itemstack to be named
     * @param name - name to be set
     */
    public static ItemStack nameItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(TextUtils.colorize(name));
        item.setItemMeta(meta);
        return item;
    }

    /**
     * set the name of a material
     * @param material - material to be named
     * @param name - lore to be set
     */
    public static ItemStack nameItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        return nameItem(item, name);
    }

    /**
     * set the lore of an itemstack
     * @param item - itemstack to be named
     * @param lore - lore to be set
     */
    public static ItemStack setItemLore(ItemStack item, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Give ItemStack enchant glint
     * @param item
     */
    public static void setGlowing(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        item.setItemMeta(meta);
    }

    /**
     * repair an ItemStack if Damageable
     * @param item
     */
    public static void repairItem(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if(meta instanceof Damageable){
            ((Damageable) meta).setDamage(0);
            item.setItemMeta(meta);
        }
    }

    public static void storeStringInItem(ItemStack item, String string, String ID){
        NamespacedKey key = new NamespacedKey(DeezItems.getInstance(), ID);
        if (item != null) {
            if(item.hasItemMeta()){
                ItemMeta meta = item.getItemMeta();
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, string);
                item.setItemMeta(meta);
            }
        }
    }

    public static String getStringFromItem(ItemStack item, String ID){
        NamespacedKey key = new NamespacedKey(DeezItems.getInstance(), ID);
        if (item != null) {
            if(item.hasItemMeta()){
                ItemMeta meta = item.getItemMeta();
                if(meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)){
                    return meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                }
            }
        }
        return null;
    }

    public static void storeIntInItem(ItemStack item, int integer, String ID){
        NamespacedKey key = new NamespacedKey(DeezItems.getInstance(), ID);
        if (item != null) {
            if(item.hasItemMeta()){
                ItemMeta meta = item.getItemMeta();
                meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, integer);
                item.setItemMeta(meta);
            }
        }
    }

    public static int getIntFromItem(ItemStack item, String ID){
        NamespacedKey key = new NamespacedKey(DeezItems.getInstance(), ID);
        if (item != null) {
            if(item.hasItemMeta()){
                ItemMeta meta = item.getItemMeta();
                if(meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)){
                    return meta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
                }
            }
        }
        return 0;
    }

    public static int stringToSeed(String string) {
        if (string == null) {
            return 0;
        } else {
            int seed = 0;
            char[] charArray = string.toCharArray();
            int arrayLength = charArray.length;

            for(int i = 0; i < arrayLength; ++i) {
                char arraychar = charArray[i];
                seed = 31 * seed + arraychar;
            }

            return seed;
        }
    }

    //convert a string to lore with a max line length and a ChatColor before each line
    public static List<String> convertStringToLore(String string, int maxLineLength, ChatColor color){
        List<String> lore = new ArrayList<>();
        String[] lines = string.split("\n");
        for(String line : lines){
            if(line.length() > maxLineLength){
                String[] words = line.split(" ");
                StringBuilder sb = new StringBuilder();
                for(String word : words){
                    if(sb.length() + word.length() + 1 > maxLineLength){
                        lore.add(color + sb.toString());
                        sb = new StringBuilder();
                    }
                    sb.append(word + " ");
                }
                lore.add(color + sb.toString());
            }else{
                lore.add(color + line);
            }
        }
        return lore;
    }

    public static boolean getLookingAtEntity(Player player, Entity target) {
        Location eye = player.getEyeLocation();
        Vector toEntity = target.getLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());

        return dot > 0.990;
    }

    public static List<Entity> entitiesInRange(Player player, double range) {
        List<Entity> entities = new ArrayList<>();
        for (Entity entity : player.getNearbyEntities(range, range, range)) {
            entities.add(entity);
        }
        return entities;
    }

    public static Entity getTargetInRange(Player player, double range) {
            for (Entity entity : entitiesInRange(player, range)) {
                if (getLookingAtEntity(player, entity)) {
                    return entity;
                }
            }
        return null;
    }

    public static Vector getDirectionBetweenLocations(Location Start, Location End) {
        Vector from = Start.toVector();
        Vector to = End.toVector();
        return to.subtract(from);
    }

    public static void delayedTask(Runnable runnable, int delay){
        DeezItems.getInstance().getServer().getScheduler().runTaskLater(DeezItems.getInstance(), runnable, delay);
    }

    public static boolean isDeez(ItemStack item){
        return getIntFromItem(item, "itemID") != 0;
    }

    public static DeezItem getDeezItem(ItemStack item){
        int isDeez = getIntFromItem(item, "itemID");
        return isDeez == 0 ? null : DeezItems.getDeezItemFromID(isDeez);
    }

    static {
        UNBREAKABLE = EnumSet.of(
                Material.END_PORTAL_FRAME,
                Material.STRUCTURE_BLOCK,
                Material.COMMAND_BLOCK,
                Material.NETHER_PORTAL,
                Material.END_GATEWAY,
                Material.END_PORTAL,
                Material.BEDROCK,
                Material.BARRIER
        );
    }
}
