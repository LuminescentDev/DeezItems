package dev.luminescent.deezitems.command.commands;

import dev.luminescent.deezitems.manager.GUIManager;
import dev.luminescent.deezitems.manager.ItemManager;
import dev.luminescent.deezitems.manager.LocaleManager;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.RoseCommand;
import dev.rosewood.rosegarden.command.framework.RoseCommandWrapper;
import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;
import dev.rosewood.rosegarden.utils.StringPlaceholders;
import org.bukkit.entity.Player;
import dev.luminescent.deezitems.manager.ConfigurationManager;
import dev.luminescent.deezitems.utils.DeezItem;

import java.util.Collection;

public class CommandList extends RoseCommand {

    public CommandList(RosePlugin rosePlugin, RoseCommandWrapper parent) {
        super(rosePlugin, parent);
    }

    @RoseExecutable
    public void execute(CommandContext context) {
        LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);
        ItemManager itemManager = this.rosePlugin.getManager(ItemManager.class);

        if((!(context.getSender() instanceof Player)) || !ConfigurationManager.Setting.USE_GUI_FOR_LIST.getBoolean()){
            Collection<DeezItem> items = itemManager.getItems();
            if(items.isEmpty()) {
                locale.sendMessage(context.getSender(), "command-list-no-items");
                return;
            }
            locale.sendMessage(context.getSender(), "command-list-header", StringPlaceholders.single("amount", items.size()));
            for(DeezItem item : items) {
                locale.sendMessage(context.getSender(), "command-list-item", StringPlaceholders.builder("item", item.getName())
                        .addPlaceholder("rarityName", item.getRarityName())
                        .addPlaceholder("rarityColor", item.getRarityColor())
                        .addPlaceholder("id", item.getID())
                        .addPlaceholder("material", item.getMaterial())
                        .build());
            }
            return;
        }
        rosePlugin.getManager(GUIManager.class).getGui().open((Player) context.getSender());
    }

    @Override
    protected String getDefaultName() {
        return "list";
    }

    @Override
    public String getDescriptionKey() {
        return "command-list-description";
    }

    @Override
    public String getRequiredPermission() {
        return "deezitems.list";
    }
}
