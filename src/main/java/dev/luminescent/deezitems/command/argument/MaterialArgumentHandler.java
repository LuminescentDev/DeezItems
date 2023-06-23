//package xyz.akiradev.deezitems.command.argument;
//
//import dev.rosewood.rosegarden.RosePlugin;
//import dev.rosewood.rosegarden.command.framework.ArgumentParser;
//import dev.rosewood.rosegarden.command.framework.RoseCommandArgumentHandler;
//import dev.rosewood.rosegarden.command.framework.RoseCommandArgumentInfo;
//import dev.rosewood.rosegarden.utils.StringPlaceholders;
//import xyz.akiradev.deezitems.manager.MaterialManager;
//import xyz.akiradev.deezitems.utils.DeezMaterial;
//
//import java.util.Collection;
//import java.util.List;
//
//public class MaterialArgumentHandler extends RoseCommandArgumentHandler<DeezMaterial> {
//    public MaterialArgumentHandler(RosePlugin rosePlugin) {
//        super(rosePlugin, DeezMaterial.class);
//    }
//
//    @Override
//    protected DeezMaterial handleInternal(RoseCommandArgumentInfo argumentInfo, ArgumentParser argumentParser) throws HandledArgumentException {
//        String input = argumentParser.next();
//        DeezMaterial material = this.rosePlugin.getManager(MaterialManager.class).getMaterial(input);
//        if (material == null) {
//            throw new HandledArgumentException("material-not-found", StringPlaceholders.single("material", input));
//        }
//        return material;
//    }
//
//    @Override
//    protected List<String> suggestInternal(RoseCommandArgumentInfo argumentInfo, ArgumentParser argumentParser) {
//        argumentParser.next();
//
//        Collection<DeezMaterial> materials = this.rosePlugin.getManager(MaterialManager.class).getMaterials();
//        if(materials.isEmpty()) {
//            return List.of("<No material found>");
//        }
//        return List.of(materials.stream().map(DeezMaterial::getID).toArray(String[]::new));
//    }
//}
