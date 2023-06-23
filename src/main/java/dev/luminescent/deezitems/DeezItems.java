package dev.luminescent.deezitems;

import dev.luminescent.deezitems.defaultitems.items.DeezSword;
import dev.luminescent.deezitems.defaultitems.items.FemboyStick;
import dev.luminescent.deezitems.defaultitems.items.LazerSword;
import dev.luminescent.deezitems.defaultitems.items.TrenchPick;
import dev.luminescent.deezitems.events.block.EventBlockBreak;
import dev.luminescent.deezitems.events.block.EventBlockPlace;
import dev.luminescent.deezitems.events.other.EventProjectileHit;
import dev.luminescent.deezitems.events.player.EventPlayerCraft;
import dev.luminescent.deezitems.events.player.EventPlayerUseDeezItem;
import dev.luminescent.deezitems.manager.*;
import dev.luminescent.deezitems.utils.ItemRarity;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.manager.Manager;

import java.util.List;

public final class DeezItems extends RosePlugin {
    private static DeezItems instance;

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
//        registerDefaultMaterials();
        registerDefaultItems();
        hookIntoPlugins();
        getManager(GUIManager.class).createGui();
    }

    @Override
    protected void disable() {

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

//    public void registerDefaultMaterials() {
//        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
//        materialManager.registerMaterial(new EnchantedStick());
//    }

    public void registerDefaultItems() {
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        itemManager.registerItem(new DeezSword());
        itemManager.registerItem(new LazerSword());
        itemManager.registerItem(new TrenchPick());
        itemManager.registerItem(new FemboyStick());
    }

    @Override
    protected List<Class<? extends Manager>> getManagerLoadPriority() {
        return List.of();
    }

}
