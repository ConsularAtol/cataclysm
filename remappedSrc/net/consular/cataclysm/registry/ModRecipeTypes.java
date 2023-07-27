package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.recipe.BewitchingRecipe;
import net.consular.cataclysm.recipe.FletchingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface ModRecipeTypes<T extends Recipe<?>> {
    public static final RecipeType<FletchingRecipe> FLETCHING = RecipeType.register("fletching");
    public static final RecipeType<BewitchingRecipe> BEWITCHING = RecipeType.register("bewitching");

    public static <T extends Recipe<?>> RecipeType<T> register(final String id) {
        return Registry.register(Registries.RECIPE_TYPE, new Identifier(Cataclysm.MODID, id), new RecipeType<T>(){

            public String toString() {
                return id;
            }
        });
    }

    public static void registerAllRecipeTypes(){
        Cataclysm.LOGGER.info("Registering recipe types");;
    }
}
