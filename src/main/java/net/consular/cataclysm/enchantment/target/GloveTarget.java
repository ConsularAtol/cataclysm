package net.consular.cataclysm.enchantment.target;

import net.consular.cataclysm.item.GloveItem;
import net.consular.cataclysm.mixin.EnchantmentTargetMixin;
import net.minecraft.item.Item;

public class GloveTarget extends EnchantmentTargetMixin {
    
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof GloveItem;
    }
}
