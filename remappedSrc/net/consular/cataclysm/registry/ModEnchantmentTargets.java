package net.consular.cataclysm.registry;

import com.chocohead.mm.api.ClassTinkerers;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.enchantment.EnchantmentTarget;

public class ModEnchantmentTargets {

   public static final EnchantmentTarget DAGGER_TARGET = ClassTinkerers.getEnum(EnchantmentTarget.class, "DAGGER");
   public static final EnchantmentTarget GLOVE_TARGET = ClassTinkerers.getEnum(EnchantmentTarget.class, "GLOVE");

   public static void registerEnchantmentTargets(){
      Cataclysm.LOGGER.info("Registering enchantment targets");;
   }
}
