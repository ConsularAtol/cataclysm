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

    public static <T extends Recipe<?>> RecipeType<T> register(final String id) {
        return Registry.register(Registries.RECIPE_TYPE, new Identifier(Cataclysm.MODID, id), new RecipeType<T>(){

            public String toString() {
                return id;
            }
        });
    }

    public static void registerAllRecipeTypes(){
        Cataclysm.LOGGER.info("Registering recipe types");;
        registerRecipes();
    }

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Cataclysm.MODID, BewitchingRecipe.Serializer.ID),
                BewitchingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Cataclysm.MODID, BewitchingRecipe.Type.ID),
                BewitchingRecipe.Type.INSTANCE);
    }
}
