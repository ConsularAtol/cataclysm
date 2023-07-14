package net.consular.cataclysm.registry;


import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.recipe.BewitchingRecipe;
import net.consular.cataclysm.recipe.FletchingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface ModRecipeSerializer<T extends Recipe<?>> {
    public static final RecipeSerializer<FletchingRecipe> FLETCHING = register("fletching", new FletchingRecipe.Serializer());
    public static final RecipeSerializer<BewitchingRecipe> BEWITCHING = register("bewitching", new BewitchingRecipe.Serializer());

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return (S)Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Cataclysm.MODID, id), serializer);
    }

    public static void registerSerializer(){
        Cataclysm.LOGGER.info("Registering recipe serializer");;
    }
}
