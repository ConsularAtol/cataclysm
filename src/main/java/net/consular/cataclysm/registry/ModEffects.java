package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.effect.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEffects {
    public static StatusEffect DRAINED;
    public static StatusEffect GREATER_INVISIBILITY;
    public static StatusEffect BLEEDING;

    public static StatusEffect registerStatusEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Cataclysm.MODID, name), effect);
    }

    public static void registerEffects() {
        DRAINED = registerStatusEffect("drained", new DrainedEffect(StatusEffectCategory.HARMFUL, 000752));
        GREATER_INVISIBILITY = registerStatusEffect("greater_invisibility", new GreaterInvisibilityEffect(StatusEffectCategory.BENEFICIAL, 0xffffff));
        BLEEDING = registerStatusEffect("bleeding", new BleedingEffect(StatusEffectCategory.HARMFUL, 0xad0c00));
    }
}
