package dev.luminescent.deezitems.command.argument;

import dev.luminescent.deezitems.manager.MaterialManager;
import dev.luminescent.deezitems.utils.DeezMaterial;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.Argument;
import dev.rosewood.rosegarden.command.framework.ArgumentHandler;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.InputIterator;
import dev.rosewood.rosegarden.utils.StringPlaceholders;

import java.util.Collection;
import java.util.List;

public class MaterialArgumentHandler extends ArgumentHandler<DeezMaterial> {

    private final RosePlugin rosePlugin;

    public MaterialArgumentHandler(RosePlugin rosePlugin) {
        super(DeezMaterial.class);
        this.rosePlugin = rosePlugin;
    }

    @Override
    public DeezMaterial handle(CommandContext commandContext, Argument argument, InputIterator inputIterator) throws HandledArgumentException {
        String input = inputIterator.next();
        DeezMaterial material = this.rosePlugin.getManager(MaterialManager.class).getMaterial(input);
        if (material == null) {
            throw new HandledArgumentException("no-material-found", StringPlaceholders.of("material", input));
        }
        return material;
    }

    @Override
    public List<String> suggest(CommandContext commandContext, Argument argument, String[] args) {
        Collection<DeezMaterial> materials = this.rosePlugin.getManager(MaterialManager.class).getMaterials();
        if(materials.isEmpty()) {
            return List.of("<No material found>");
        }
        return List.of(materials.stream().map(DeezMaterial::getID).toArray(String[]::new));
    }

}
