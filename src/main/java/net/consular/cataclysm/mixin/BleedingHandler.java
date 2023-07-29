package net.consular.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.consular.cataclysm.registry.ModEffects;
import net.consular.cataclysm.util.BleedingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

@Mixin(LivingEntity.class)
public class BleedingHandler implements BleedingEntity{
    public void startBleeding(int amplifier){
        LivingEntity entity = (LivingEntity)(Object)this;
        if (entity.hasStatusEffect(ModEffects.BLEEDING)){
            entity.setInvulnerable(true);
            entity.addStatusEffect(new StatusEffectInstance(ModEffects.BLEEDING, 200 - (amplifier * 20), entity.getStatusEffect(ModEffects.BLEEDING).getAmplifier() + 1));
            entity.setInvulnerable(false);
        }
        else
            entity.addStatusEffect(new StatusEffectInstance(ModEffects.BLEEDING, 200 - (amplifier * 20), 0));
    }
}
