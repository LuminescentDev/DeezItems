package xyz.akiradev.deezitems.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemAbility {
    private String abilityName;
    private String abilityDescription;
    private AbilityTypes abilityType;
    private int abilityCooldown;

    public ItemAbility(String abilityName, String abilityDescription, AbilityTypes abilityType, int abilityCooldown) {
        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
        this.abilityType = abilityType;
        this.abilityCooldown = abilityCooldown;
    }

    public ItemAbility(String abilityName, String abilityDescription, AbilityTypes abilityType) {
        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
        this.abilityType = abilityType;
    }

    public List<String> setLore() {
        ArrayList lore = new ArrayList();
        lore.add(TextUtils.colorize(ChatColor.GOLD + "Item Ability: " + this.abilityName + " " + ChatColor.DARK_BLUE + ChatColor.BOLD + this.abilityType.getName()));
        lore.addAll(ItemUtils.convertStringToLore(this.abilityDescription, 40, ChatColor.GRAY));
        if(this.abilityCooldown > 0) {
            lore.add(TextUtils.colorize(ChatColor.GOLD + "Cooldown: " + this.abilityCooldown + " seconds"));
        }

        return lore;
    }

    public static boolean enforceCooldown(Player player, String cooldownName, double cooldown, ItemStack item, boolean sendMessage){
        double systime = (double)System.currentTimeMillis() / 1000.0;
        int cooldownTime = ItemUtils.getIntFromItem(item, cooldownName);
        if(cooldownTime <= 0){
            ItemUtils.storeIntInItem(item, (int)systime, cooldownName);
            return false;
        } else if(systime - cooldown > (double)cooldownTime){
            ItemUtils.storeIntInItem(item, (int)systime, cooldownName);
            return false;
        } else {
            if(sendMessage) {
                player.sendMessage(TextUtils.colorize("&4&lYou must wait " + (cooldownTime - (int)(systime - cooldown)) + " seconds before using this ability again."));
            }
            return true;
        }
    }
}
