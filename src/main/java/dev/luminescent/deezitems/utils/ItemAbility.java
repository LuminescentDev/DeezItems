package dev.luminescent.deezitems.utils;

import dev.luminescent.deezitems.DeezItems;
import dev.luminescent.deezitems.manager.ConfigurationManager;
import dev.luminescent.deezitems.manager.LocaleManager;
import dev.rosewood.rosegarden.utils.HexUtils;
import dev.rosewood.rosegarden.utils.StringPlaceholders;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ItemAbility {

    private static final LocaleManager localeManager = DeezItems.getInstance().getManager(LocaleManager.class);
    private final String abilityName;
    private final String abilityDescription;
    private final AbilityTypes abilityType;
    private float abilityCooldown;

    public ItemAbility(String abilityName, String abilityDescription, AbilityTypes abilityType, float abilityCooldown) {
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
        List<String> lore = new ArrayList<>();
        lore.add(HexUtils.colorify(ChatColor.GOLD + "Item Ability: " + this.abilityName + " &b&l" + this.abilityType.getName()));
        lore.addAll(ItemUtils.convertStringToLore(this.abilityDescription, 40, ChatColor.GRAY));
        if (this.abilityCooldown > 0) {
            lore.add(HexUtils.colorify(ChatColor.GOLD + "Cooldown: " + this.abilityCooldown + " seconds"));
        }
        return lore;
    }

    public boolean enforceCooldown(Player player, ItemStack item, boolean sendMessage) {
        double systime = (double) System.currentTimeMillis() / 1000.0;
        int cooldownTime = ItemUtils.getIntFromItem(item, abilityName);
        if (cooldownTime <= 0) {
            ItemUtils.storeIntInItem(item, (int) systime, abilityName);
            return false;
        } else if (systime - abilityCooldown > (double) cooldownTime) {
            ItemUtils.storeIntInItem(item, (int) systime, abilityName);
            return false;
        } else {
            if (ConfigurationManager.Setting.USE_ACTIONBAR.getBoolean()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(HexUtils.colorify(
                        localeManager.getLocaleMessage("cooldown-message", StringPlaceholders.single("time", (cooldownTime - (int) (systime - abilityCooldown))))
                )));
            } else {
                localeManager.sendMessage(player, "cooldown-message", StringPlaceholders.single("time", (cooldownTime - (int) (systime - abilityCooldown))));
            }
        }
        return true;
    }

    public enum AbilityTypes {
        NONE(""),
        LEFT_CLICK(localeManager.getLocaleMessage("left-click")),
        RIGHT_CLICK(localeManager.getLocaleMessage("right-click")),
        MIDDLE_CLICK(localeManager.getLocaleMessage("middle-click")),
        BLOCK_BREAK(localeManager.getLocaleMessage("block-break")),
        PROJECTILE_HIT(localeManager.getLocaleMessage("projectile-hit"));

        private final String name;

        AbilityTypes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
