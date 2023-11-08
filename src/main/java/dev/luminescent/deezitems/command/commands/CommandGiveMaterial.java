//package xyz.akiradev.deezitems.command.commands;
//
//import dev.rosewood.rosegarden.RosePlugin;
//import dev.rosewood.rosegarden.command.framework.CommandContext;
//import dev.rosewood.rosegarden.command.framework.RoseCommand;
//import dev.rosewood.rosegarden.command.framework.RoseCommandWrapper;
//import dev.rosewood.rosegarden.command.framework.annotation.Optional;
//import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;
//import dev.rosewood.rosegarden.utils.StringPlaceholders;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//import xyz.akiradev.deezitems.manager.LocaleManager;
//import xyz.akiradev.deezitems.utils.DeezMaterial;
//
//public class CommandGiveMaterial extends RoseCommand {
//
//    public CommandGiveMaterial(RosePlugin rosePlugin, RoseCommandWrapper parent) {
//        super(rosePlugin, parent);
//    }
//
//    @RoseExecutable
//    public void execute(CommandContext context, DeezMaterial material, @Optional Integer amount, @Optional Player target) {
//        LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);
//
//        if(amount == null) amount = 1;
//        if(target == null) target = (Player) context.getSender();
//        ItemStack itemstack = material.makeItem(amount);
//        target.getInventory().addItem(itemstack);
//        locale.sendMessage(context.getSender(), "item-given", StringPlaceholders.single("item", material.getName()));
//    }
//
//    @Override
//    protected String getDefaultName() {
//        return "givematerial";
//    }
//
//    @Override
//    public String getDescriptionKey() {
//        return "command-givematerial-description";
//    }
//
//    @Override
//    public String getRequiredPermission() {
//        return "deezitems.give";
//    }
//}