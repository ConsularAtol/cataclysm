package net.consular.cataclysm.enchantment.target;

import net.consular.cataclysm.item.DaggerItem;
import net.consular.cataclysm.mixin.EnchantmentTargetMixin;
import net.minecraft.item.Item;

public class DaggerTarget extends EnchantmentTargetMixin {
    
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof DaggerItem;
    }
}
