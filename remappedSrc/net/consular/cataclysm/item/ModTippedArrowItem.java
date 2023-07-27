package net.consular.cataclysm.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.consular.cataclysm.util.ArrowType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.world.World;
import net.minecraft.text.Text;

public class ModTippedArrowItem extends ModArrowItem{

    public ModTippedArrowItem(Settings settings, ArrowType type) {
        super(settings, type);
    }

    @Override
    public ItemStack getDefaultStack() {
        return PotionUtil.setPotion(super.getDefaultStack(), Potions.POISON);
    }

    //@Override
    //public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
    //    if (this.isIn(group)) {
    //        for (Potion potion : Registries.POTION) {
    //            if (potion.getEffects().isEmpty()) continue;
    //            stacks.add(PotionUtil.setPotion(new ItemStack(this), potion));
    //        }
    //    }
    //}

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        PotionUtil.buildTooltip(stack, tooltip, 0.125f);
    }

    public String getDescription(){
        return "item.cataclysm.tipped_" + arrowType.toString().toLowerCase() + "_arrow";
    }

    // This is a little messy, I'm essentially bashing the names together to fix compatibility issues.
    @Override
    public Text getName(ItemStack stack) {
        Potion p = PotionUtil.getPotion(stack);
        Text arrowName = Text.translatable(p.finishTranslationKey("item.minecraft.tipped_arrow.effect."));
        String s = arrowName.getString();
        if (s.contains("Arrow of ")) {
            return Text.translatable(getDescription() + ".effect",
                    s.replace("Arrow of ", ""));
        }
        return Text.translatable(s);
    }
}
