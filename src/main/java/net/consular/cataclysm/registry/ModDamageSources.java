package net.consular.cataclysm.registry;

import java.util.HashMap;
import java.util.Map;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageSources {
    public static final RegistryKey<DamageType> BLEEDING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Cataclysm.MODID, "bleeding"));
    public static final RegistryKey<DamageType> QUICK_SAND = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Cataclysm.MODID, "quick_sand"));

    private static final Map<RegistryKey<DamageType>, DamageSource> damageSourceCache = new HashMap<>();

    public static DamageSource getSource(DamageSources damageSources, RegistryKey<DamageType> damageType) {
        return damageSourceCache.computeIfAbsent(damageType, damageSources::create);
    }

}

