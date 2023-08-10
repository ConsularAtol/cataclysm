package net.consular.cataclysm.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class WeightItem extends TrinketItem{

    public WeightItem(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {

        if (entity.hasStatusEffect(StatusEffects.LEVITATION)) {
            entity.removeStatusEffect(StatusEffects.LEVITATION);
        }


        super.tick(stack, slot, entity);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Grants immunity to Levitation").formatted(Formatting.BLUE));
        super.appendTooltip(stack, world, tooltip, context);
    }
    
}
