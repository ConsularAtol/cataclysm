package net.consular.cataclysm.mixin;

import java.util.EnumMap;
import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPointer;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin extends Item implements Equipment{
    public ArmorItemMixin(Settings settings) {
        super(settings);
    }

    private static final EnumMap<ArmorItem.Type, UUID> MODIFIERS = Util.make(new EnumMap(ArmorItem.Type.class), uuidMap -> {
        uuidMap.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        uuidMap.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        uuidMap.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        uuidMap.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });

    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers2;
    protected ArmorItem.Type type2;
    private ArmorMaterial material2;
    private int protection2;
    private float toughness2;
    protected float knockbackResistance2;

    private static final DispenserBehavior DISPENSER_BEHAVIOR2 = new ItemDispenserBehavior(){

        @Override
        protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
            return ArmorItem.dispenseArmor(pointer, stack) ? stack : super.dispenseSilently(pointer, stack);
        }
    };

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectCode(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings, CallbackInfo ci) {
        this.material2 = material;
        this.type2 = type;
        this.protection2 = material.getProtection(type);
        this.toughness2 = material.getToughness();
        this.knockbackResistance2 = material.getKnockbackResistance();
        DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR2);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        UUID uUID = MODIFIERS.get((Object)type);
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uUID, "Armor modifier", (double)this.protection2, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uUID, "Armor toughness", (double)this.toughness2, EntityAttributeModifier.Operation.ADDITION));
        if (material == ArmorMaterials.NETHERITE) {
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(uUID, "Armor knockback resistance", (double)this.knockbackResistance2, EntityAttributeModifier.Operation.ADDITION));
        }
        if (material == ArmorMaterials.CHAIN) {
            builder.put(Cataclysm.EntityAttributes.ARROW_DAMAGE, new EntityAttributeModifier(uUID, "Armor arrow damage", 0.2D, EntityAttributeModifier.Operation.ADDITION));
        }
        this.attributeModifiers2 = builder.build();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == this.type2.getEquipmentSlot()) {
            return this.attributeModifiers2;
        }
        return super.getAttributeModifiers(slot);
    }
}
