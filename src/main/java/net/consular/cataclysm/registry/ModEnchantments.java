package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.enchantment.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModEnchantments {

    public static Enchantment QUICK_STAB = register("quick_stab",
            new QuickStabEnchantment());

    public static Enchantment CUNNING = register("cunning",
            new CunningEnchantment());

    public static Enchantment CUTTING_EDGE = register("cutting_edge",
            new CuttingEdgeEnchantment());

    public static Enchantment MAGMA_WALKER = register("magma_walker",
            new MagmaWalkerEnchantment());

    public static Enchantment BLUDGEONING = register("bludgeoning",
            new BludgeoningEnchantment());


    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(Cataclysm.MODID, name), enchantment);
    }

    public static void registerModEnchantments() {
        Cataclysm.LOGGER.info("Registering enchantments");;
    }

}