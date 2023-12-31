package net.consular.cataclysm;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.Difficulty;

public class EarlyRiser implements Runnable {

    @Override
    public void run() {
        var remapper = FabricLoader.getInstance().getMappingResolver();
        String enchantmentTarget = remapper.mapClassName("intermediary", "net.minecraft.class_1886");
        ClassTinkerers.enumBuilder(enchantmentTarget, new Class[0])
                .addEnumSubclass("DAGGER", "net.consular.cataclysm.enchantment.target.DaggerTarget")
                .build();
        ClassTinkerers.enumBuilder(enchantmentTarget, new Class[0])
                .addEnumSubclass("GLOVE", "net.consular.cataclysm.enchantment.target.GloveTarget")
                .build();
    }
}