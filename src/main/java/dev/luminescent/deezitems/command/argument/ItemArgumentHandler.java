package dev.luminescent.deezitems.command.argument;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.Argument;
import dev.rosewood.rosegarden.command.framework.ArgumentHandler;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.InputIterator;
import dev.rosewood.rosegarden.utils.StringPlaceholders;
import dev.luminescent.deezitems.manager.ItemManager;
import dev.luminescent.deezitems.utils.DeezItem;

import java.util.Collection;
import java.util.List;

public class ItemArgumentHandler extends ArgumentHandler<DeezItem>{

    private final RosePlugin rosePlugin;

    public ItemArgumentHandler(RosePlugin rosePlugin) {
        super(DeezItem.class);
        this.rosePlugin = rosePlugin;
    }

    @Override
    public DeezItem handle(CommandContext commandContext, Argument argument, InputIterator inputIterator) throws HandledArgumentException {
        String input = inputIterator.next();
        DeezItem item = this.rosePlugin.getManager(ItemManager.class).getItem(input);
        if (item == null) {
            throw new HandledArgumentException("no-item-found", StringPlaceholders.of("item", input));
        }
        return item;
    }

    @Override
    public List<String> suggest(CommandContext commandContext, Argument argument, String[] args) {
        Collection<DeezItem> items = this.rosePlugin.getManager(ItemManager.class).getItems();
        if(items.isEmpty()) {
            return List.of("<No item found>");
        }
        return List.of(items.stream().map(DeezItem::getID).toArray(String[]::new));
    }
}
