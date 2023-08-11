package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType SNEAK_ATTACK = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(Cataclysm.MODID, "sneak_attack"), SNEAK_ATTACK);
    }
}
