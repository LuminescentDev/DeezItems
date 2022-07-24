package xyz.akiradev.deezitems;

import xyz.akiradev.deezitems.manager.ConfigurationManager;
import xyz.akiradev.deezitems.manager.LocaleManager;
import xyz.akiradev.deezitems.defaultitems.DeezSword;
import xyz.akiradev.deezitems.defaultitems.FemboyStick;
import xyz.akiradev.deezitems.defaultitems.LazerSword;
import xyz.akiradev.deezitems.defaultitems.TrenchPick;
import xyz.akiradev.deezitems.events.other.EventProjectileHit;
import xyz.akiradev.deezitems.events.player.EventPlayerCraft;
import xyz.akiradev.deezitems.utils.DeezItem;
import xyz.akiradev.deezitems.commands.CommandDeez;
import xyz.akiradev.deezitems.events.block.EventBlockBreak;
import xyz.akiradev.deezitems.events.block.EventBlockPlace;
import xyz.akiradev.deezitems.events.player.EventPlayerUseDeezItem;
import xyz.akiradev.deezitems.utils.ItemRarity;
import xyz.akiradev.pluginutils.PluginUtils;
import xyz.akiradev.pluginutils.manager.Manager;

import java.util.*;

public final class DeezItems extends PluginUtils {
    private final Map<String, DeezItem> items = new HashMap<>();
    private final Map<Integer, DeezItem> itemIDs = new HashMap<>();
    private static DeezItems instance;

    public DeezItems() {
        super(103356, 15738, ConfigurationManager.class, LocaleManager.class);
        instance = this;
    }

    @Override
    protected void enable() {
        registerEvents();
        registerCommands();
        registerDefaultItems();
        hookIntoPlugins();
        ItemRarity.loadRaritys();
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
        registerItem("deez_sword", new DeezSword());
        registerItem("lazer_sword", new LazerSword());
        registerItem("trench_pickaxe", new TrenchPick());
        registerItem("femboy_stick", new FemboyStick());
    }

    public void registerCommands(){
        this.getCommand("deezitems").setExecutor(new CommandDeez(this));
        this.getCommand("deezitems").setTabCompleter(new CommandDeez(this));
    }

    public static void registerItem(String name, DeezItem item){
        instance.items.put(name, item);
        instance.itemIDs.put(item.getItemID(), item);
        DeezItems.getInstance().getLogger().info("Registering item: " + name);
    }

    public Collection<DeezItem> getItems() {
        return items.values();
    }

    public Collection<String> getItemNames() {
        return items.keySet();
    }

    public static DeezItem getDeezItem(String name) {
        return instance.items.get(name);
    }

    public static DeezItem getDeezItemFromID(int id) {
        return instance.itemIDs.get(id);
    }

    public static DeezItems getInstance() {
        return instance;
    }

    @Override
    protected List<Class<? extends Manager>> getManagerLoadPriority() {
        return Arrays.asList(
                ConfigurationManager.class,
                LocaleManager.class
        );
    }

}
