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

public class BrassKnucklesItem extends TrinketItem{

    public BrassKnucklesItem(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.UNARMED_DAMAGE, new EntityAttributeModifier(uuid, "cataclysm:unarmed_damage", 1, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;
    }
    
}
