package dev.luminescent.deezitems.command.commands;

import dev.luminescent.deezitems.manager.*;
import dev.luminescent.deezitems.utils.DeezMaterial;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.RoseCommand;
import dev.rosewood.rosegarden.command.framework.RoseCommandWrapper;
import dev.rosewood.rosegarden.command.framework.RoseSubCommand;
import dev.rosewood.rosegarden.command.framework.annotation.Optional;
import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;
import dev.rosewood.rosegarden.utils.StringPlaceholders;
import org.bukkit.entity.Player;
import dev.luminescent.deezitems.utils.DeezItem;

import java.util.Collection;

public class CommandList extends RoseCommand {

    public CommandList(RosePlugin rosePlugin, RoseCommandWrapper parent) {
        super(rosePlugin, parent);
    }

    @RoseExecutable
    public void execute(CommandContext context, @Optional RoseSubCommand subCommand) {
        LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);
        ItemManager itemManager = this.rosePlugin.getManager(ItemManager.class);

        if((!(context.getSender() instanceof Player)) || !ConfigurationManager.Setting.USE_GUI_FOR_LIST.getBoolean()){
            Collection<DeezItem> items = itemManager.getItems();
            if(items.isEmpty()) {
                locale.sendMessage(context.getSender(), "command-list-none-item");
                return;
            }
            locale.sendMessage(context.getSender(), "command-list-header-item", StringPlaceholders.single("amount", items.size()));
            for(DeezItem item : items) {
                locale.sendMessage(context.getSender(), "command-list-item", StringPlaceholders.builder("name", item.getName())
                        .addPlaceholder("rarityName", item.getRarityName())
                        .addPlaceholder("rarityColor", item.getRarityColor())
                        .addPlaceholder("id", item.getID())
                        .addPlaceholder("material", item.getMaterial())
                        .build());
            }
            return;
        }
        rosePlugin.getManager(GUIManager.class).openGUI((Player) context.getSender());
    }

    public static class SubCommandMaterials extends RoseSubCommand {
        public SubCommandMaterials(RosePlugin rosePlugin, RoseCommandWrapper parent) {super(rosePlugin, parent);}

        @RoseExecutable
        public void execute(CommandContext context){
            LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);
            MaterialManager materialManager = this.rosePlugin.getManager(MaterialManager.class);

            Collection<DeezMaterial> materials = materialManager.getMaterials();
            if(materials.isEmpty()) {
                locale.sendMessage(context.getSender(), "command-list-none-material");
                return;
            }
            locale.sendMessage(context.getSender(), "command-list-header-material", StringPlaceholders.single("amount", materials.size()));
            for(DeezMaterial material : materials) {
                locale.sendMessage(context.getSender(), "command-list-material", StringPlaceholders.builder("name", material.getName())
                        .addPlaceholder("rarityName", material.getRarityName())
                        .addPlaceholder("rarityColor", material.getRarityColor())
                        .addPlaceholder("id", material.getID())
                        .addPlaceholder("material", material.getMaterial())
                        .build());
            }
        }

        @Override
        protected String getDefaultName() {
            return "Materials";
        }
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
