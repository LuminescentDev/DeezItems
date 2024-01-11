package dev.luminescent.deezitems.command.argument;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.ArgumentParser;
import dev.rosewood.rosegarden.command.framework.RoseCommandArgumentHandler;
import dev.rosewood.rosegarden.command.framework.RoseCommandArgumentInfo;
import dev.rosewood.rosegarden.utils.StringPlaceholders;
import dev.luminescent.deezitems.manager.ItemManager;
import dev.luminescent.deezitems.utils.DeezItem;

import java.util.Collection;
import java.util.List;

public class ItemArgumentHandler extends RoseCommandArgumentHandler<DeezItem> {

    public ItemArgumentHandler(RosePlugin rosePlugin) {
        super(rosePlugin, DeezItem.class);
    }

    @Override
    protected DeezItem handleInternal(RoseCommandArgumentInfo argumentInfo, ArgumentParser argumentParser) throws HandledArgumentException {
        String input = argumentParser.next();
        DeezItem item = this.rosePlugin.getManager(ItemManager.class).getItem(input);
        if (item == null) {
            throw new HandledArgumentException("no-item-found", StringPlaceholders.single("item", input));
        }
        return item;
    }

    @Override
    protected List<String> suggestInternal(RoseCommandArgumentInfo argumentInfo, ArgumentParser argumentParser) {
        argumentParser.next();

        Collection<DeezItem> items = this.rosePlugin.getManager(ItemManager.class).getItems();
        if(items.isEmpty()) {
            return List.of("<No item found>");
        }
        return List.of(items.stream().map(DeezItem::getID).toArray(String[]::new));
    }
}
