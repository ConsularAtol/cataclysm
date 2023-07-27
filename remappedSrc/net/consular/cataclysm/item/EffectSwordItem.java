package net.consular.cataclysm.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class EffectSwordItem extends SwordItem{

    StatusEffect statusEffect;
    int effectDuration;

    public EffectSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, StatusEffect effect, int duration) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        statusEffect = effect;
        effectDuration = duration;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(statusEffect, effectDuration));
        return true;
    }

    public String getDescription(){
        return "Inflicts ";
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Text effectName = Text.translatable(statusEffect.getTranslationKey());
        String s = effectName.getString();
        tooltip.add(Text.translatable(getDescription() + s).formatted(Formatting.BLUE));
        super.appendTooltip(stack, world, tooltip, context);
    }
    
}
