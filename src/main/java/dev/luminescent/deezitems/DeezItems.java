package dev.luminescent.deezitems;

import dev.luminescent.deezitems.defaultitems.items.DeezSword;
import dev.luminescent.deezitems.defaultitems.items.FemboyStick;
import dev.luminescent.deezitems.defaultitems.items.LazerSword;
import dev.luminescent.deezitems.defaultitems.items.TrenchPick;
import dev.luminescent.deezitems.defaultitems.materials.ExampleMaterial;
import dev.luminescent.deezitems.events.block.EventBlockBreak;
import dev.luminescent.deezitems.events.block.EventBlockPlace;
import dev.luminescent.deezitems.events.other.EventProjectileHit;
import dev.luminescent.deezitems.events.player.EventPlayerCraft;
import dev.luminescent.deezitems.events.player.EventPlayerUseDeezItem;
import dev.luminescent.deezitems.manager.*;
import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.ItemRarity;
import dev.luminescent.deezitems.utils.ItemUtils;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public final class DeezItems extends RosePlugin {
    private static DeezItems instance;

    private int activeEffectTaskId;

    public DeezItems() {
        super(103356, 15738, ConfigurationManager.class, null, LocaleManager.class, CommandManager.class);
        instance = this;
    }

    public static DeezItems getInstance() {
        return instance;
    }

    @Override
    protected void enable() {
        registerEvents();
        ItemRarity.loadRaritys();
        registerDefaultMaterials();
        registerDefaultItems();
        hookIntoPlugins();
        activeEffectTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, DeezItems::doActiveEffects, 2L, 2L);
    }

    @Override
    protected void disable() {
        Bukkit.getScheduler().cancelTask(activeEffectTaskId);
    }

    public void hookIntoPlugins() {
        getServer().getLogger().info("Hooking into plugins...");
    }

    public void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new EventPlayerUseDeezItem(), this);
        this.getServer().getPluginManager().registerEvents(new EventBlockBreak(), this);
        this.getServer().getPluginManager().registerEvents(new EventBlockPlace(), this);
        this.getServer().getPluginManager().registerEvents(new EventProjectileHit(), this);
        this.getServer().getPluginManager().registerEvents(new EventPlayerCraft(), this);
    }

    public void registerDefaultMaterials() {
        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
        materialManager.registerMaterial(new ExampleMaterial());
    }

    public void registerDefaultItems() {
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        itemManager.registerItem(new DeezSword());
        itemManager.registerItem(new LazerSword());
        itemManager.registerItem(new TrenchPick());
        itemManager.registerItem(new FemboyStick());
    }

    private static void doActiveEffects() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerInventory inventory = player.getInventory();

            ItemStack mainHand = inventory.getItemInMainHand();
            if (ItemUtils.isDeezItem(mainHand)) {
                DeezItem deezItem = ItemUtils.getDeezItem(mainHand);
                if (deezItem != null && deezItem.hasActiveEffect) {
                    deezItem.activeEffect(player, mainHand);
                }
            }

            ItemStack offHand = inventory.getItemInOffHand();
            if (ItemUtils.isDeezItem(offHand)) {
                DeezItem deezItem = ItemUtils.getDeezItem(offHand);
                if (deezItem != null && deezItem.hasActiveEffect) {
                    deezItem.activeEffect(player, offHand);
                }
            }

            ItemStack[] armorContents = inventory.getArmorContents();
            for (ItemStack armorItem : armorContents) {
                if (ItemUtils.isDeezItem(armorItem)) {
                    DeezItem deezItem = ItemUtils.getDeezItem(armorItem);
                    if (deezItem != null && deezItem.hasActiveEffect) {
                        deezItem.activeEffect(player, armorItem);
                    }
                }
            }
        }
    }


    @Override
    protected List<Class<? extends Manager>> getManagerLoadPriority() {
        return List.of();
    }

}
