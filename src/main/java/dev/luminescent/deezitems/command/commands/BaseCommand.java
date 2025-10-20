package dev.luminescent.deezitems.command.commands;

import java.util.ArrayList;
import java.util.List;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.PrimaryCommand;
import dev.rosewood.rosegarden.command.framework.ArgumentsDefinition;
import dev.rosewood.rosegarden.command.framework.CommandInfo;
import dev.rosewood.rosegarden.command.framework.RoseCommand;

public class BaseCommand extends PrimaryCommand {

  public BaseCommand(RosePlugin rosePlugin) {
    super(rosePlugin);
  }

  @Override
  public CommandInfo createCommandInfo() {

    List<RoseCommand> subcommands = new ArrayList<>(List.of(
        new CommandGive(this.rosePlugin),
        new CommandList(this.rosePlugin)));

    return CommandInfo.builder("deezitems")
        .aliases("ditems", "items")
        .arguments(ArgumentsDefinition.builder()
            .optionalSub(subcommands.toArray(RoseCommand[]::new)))
        .build();
  }

}
