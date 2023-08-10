package net.consular.cataclysm.item;

import java.util.UUID;

import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.consular.cataclysm.Cataclysm.EntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

public class LapisRingItem extends TrinketItem{

    public LapisRingItem(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.MANA_REGEN, new EntityAttributeModifier(uuid, "cataclysm:mana_regen", 0.1, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        return modifiers;
    }
}
