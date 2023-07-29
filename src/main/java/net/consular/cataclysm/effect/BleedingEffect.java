package net.consular.cataclysm.effect;

import net.consular.cataclysm.registry.ModDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BleedingEffect extends StatusEffect{

    public BleedingEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }


    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.damage(ModDamageSources.getSource(entity.getDamageSources(), ModDamageSources.BLEEDING), amplifier + 1);
    }
}
