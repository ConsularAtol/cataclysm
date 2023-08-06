package net.consular.cataclysm.screen;

import java.util.List;

import net.consular.cataclysm.item.ScrollItem;
import net.consular.cataclysm.recipe.BewitchingRecipe;
import net.consular.cataclysm.registry.ModBlocks;
import net.consular.cataclysm.registry.ModRecipeTypes;
import net.consular.cataclysm.registry.ModScreenHandlers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

public class BewitchingScreenHandler extends ForgingScreenHandler {
    private final World world;
    @Nullable
    private BewitchingRecipe currentRecipe;
    private final List<BewitchingRecipe> recipes;

    public BewitchingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public BewitchingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.BEWITCHING_SCREEN_HANDLER, syncId, playerInventory, context);
        this.world = playerInventory.player.getWorld();
        this.recipes = this.world.getRecipeManager().listAllOfType(ModRecipeTypes.BEWITCHING);
    }

    @Override
    protected boolean canUse(BlockState state) {
        return state.isOf(ModBlocks.BEWITCHING_TABLE);
    }

    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        ScrollItem scroll = (ScrollItem)this.getSlot(1).getStack().getItem();
        return player.experienceLevel >= scroll.getSpell().getManaCost();
    }

    @Override
    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        stack.onCraft(player.getWorld(), player, stack.getCount());
        this.output.unlockLastRecipe(player, this.getInputStacks());
        this.decrementStack(0);
        this.decrementStack(1);
        this.context.run((world, pos) -> world.syncWorldEvent(WorldEvents.SMITHING_TABLE_USED, (BlockPos)pos, 0));
    }

    private List<ItemStack> getInputStacks() {
        return List.of(this.input.getStack(0), this.input.getStack(1), this.input.getStack(2));
    }

    private void decrementStack(int slot) {
        ItemStack itemStack = this.input.getStack(slot);
        itemStack.decrement(1);
        this.input.setStack(slot, itemStack);
    }

    @Override
    public void updateResult() {
        List<BewitchingRecipe> list = this.world.getRecipeManager().getAllMatches(ModRecipeTypes.BEWITCHING, this.input, this.world);
        if (list.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
        } else {
            this.currentRecipe = list.get(0);
            ItemStack itemStack = this.currentRecipe.craft(this.input, this.world.getRegistryManager());
            this.output.setLastRecipe(this.currentRecipe);
            this.output.setStack(0, itemStack);
        }
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.output && super.canInsertIntoSlot(stack, slot);
    }

    @Override
    protected ForgingSlotsManager getForgingSlotsManager() {
        return ForgingSlotsManager.create().input(0, 27, 47, stack -> this.recipes.stream().anyMatch(smithingRecipe -> smithingRecipe.testBase((ItemStack)stack))).input(1, 76, 47, stack -> this.recipes.stream().anyMatch(smithingRecipe -> smithingRecipe.testAddition((ItemStack)stack))).output(2, 134, 47).build();
    }
}

