package net.consular.cataclysm.item;

import java.util.List;

import javax.annotation.Nullable;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class LuckyHorseshoeItem extends TrinketItem{

    public LuckyHorseshoeItem(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {

        entity.fallDistance = 0;

        super.tick(stack, slot, entity);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Grants immunity to fall damage").formatted(Formatting.BLUE));
        super.appendTooltip(stack, world, tooltip, context);
    }
    
}
