package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.effect.DrainedEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEffects {
    public static StatusEffect DRAINED;
    public static StatusEffect GREATER_INVISIBILITY;

    public static StatusEffect registerStatusEffect(String name, StatusEffectCategory category, int color) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Cataclysm.MODID, name),
                new DrainedEffect(category, color));
    }

    public static void registerEffects() {
        DRAINED = registerStatusEffect("drained", StatusEffectCategory.HARMFUL, 000752);
        GREATER_INVISIBILITY = registerStatusEffect("greater_invisibility", StatusEffectCategory.BENEFICIAL, 0xffffff);
    }
}
