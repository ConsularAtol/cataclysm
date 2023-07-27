package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.enchantment.CunningEnchantment;
import net.consular.cataclysm.enchantment.CuttingEdgeEnchantment;
import net.consular.cataclysm.enchantment.BludgeoningEnchantment;
import net.consular.cataclysm.enchantment.QuickStabEnchantment;
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

    public static Enchantment BLUDGEONING = register("bludgeoning",
            new BludgeoningEnchantment());


    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(Cataclysm.MODID, name), enchantment);
    }

    public static void registerModEnchantments() {
        Cataclysm.LOGGER.info("Registering enchantments");;
    }

}