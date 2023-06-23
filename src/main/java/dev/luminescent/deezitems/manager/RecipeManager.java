package dev.luminescent.deezitems.manager;

import dev.luminescent.deezitems.utils.CustomRecipe;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.manager.Manager;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager extends Manager {

    private final List<CustomRecipe> recipes = new ArrayList<>();

    public RecipeManager(RosePlugin rosePlugin) {
        super(rosePlugin);
    }

    public void Register(CustomRecipe recipe) {
        recipes.add(recipe);
    }

    public List<CustomRecipe> getRecipes() {
        return recipes;
    }

    @Override
    public void reload() {

    }

    @Override
    public void disable() {

    }
}
