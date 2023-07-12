package net.consular.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.consular.cataclysm.item.DaggerItem;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin extends Enchantment {

    protected DamageEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if (stack.getItem() instanceof AxeItem || stack.getItem() instanceof DaggerItem) {
            return true;
        }
        return super.isAcceptableItem(stack);
    }
    
}
