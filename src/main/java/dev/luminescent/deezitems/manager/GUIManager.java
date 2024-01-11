package dev.luminescent.deezitems.manager;

import dev.luminescent.deezitems.DeezItems;
import dev.luminescent.deezitems.utils.DeezMaterial;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.manager.Manager;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import dev.luminescent.deezitems.utils.DeezItem;

import java.util.Collection;

public class GUIManager extends Manager {

    private PaginatedGui gui;
    private final LocaleManager localeManager;
    private SHOW showToggle = SHOW.ITEMS;
    private enum SHOW {
        ITEMS,
        MATERIALS
    }

    public GUIManager(RosePlugin rosePlugin) {
        super(rosePlugin);
        DeezItems deezItems = DeezItems.getInstance();
        this.localeManager = deezItems.getManager(LocaleManager.class);
        ItemManager itemManager = deezItems.getManager(ItemManager.class);
        MaterialManager materialManager = deezItems.getManager(MaterialManager.class);
    }

    public void createGui(Player viewer) {
        LocaleManager localeManager = rosePlugin.getManager(LocaleManager.class);
        this.gui = Gui.paginated()
                .title(Component.text(getTitle()))
                .rows(6)
                .create();
        addItems();
        for (int i = 1; i < 10; i++) {
            gui.setItem(6, i, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).name(Component.text("")).asGuiItem());
        }

        addNavigationItems(viewer);
        addTypeSwitchItem(viewer);

        gui.setDefaultClickAction(event -> event.setCancelled(true));
        gui.setCloseGuiAction(event -> {});
    }

    private String getTitle() {
        if(showToggle == SHOW.ITEMS) {
            return localeManager.getLocaleMessage("gui-title-items");
        }else{
            return localeManager.getLocaleMessage("gui-title-materials");
        }
    }

    private void addItems(){
        if(showToggle == SHOW.ITEMS){
            Collection<DeezItem> items = rosePlugin.getManager(ItemManager.class).getItems();
            items.forEach(item -> {
                ItemStack Item = item.generate(1);
                GuiItem guiItem = new GuiItem(Item);
                guiItem.setAction(event -> {
                    HumanEntity player = event.getWhoClicked();
                    if(player.hasPermission("deezitems.give")){
                        player.getInventory().addItem(item.generate(1));
                    }
                });
                gui.addItem(guiItem);
            });
        }else{
            Collection<DeezMaterial> materials = rosePlugin.getManager(MaterialManager.class).getMaterials();
            materials.forEach(material -> {
                ItemStack Item = material.generate(1);
                GuiItem guiItem = new GuiItem(Item);
                guiItem.setAction(event -> {
                    HumanEntity player = event.getWhoClicked();
                    if(player.hasPermission("deezitems.give")){
                        player.getInventory().addItem(material.generate(1));
                    }
                });
                gui.addItem(guiItem);
            });
        }
    }

    private void addNavigationItems(Player viewer){
        gui.setItem(6, 4, ItemBuilder.from(Material.PAPER).name(Component.text("Previous Page")).asGuiItem(event -> gui.previous()));
        gui.setItem(6, 6, ItemBuilder.from(Material.PAPER).name(Component.text("Next Page")).asGuiItem(event -> gui.next()));
    }

    private void addTypeSwitchItem(Player viewer){
        Material typeSwitchMaterial;
        String typeSwitchName;
        int typeSwitchModel;

        if (showToggle == SHOW.MATERIALS) {
            typeSwitchMaterial = Material.IRON_SWORD;
            typeSwitchName = localeManager.getLocaleMessage("gui-materials");
            typeSwitchModel = ConfigurationManager.Setting.GUI_ITEM_MODELS_SHOW_MATERIALS.getInt();
        } else {
            typeSwitchMaterial = Material.IRON_INGOT;
            typeSwitchName = localeManager.getLocaleMessage("gui-items");
            typeSwitchModel = ConfigurationManager.Setting.GUI_ITEM_MODELS_SHOW_ITEMS.getInt();
        }

        GuiItem typeSwitchItem = ItemBuilder
                .from(typeSwitchMaterial)
                .name(Component.text(typeSwitchName))
                .model(typeSwitchModel)
                .asGuiItem();

        typeSwitchItem.setAction(event -> {
            switch(showToggle) {
                case ITEMS -> showToggle = SHOW.MATERIALS;
                case MATERIALS -> showToggle = SHOW.ITEMS;
            }
            openGUI(viewer);
        });
        gui.setItem(6,5, typeSwitchItem);
    }

    @Override
    public void reload() {
        for (Player player :Bukkit.getOnlinePlayers()) {
            player.closeInventory();
        }
    }

    @Override
    public void disable() {

    }

    public void openGUI(Player player) {
        this.createGui(player);
        gui.open(player);
    }

}
