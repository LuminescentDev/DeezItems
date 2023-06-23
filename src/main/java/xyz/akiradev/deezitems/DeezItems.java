package xyz.akiradev.deezitems;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.manager.Manager;
import xyz.akiradev.deezitems.defaultitems.items.*;
//import xyz.akiradev.deezitems.defaultitems.materials.EnchantedStick;
import xyz.akiradev.deezitems.manager.*;
import xyz.akiradev.deezitems.events.other.EventProjectileHit;
import xyz.akiradev.deezitems.events.player.EventPlayerCraft;
import xyz.akiradev.deezitems.events.block.EventBlockBreak;
import xyz.akiradev.deezitems.events.block.EventBlockPlace;
import xyz.akiradev.deezitems.events.player.EventPlayerUseDeezItem;
import xyz.akiradev.deezitems.utils.ItemRarity;

import java.util.*;

public final class DeezItems extends RosePlugin {
    private static DeezItems instance;

    public DeezItems() {
        super(103356, 15738, ConfigurationManager.class, null, LocaleManager.class, CommandManager.class);
        instance = this;
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

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new EventPlayerUseDeezItem(), this);
        this.getServer().getPluginManager().registerEvents(new EventBlockBreak(), this);
        this.getServer().getPluginManager().registerEvents(new EventBlockPlace(), this);
        this.getServer().getPluginManager().registerEvents(new EventProjectileHit(), this);
        this.getServer().getPluginManager().registerEvents(new EventPlayerCraft(), this);
    }

    public void registerDefaultItems() {
        ItemManager itemManager = DeezItems.getInstance().getManager(ItemManager.class);
        itemManager.registerItem(new DeezSword());
        itemManager.registerItem(new LazerSword());
        itemManager.registerItem(new TrenchPick());
        itemManager.registerItem(new FemboyStick());
    }

//    public void registerDefaultMaterials() {
//        MaterialManager materialManager = DeezItems.getInstance().getManager(MaterialManager.class);
//        materialManager.registerMaterial(new EnchantedStick());
//    }

    public static DeezItems getInstance() {
        return instance;
    }

    @Override
    protected List<Class<? extends Manager>> getManagerLoadPriority() {
        return List.of();
    }

}
