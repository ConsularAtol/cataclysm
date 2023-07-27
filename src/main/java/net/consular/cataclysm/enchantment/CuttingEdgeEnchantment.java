package net.consular.cataclysm.enchantment;

import net.consular.cataclysm.registry.ModEnchantmentTargets;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class CuttingEdgeEnchantment extends Enchantment{

    public CuttingEdgeEnchantment() {
        super(Rarity.RARE, ModEnchantmentTargets.DAGGER_TARGET, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
