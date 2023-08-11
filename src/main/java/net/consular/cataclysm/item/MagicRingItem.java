package net.consular.cataclysm.item;

import java.util.UUID;

import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.consular.cataclysm.Cataclysm.EntityAttributes;
import net.consular.cataclysm.registry.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

public class MagicRingItem extends TrinketItem{

    float amount;

    public MagicRingItem(Settings settings, float amount) {
        super(settings);
        this.amount = amount;
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        if (!stack.isOf(ModItems.RING))
            modifiers.put(EntityAttributes.MANA_REGEN, new EntityAttributeModifier(uuid, "cataclysm:mana_regen", amount, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        return modifiers;
    }
}
