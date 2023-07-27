package net.consular.cataclysm.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.material.ModArmorMaterials;
import net.consular.cataclysm.mixin.LivingEntityMixin;
import net.consular.cataclysm.util.ModArmorMaterial;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.world.World;

public class ModArmorItem extends ArmorItem {
    private static final EnumMap<Type, UUID> MODIFIERS = Util.make(new EnumMap(Type.class), uuidMap -> {
        uuidMap.put(Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        uuidMap.put(Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        uuidMap.put(Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        uuidMap.put(Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });
    public static final DispenserBehavior DISPENSER_BEHAVIOR = new ItemDispenserBehavior(){

        @Override
        protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
            return ArmorItem.dispenseArmor(pointer, stack) ? stack : super.dispenseSilently(pointer, stack);
        }
    };
    protected final ArmorItem.Type type;
    private final int protection;
    private final float toughness;
    protected final float knockbackResistance;
    protected final float daggerBoost;
    protected final float sneakAttackDamage;
    protected final float unarmedBoost;
    protected final float maxMana;
    protected final float arrowDamage;
    protected final ModArmorMaterial material;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;


    public ModArmorItem(ModArmorMaterial material, ArmorItem.Type type, Item.Settings settings) {
        super(material, type, settings.maxDamageIfAbsent(material.getDurability(type)));
        this.material = material;
        this.type = type;
        this.protection = material.getProtection(type);
        this.toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        this.daggerBoost = material.getDaggerBoost();
        this.sneakAttackDamage = material.getSneakAttackDamage();
        this.unarmedBoost = material.getUnarmedDamage();
        this.maxMana = material.getMaxMana();
        this.arrowDamage = material.getArrowDamage();
        DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        UUID uUID = MODIFIERS.get((Object)type);
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uUID, "Armor modifier", (double)this.protection, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uUID, "Armor toughness", (double)this.toughness, EntityAttributeModifier.Operation.ADDITION));
        if (knockbackResistance > 0) {
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(UUID.randomUUID(), "Armor knockback resist", (double)this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION));
        }
        if (daggerBoost > 0) {
            builder.put(Cataclysm.EntityAttributes.DAGGER_DAMAGE_BOOST, new EntityAttributeModifier(UUID.randomUUID(), "Armor dagger boost", (double)this.daggerBoost, EntityAttributeModifier.Operation.ADDITION));
        }
        if (sneakAttackDamage > 0) {
            builder.put(Cataclysm.EntityAttributes.SNEAK_ATTACK_DAMAGE, new EntityAttributeModifier(UUID.randomUUID(), "Armor sneak attack", (double)this.sneakAttackDamage, EntityAttributeModifier.Operation.ADDITION));
        }
        if (arrowDamage > 0) {
            builder.put(Cataclysm.EntityAttributes.ARROW_DAMAGE, new EntityAttributeModifier(UUID.randomUUID(), "Armor arrow damage", (double)this.arrowDamage, EntityAttributeModifier.Operation.ADDITION));
        }
        if (unarmedBoost > 0){
            builder.put(Cataclysm.EntityAttributes.UNARMED_DAMAGE, new EntityAttributeModifier(UUID.randomUUID(), "Armor unarmed boost", (double)this.unarmedBoost, EntityAttributeModifier.Operation.ADDITION));
        }
        if (maxMana > 0){
            builder.put(Cataclysm.EntityAttributes.MANA_MAX, new EntityAttributeModifier(UUID.randomUUID(), "Armor max mana", (double)this.maxMana, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        }
        this.attributeModifiers = builder.build();
    }

    public ArmorItem.Type getType() {
        return this.type;
    }

    @Override
    public int getEnchantability() {
        return this.material.getEnchantability();
    }

    public ArmorMaterial getMaterial() {
        return this.material;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return this.equipAndSwap(this, world, user, hand);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == this.type.getEquipmentSlot()) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }

    public int getProtection() {
        return this.protection;
    }

    public float getToughness() {
        return this.toughness;
    }

    @Override
    public EquipmentSlot getSlotType() {
        return this.type.getEquipmentSlot();
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.getMaterial().getEquipSound();
    }

    @Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (this.material == ModArmorMaterials.PHASE){
            tooltip.add(Text.literal("Set Bonus:").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("Randomly teleport after taking damage").formatted(Formatting.BLUE));
        }
        if (this.material == ModArmorMaterials.ANCIENT){
            tooltip.add(Text.literal("Set Bonus:").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("Footsteps make no noise").formatted(Formatting.BLUE));
        }
	}
}
