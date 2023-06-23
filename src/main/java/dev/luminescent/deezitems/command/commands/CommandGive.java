package dev.luminescent.deezitems.command.commands;

import dev.luminescent.deezitems.manager.LocaleManager;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.RoseCommand;
import dev.rosewood.rosegarden.command.framework.RoseCommandWrapper;
import dev.rosewood.rosegarden.command.framework.annotation.Optional;
import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;
import dev.rosewood.rosegarden.utils.StringPlaceholders;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import dev.luminescent.deezitems.utils.DeezItem;

public class CommandGive extends RoseCommand {

    public CommandGive(RosePlugin rosePlugin, RoseCommandWrapper parent) {
        super(rosePlugin, parent);
    }

    @RoseExecutable
    public void execute(CommandContext context, DeezItem item, @Optional Integer amount, @Optional Player target) {
        LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);
        if(amount == null) amount = 1;
        if(target == null) target = (Player) context.getSender();
        ItemStack itemstack = item.generate(amount);
        target.getInventory().addItem(itemstack);
        locale.sendMessage(context.getSender(), "item-given", StringPlaceholders.single("item", item.getName()));
    }


    @Override
    protected String getDefaultName() {
        return "give";
    }

    @Override
    public String getDescriptionKey() {
        return "command-give-description";
    }

    @Override
    public String getRequiredPermission() {
        return "deezitems.give";
    }
}
