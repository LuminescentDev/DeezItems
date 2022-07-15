package xyz.akiradev.deezitems.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
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
        ArrayList<String> lore = new ArrayList<>();
        lore.add(HexUtils.colorify(ChatColor.GOLD + "Item Ability: " + this.abilityName + " &b&l" + this.abilityType.getName()));
        lore.addAll(ItemUtils.convertStringToLore(this.abilityDescription, 40, ChatColor.GRAY));
        if(this.abilityCooldown > 0) {
            lore.add(HexUtils.colorify(ChatColor.GOLD + "Cooldown: " + this.abilityCooldown + " seconds"));
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
                if(ConfigManager.getConfig().getBoolean("useActionBar.cooldown")){
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(HexUtils.colorify("&c&lYou must wait " + (cooldownTime - (int)(systime - cooldown)) + " seconds before using this ability again.")));
                }else {
                    player.sendMessage(HexUtils.colorify("&c&lYou must wait " + (cooldownTime - (int) (systime - cooldown)) + " seconds before using this ability again."));
                }
            }
            return true;
        }
    }

    public enum AbilityTypes {
        NONE(""),
        LEFT_CLICK("Left click"),
        RIGHT_CLICK("Right click"),
        MIDDLE_CLICK("Middle click"),
        BLOCK_BREAK("Break block"),
        PROJECTILE_HIT("Projectile hit");

        private String name;

        AbilityTypes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
