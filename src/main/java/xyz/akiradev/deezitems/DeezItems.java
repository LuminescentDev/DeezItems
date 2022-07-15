package xyz.akiradev.deezitems;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.akiradev.deezitems.defaultitems.DeezSword;
import xyz.akiradev.deezitems.defaultitems.FemboyStick;
import xyz.akiradev.deezitems.defaultitems.LazerSword;
import xyz.akiradev.deezitems.defaultitems.TrenchPick;
import xyz.akiradev.deezitems.events.other.EventProjectileHit;
import xyz.akiradev.deezitems.utils.DeezItem;
import xyz.akiradev.deezitems.commands.CommandDeez;
import xyz.akiradev.deezitems.events.block.EventBlockBreak;
import xyz.akiradev.deezitems.events.block.EventBlockPlace;
import xyz.akiradev.deezitems.events.player.EventPlayerUseDeezItem;
import xyz.akiradev.deezitems.utils.ConfigManager;
import xyz.akiradev.deezitems.utils.ItemRarity;

import java.util.*;

public final class DeezItems extends JavaPlugin {
    private static Map<String, DeezItem> items = new HashMap();
    private static Map<Integer, DeezItem> itemIDs = new HashMap();
    public static String prefix = "&a[&bDeezItems&a] &8&l";
    private static DeezItems instance;
    private final List<String> hooks = new ArrayList();

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();
        registerDefaultItems();
        ConfigManager.loadConfig();
        hookIntoPlugins();
        enableMetrics();
        ItemRarity.loadRaritys();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void hookIntoPlugins() {
        getServer().getLogger().info("Hooking into plugins...");
    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new EventPlayerUseDeezItem(), this);
        this.getServer().getPluginManager().registerEvents(new EventBlockBreak(), this);
        this.getServer().getPluginManager().registerEvents(new EventBlockPlace(), this);
        this.getServer().getPluginManager().registerEvents(new EventProjectileHit(), this);
    }

    public void enableMetrics(){
        int pluginId = 15738;
        new Metrics(this, pluginId);
    }

    public static void registerDefaultItems() {
        DeezItems.registerItem("deez_sword", new DeezSword());
        DeezItems.registerItem("lazer_sword", new LazerSword());
        DeezItems.registerItem("trench_pickaxe", new TrenchPick());
        DeezItems.registerItem("femboy_stick", new FemboyStick());
    }

    public void registerCommands(){
        this.getCommand("deezitems").setExecutor(new CommandDeez());
        this.getCommand("deezitems").setTabCompleter(new CommandDeez());
    }

    public static void registerItem(String name, DeezItem item){
        items.put(name, item);
        itemIDs.put(item.getItemID(), item);
        DeezItems.getInstance().getLogger().info("Registering item: " + name);
    }

    public static Collection<DeezItem> getItems() {
        return items.values();
    }

    public static Collection<String> getItemNames() {
        return items.keySet();
    }

    public static DeezItem getDeezItem(String name) {
        return items.get(name);
    }

    public static DeezItem getDeezItemFromID(int id) {
        return itemIDs.get(id);
    }

    public static DeezItems getInstance() {
        return instance;
    }
}
