package net.consular.cataclysm.recipe;

import com.google.gson.JsonObject;
import java.util.stream.Stream;

import net.consular.cataclysm.item.ModArrowItem;
import net.consular.cataclysm.item.ModTippedArrowItem;
import net.consular.cataclysm.registry.ModRecipeSerializer;
import net.consular.cataclysm.registry.ModRecipeTypes;
import net.consular.cataclysm.util.ArrowType;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class FletchingRecipe
implements Recipe<Inventory> {
    public final Ingredient base;
    public final Ingredient addition;
    final ItemStack result;
    private final Identifier id;

    public FletchingRecipe(Identifier id, Ingredient base, Ingredient addition, ItemStack result) {
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

        if (inventory.getStack(0).isIn(ItemTags.ARROWS) && inventory.getStack(1).isOf(Items.LINGERING_POTION))
            return true;
        return this.base.test(inventory.getStack(0)) && this.addition.test(inventory.getStack(1));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager var2) {
        ItemStack itemStack = this.result.copy();
        ItemStack baseItem = inventory.getStack(0);
        ItemStack additionItem = inventory.getStack(1);
        NbtCompound nbtCompound = inventory.getStack(0).getNbt();
        if (baseItem.isIn(ItemTags.ARROWS) && additionItem.isOf(Items.LINGERING_POTION)){
            if(baseItem.isOf(Items.ARROW))
                itemStack = new ItemStack(Items.TIPPED_ARROW);
            else if (baseItem.getItem() instanceof ModArrowItem)
                itemStack = ArrowType.getTippedStack(ArrowType.getTypeFromStack(baseItem));
            if (baseItem.isOf(Items.TIPPED_ARROW) || baseItem.getItem() instanceof ModTippedArrowItem)
                itemStack = new ItemStack(Items.AIR);
            PotionUtil.setPotion(itemStack, PotionUtil.getPotion(additionItem));
            PotionUtil.setCustomPotionEffects(itemStack, PotionUtil.getCustomPotionEffects(additionItem));
        }
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
    
    public ItemStack getResultItem(DynamicRegistryManager var2) {
        return this.result;
    }

    public boolean testAddition(ItemStack stack) {
        return this.addition.test(stack) || stack.isOf(Items.LINGERING_POTION);
    }

    public boolean testBase(ItemStack stack){
        return this.base.test(stack) || stack.isIn(ItemTags.ARROWS);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.FLETCHING_TABLE);
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.FLETCHING;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.FLETCHING;
    }

    @Override
    public boolean isEmpty() {
        return Stream.of(this.base, this.addition).anyMatch(ingredient -> ingredient.getMatchingStacks().length == 0);
    }

    public static class Serializer
    implements RecipeSerializer<FletchingRecipe> {
        @Override
        public FletchingRecipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
            ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
            return new FletchingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        @Override
        public FletchingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new FletchingRecipe(identifier, ingredient, ingredient2, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, FletchingRecipe smithingRecipe) {
            smithingRecipe.base.write(packetByteBuf);
            smithingRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeItemStack(smithingRecipe.result);
        }
    }
}