package dev.luminescent.deezitems.command.commands;

import dev.luminescent.deezitems.manager.LocaleManager;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.BaseRoseCommand;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.CommandInfo;
import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;

public class CommandReload extends BaseRoseCommand {

  public CommandReload(RosePlugin rosePlugin) {
    super(rosePlugin);
  }

  @RoseExecutable
  public void execute(CommandContext context) {
    this.rosePlugin.reload();
    this.rosePlugin.getManager(LocaleManager.class).sendCommandMessage(context.getSender(), "reload-success");
  }

  @Override
    protected CommandInfo createCommandInfo() {
        return CommandInfo.builder("reload")
                .descriptionKey("command-reload-description")
                .permission("deezitems.reload")
                .build();
    }

  
}
