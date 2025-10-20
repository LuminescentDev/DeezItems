package dev.luminescent.deezitems.command.commands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.luminescent.deezitems.command.argument.DeezArgumentHandlers;
import dev.luminescent.deezitems.manager.LocaleManager;
import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.DeezMaterial;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.argument.ArgumentHandlers;
import dev.rosewood.rosegarden.command.framework.ArgumentsDefinition;
import dev.rosewood.rosegarden.command.framework.BaseRoseCommand;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.CommandInfo;
import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;
import dev.rosewood.rosegarden.utils.StringPlaceholders;

public class CommandGive extends BaseRoseCommand {

  public CommandGive(RosePlugin rosePlugin) {
    super(rosePlugin);
  }

  @Override
  protected CommandInfo createCommandInfo() {
    return CommandInfo.builder("give")
        .arguments(ArgumentsDefinition.builder()
            .requiredSub(new SubCommandGiveItem(rosePlugin),
                new SubCommandGiveMaterial(rosePlugin)))
        .build();
  }

  public static class SubCommandGiveItem extends BaseRoseCommand {

    public SubCommandGiveItem(RosePlugin rosePlugin) {
      super(rosePlugin);
    }

    @RoseExecutable
    public void execute(CommandContext context, DeezItem item, Integer amount, Player target) {
      LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);
      if (amount == null)
        amount = 1;
      if (target == null)
        target = (Player) context.getSender();
      ItemStack itemstack = item.generate(amount);
      target.getInventory().addItem(itemstack);
      locale.sendMessage(context.getSender(), "item-given", StringPlaceholders.of("item", item.getName()));
    }

    @Override
    protected CommandInfo createCommandInfo() {
      return CommandInfo.builder("item")
          .descriptionKey("command-give-description")
          .permission("deezitems.give")
          .arguments(ArgumentsDefinition.builder()
              .required("item", DeezArgumentHandlers.ITEM)
              .optional("amount", ArgumentHandlers.INTEGER)
              .optional("target", ArgumentHandlers.PLAYER)
              .build())
          .build();
    }
  }

  public static class SubCommandGiveMaterial extends BaseRoseCommand {

    public SubCommandGiveMaterial(RosePlugin rosePlugin) {
      super(rosePlugin);
    }

    @RoseExecutable
    public void execute(CommandContext context, DeezMaterial item, Integer amount, Player target) {
      LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);
      if (amount == null)
        amount = 1;
      if (target == null)
        target = (Player) context.getSender();
      ItemStack itemstack = item.generate(amount);
      target.getInventory().addItem(itemstack);
      locale.sendMessage(context.getSender(), "item-given", StringPlaceholders.of("item", item.getName()));
    }

    @Override
    protected CommandInfo createCommandInfo() {
      return CommandInfo.builder("material")
          .descriptionKey("command-give-description")
          .permission("deezitems.give")
          .arguments(ArgumentsDefinition.builder()
              .required("item", DeezArgumentHandlers.MATERIAL)
              .optional("amount", ArgumentHandlers.INTEGER)
              .optional("target", ArgumentHandlers.PLAYER)
              .build())
          .build();
    }
  }

}
