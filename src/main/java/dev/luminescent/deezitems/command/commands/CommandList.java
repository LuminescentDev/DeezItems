package dev.luminescent.deezitems.command.commands;

import dev.luminescent.deezitems.command.commands.CommandGive.SubCommandGiveItem;
import dev.luminescent.deezitems.command.commands.CommandGive.SubCommandGiveMaterial;
import dev.luminescent.deezitems.manager.*;
import dev.luminescent.deezitems.utils.DeezMaterial;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.ArgumentsDefinition;
import dev.rosewood.rosegarden.command.framework.BaseRoseCommand;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.CommandInfo;
import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;
import dev.rosewood.rosegarden.utils.StringPlaceholders;
import org.bukkit.entity.Player;
import dev.luminescent.deezitems.utils.DeezItem;

import java.util.Collection;

public class CommandList extends BaseRoseCommand {

    public CommandList(RosePlugin rosePlugin) {
        super(rosePlugin);
    }

    protected CommandInfo createCommandInfo() {
        return CommandInfo.builder("list")
                .arguments(ArgumentsDefinition.builder()
                        .optionalSub(new SubCommandMaterials(rosePlugin)))
                .build();
    }

    @RoseExecutable
    public void execute(CommandContext context) {
        LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);
        ItemManager itemManager = this.rosePlugin.getManager(ItemManager.class);

        if ((!(context.getSender() instanceof Player)) || !Settings.USE_GUI_FOR_LIST.get()) {
            Collection<DeezItem> items = itemManager.getItems();
            if (items.isEmpty()) {
                locale.sendMessage(context.getSender(), "command-list-none-item");
                return;
            }
            locale.sendMessage(context.getSender(), "command-list-header-item",
                    StringPlaceholders.of("amount", items.size()));
            for (DeezItem item : items) {
                locale.sendMessage(context.getSender(), "command-list-item",
                        StringPlaceholders.builder("name", item.getName())
                                .add("rarityName", item.getRarityName())
                                .add("rarityColor", item.getRarityColor())
                                .add("id", item.getID())
                                .add("material", item.getMaterial())
                                .build());
            }
            return;
        }
        rosePlugin.getManager(GUIManager.class).openGUI((Player) context.getSender());
    }

    public static class SubCommandMaterials extends BaseRoseCommand {

        public SubCommandMaterials(RosePlugin rosePlugin) {
            super(rosePlugin);
        }

        @RoseExecutable
        public void execute(CommandContext context) {
            LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);
            MaterialManager materialManager = this.rosePlugin.getManager(MaterialManager.class);

            Collection<DeezMaterial> materials = materialManager.getMaterials();
            if (materials.isEmpty()) {
                locale.sendMessage(context.getSender(), "command-list-none-material");
                return;
            }
            locale.sendMessage(context.getSender(), "command-list-header-material",
                    StringPlaceholders.of("amount", materials.size()));
            for (DeezMaterial material : materials) {
                locale.sendMessage(context.getSender(), "command-list-material",
                        StringPlaceholders.builder("name", material.getName())
                                .add("rarityName", material.getRarityName())
                                .add("rarityColor", material.getRarityColor())
                                .add("id", material.getID())
                                .add("material", material.getMaterial())
                                .build());
            }
        }

        protected CommandInfo createCommandInfo() {
            return CommandInfo.builder("materials")
                    .arguments(ArgumentsDefinition.builder()
                            .requiredSub(new SubCommandGiveItem(rosePlugin),
                                    new SubCommandGiveMaterial(rosePlugin)))
                    .build();
        }
    }
}
