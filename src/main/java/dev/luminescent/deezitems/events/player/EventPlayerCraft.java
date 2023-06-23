package dev.luminescent.deezitems.events.player;

import dev.luminescent.deezitems.manager.ConfigurationManager;
import dev.luminescent.deezitems.utils.CustomRecipe;
import dev.luminescent.deezitems.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class EventPlayerCraft implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCraft(PrepareItemCraftEvent event) {
        ItemStack[] items = event.getInventory().getMatrix();

//        RecipeManager recipeManager = DeezItems.getInstance().getManager(RecipeManager.class);
//
//        for (CustomRecipe customRecipe : recipeManager.getRecipes()) {
//            ItemStack[] recipeMatrix = getRecipeMatrix(customRecipe);
//            if (matchesMatrix(items, recipeMatrix)) {
//                event.getInventory().setResult(customRecipe.getOutput());
//            }
//        }

        if (ConfigurationManager.Setting.CAN_CRAFT_WITH_ITEMS.getBoolean()) return;

        for (ItemStack itemStack : items) {
            if (itemStack != null && ItemUtils.isDeezItem(itemStack)) {
                event.getInventory().setResult(null);
                return;
            }
        }
    }

    private ItemStack[] getRecipeMatrix(CustomRecipe customRecipe) {
        String[] shape = customRecipe.getShape();
        Map<Character, ItemStack> ingredients = customRecipe.getIngredients();

        ItemStack[] matrix = new ItemStack[9];
        int index = 0;
        for (String row : shape) {
            for (char c : row.toCharArray()) {
                matrix[index] = ingredients.getOrDefault(c, new ItemStack(Material.AIR));
                index++;
            }
        }

        return matrix;
    }

    private boolean matchesMatrix(ItemStack[] items, ItemStack[] matrix) {
        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            ItemStack recipeItem = matrix[i];

            if (item == null && recipeItem == null) {
                continue;
            }

            if (item == null || recipeItem == null || !item.isSimilar(recipeItem)) {
                return false;
            }
        }
        return true;
    }


}
