package net.consular.cataclysm.registry;

import com.google.gson.JsonObject;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.recipe.FletchingRecipe;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface ModRecipeSerializer<T extends Recipe<?>> {
    public static final RecipeSerializer<FletchingRecipe> FLETCHING = RecipeSerializer.register("fletching", new FletchingRecipe.Serializer());
    /**
     * Reads a recipe from a JSON object.
     * 
     * @implNote If this throws any exception besides {@link com.google.gson.JsonParseException}
     * and {@link IllegalArgumentException}, it will terminate and affect loading
     * of all recipes from data packs beyond the current recipe.
     * 
     * @throws com.google.gson.JsonParseException if the recipe JSON is incorrect
     * @return the read recipe
     * 
     * @param json the recipe JSON
     * @param id the recipe's ID
     */
    public T read(Identifier var1, JsonObject var2);

    /**
     * Reads a recipe from a packet byte buf, usually on the client.
     * 
     * <p>This can throw whatever exception the packet byte buf throws. This may be
     * called in the netty event loop than the client game engine thread.
     * 
     * @return the read recipe
     * 
     * @param buf the recipe buf
     * @param id the recipe's ID
     */
    public T read(Identifier var1, PacketByteBuf var2);

    /**
     * Writes a recipe to a packet byte buf, usually on the server.
     * 
     * <p>The recipe's ID is already written into the buf when this is called.
     * 
     * <p>This can throw whatever exception the packet byte buf throws. This may be
     * called in the netty event loop than the server game engine thread.
     * 
     * @param buf the recipe buf
     * @param recipe the recipe
     */
    public void write(PacketByteBuf var1, T var2);

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return (S)Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Cataclysm.MODID, id), serializer);
    }

    public static void registerSerializer(){
        Cataclysm.LOGGER.info("Registering recipe serializer");;
    }
}
