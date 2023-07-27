package net.consular.cataclysm.enchantment;

import net.consular.cataclysm.registry.ModEnchantmentTargets;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class QuickStabEnchantment extends Enchantment{

    public QuickStabEnchantment() {
        super(Rarity.UNCOMMON, ModEnchantmentTargets.DAGGER_TARGET, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
