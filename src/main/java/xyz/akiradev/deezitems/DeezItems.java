package xyz.akiradev.deezitems;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.akiradev.deezitems.utils.DeezItem;
import xyz.akiradev.deezitems.commands.CommandDeez;
import xyz.akiradev.deezitems.events.block.EventBlockBreak;
import xyz.akiradev.deezitems.events.block.EventBlockPlace;
import xyz.akiradev.deezitems.events.player.EventPlayerUseDeezItem;
import xyz.akiradev.deezitems.utils.ConfigManager;
import xyz.akiradev.deezitems.utils.RegisterDefaultItems;

import java.util.*;

public final class DeezItems extends JavaPlugin {
    private static Map<String, DeezItem> items = new HashMap();
    private static Map<Integer, DeezItem> itemIDs = new HashMap();
    public static String prefix = "&a[&bDeezItems&a] &8&l";
    private static DeezItems instance;
    private List<String> hooks = new ArrayList();

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();
        RegisterDefaultItems.registerDefaultItems();
        ConfigManager.loadConfig();
        hookIntoPlugins();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void hookIntoPlugins() {
        getServer().getLogger().info("Hooking into plugins...");
        if(getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
            getServer().getLogger().info("Detected ProtocolLib, enabling support.");
            hooks.add("ProtocolLib");
        }
    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new EventPlayerUseDeezItem(), this);
        this.getServer().getPluginManager().registerEvents(new EventBlockBreak(), this);
        this.getServer().getPluginManager().registerEvents(new EventBlockPlace(), this);
    }

    public void registerCommands(){
        this.getCommand("deezitems").setExecutor(new CommandDeez());
        this.getCommand("deezitems").setTabCompleter(new CommandDeez());
    }

    public static void putItem(String name, DeezItem item){
        items.put(name, item);
        itemIDs.put(item.getItemID(), item);
    }

    public static Collection<DeezItem> getItems() {
        return items.values();
    }

    public static Collection<String> getItemNames() {
        return items.keySet();
    }

    public static DeezItem getDeezItem(String name) {
        DeezItem deezItem = (DeezItem) items.get(name);
        return deezItem == null ? (DeezItem)items.get("null") : deezItem;
    }

    public static DeezItem getDeezItemFromID(int id) {
        DeezItem deezItem = (DeezItem) itemIDs.get(id);
        return deezItem == null ? (DeezItem)itemIDs.get("null") : deezItem;
    }

    public static DeezItems getInstance() {
        return instance;
    }
}
