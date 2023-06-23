package dev.luminescent.deezitems.manager;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.manager.Manager;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import dev.luminescent.deezitems.utils.DeezItem;

import java.util.Collection;

public class GUIManager extends Manager {

    private PaginatedGui gui;

    public GUIManager(RosePlugin rosePlugin) {
        super(rosePlugin);
    }

    public void createGui() {
        LocaleManager localeManager = rosePlugin.getManager(LocaleManager.class);
        this.gui = Gui.paginated()
                .title(Component.text(localeManager.getLocaleMessage("gui-title")))
                .rows(6)
                .create();
        Collection<DeezItem> items = rosePlugin.getManager(ItemManager.class).getItems();
        items.forEach(item -> {
            ItemStack Item = item.generate(1);
            GuiItem guiItem = new GuiItem(Item);
            guiItem.setAction(event -> {
                HumanEntity player = event.getWhoClicked();
                if(player.hasPermission("deezitems.give")){
                    player.getInventory().addItem(Item);
                }
            });
            gui.addItem(guiItem);
        });
        for (int i = 1; i < 10; i++) {
            gui.setItem(6, i, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).name(Component.text("")).asGuiItem());
        }
        gui.setItem(6, 4, ItemBuilder.from(Material.PAPER).name(Component.text("Previous Page")).asGuiItem(event -> gui.previous()));
        gui.setItem(6, 6, ItemBuilder.from(Material.PAPER).name(Component.text("Next Page")).asGuiItem(event -> gui.next()));

        gui.setDefaultClickAction(event -> event.setCancelled(true));
        gui.setCloseGuiAction(event -> {});
    }

    @Override
    public void reload() {
        createGui();
    }

    @Override
    public void disable() {

    }

    public PaginatedGui getGui() {
        return gui;
    }
}
