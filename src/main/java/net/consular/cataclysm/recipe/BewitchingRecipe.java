package net.consular.cataclysm.recipe;

import com.google.gson.JsonObject;
import java.util.stream.Stream;

import net.consular.cataclysm.registry.ModBlocks;
import net.consular.cataclysm.registry.ModRecipeSerializer;
import net.consular.cataclysm.registry.ModRecipeTypes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class BewitchingRecipe
implements Recipe<Inventory> {
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;
    private final Identifier id;

    public BewitchingRecipe(Identifier id, Ingredient base, Ingredient addition, ItemStack result) {
        this.id = id;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if(world.isClient()) {
            return false;
        }

        return this.base.test(inventory.getStack(0)) && this.addition.test(inventory.getStack(1));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager var2) {
        ItemStack itemStack = this.result.copy();
        NbtCompound nbtCompound = inventory.getStack(0).getNbt();
        if (nbtCompound != null) {
            itemStack.setNbt(nbtCompound.copy());
        }
        return itemStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager var2) {
        return this.result;
    }

    public boolean testAddition(ItemStack stack) {
        return this.addition.test(stack);
    }

    public boolean testBase(ItemStack stack){
        return this.base.test(stack);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.BEWITCHING_TABLE);
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.BEWITCHING;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.BEWITCHING;
    }

    @Override
    public boolean isEmpty() {
        return Stream.of(this.base, this.addition).anyMatch(ingredient -> ingredient.getMatchingStacks().length == 0);
    }

    public static class Serializer
    implements RecipeSerializer<BewitchingRecipe> {
        @Override
        public BewitchingRecipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
            ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
            return new BewitchingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        @Override
        public BewitchingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new BewitchingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, BewitchingRecipe smithingRecipe) {
            smithingRecipe.base.write(packetByteBuf);
            smithingRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeItemStack(smithingRecipe.result);
        }
    }
}