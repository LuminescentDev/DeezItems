//package xyz.akiradev.deezitems.defaultitems.materials;
//
//import org.bukkit.Material;
//import org.bukkit.NamespacedKey;
//import xyz.akiradev.deezitems.DeezItems;
//import xyz.akiradev.deezitems.manager.MaterialManager;
//import xyz.akiradev.deezitems.manager.RecipeManager;
//import xyz.akiradev.deezitems.utils.CustomRecipe;
//import xyz.akiradev.deezitems.utils.DeezMaterial;
//
//public class EnchantedStick extends DeezMaterial {
//
//    public EnchantedStick() {
//        super(
//                "enchanted_stick",
//                Material.STICK,
//                "Enchanted Stick",
//                "Example Material",
//                "Unfinished",
//                true,
//                true,
//                true,
//                -1);
//    }
//
//    @Override
//    public void registerRecipe() {
//        CustomRecipe recipe = new CustomRecipe(this.makeItem(1));
//        recipe.shape("SSS", "SSS", "SSS");
//        recipe.setIngredient('S', Material.STICK);
//        DeezItems.getInstance().getManager(RecipeManager.class).Register(recipe);
//    }
//
//
//}
