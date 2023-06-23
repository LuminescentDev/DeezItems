package dev.luminescent.deezitems.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class CustomRecipe {

    private final ItemStack output;
    private final Map<Character, ItemStack> ingredients = new HashMap<>();
    private String[] shape;

    public CustomRecipe(ItemStack output) {
        this.output = output;
    }

    public void shape(String... shape) {
        this.shape = new String[shape.length];
        System.arraycopy(shape, 0, this.shape, 0, shape.length);

        Map<Character, ItemStack> newIngredients = new HashMap<>();
        for (String row : shape) {
            for (char c : row.toCharArray()) {
                newIngredients.put(c, ingredients.get(c));
            }
        }
        ingredients.clear();
        ingredients.putAll(newIngredients);

    }

    public void setIngredient(char c, Material mat) {
        setIngredient(c, new ItemStack(mat, 1));
    }

    public void setIngredient(char c, ItemStack item) {
        ingredients.put(c, item);
    }

    public ItemStack getOutput() {
        return output;
    }

    public ItemMeta getItemMeta() {
        return output.getItemMeta();
    }

    public void setItemMeta(ItemMeta meta) {
        output.setItemMeta(meta);
    }

    public String[] getShape() {
        return shape;
    }

    public Map<Character, ItemStack> getIngredients() {
        return ingredients;
    }
}
