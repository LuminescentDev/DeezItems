package dev.luminescent.deezitems.command;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.RoseCommandWrapper;

import java.util.List;

public class DeezCommandWrapper extends RoseCommandWrapper {

    public DeezCommandWrapper(RosePlugin rosePlugin) {
        super(rosePlugin);
    }

    @Override
    public String getDefaultName() {
        return "deezitems";
    }

    @Override
    public List<String> getDefaultAliases() {
        return List.of("ditems", "items");
    }

    @Override
    public List<String> getCommandPackages() {
        return List.of("xyz.akiradev.deezitems.command.commands");
    }

    @Override
    public boolean includeBaseCommand() {
        return true;
    }

    @Override
    public boolean includeHelpCommand() {
        return true;
    }

    @Override
    public boolean includeReloadCommand() {
        return true;
    }
}
