package net.consular.cataclysm.enchantment;

import net.consular.cataclysm.registry.ModEnchantmentTargets;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class BludgeoningEnchantment extends Enchantment{

    public BludgeoningEnchantment() {
        super(Rarity.UNCOMMON, ModEnchantmentTargets.GLOVE_TARGET, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
