package net.consular.cataclysm.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.registry.ModEffects;
import net.consular.cataclysm.registry.ModEnchantments;
import net.consular.cataclysm.util.BleedingEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Vanishable;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DaggerItem extends ToolItem implements Vanishable{

    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public DaggerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, settings);
        this.attackDamage = (float)attackDamage + toolMaterial.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double)this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double)attackSpeed, EntityAttributeModifier.Operation.ADDITION));

        this.attributeModifiers = builder.build();
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.COBWEB)) {
            return 15.0f;
        }
        return state.isIn(BlockTags.SWORD_EFFICIENT) ? 1.5f : 1.0f;
    }

    private Double getDamageBonus(LivingEntity attacker){
        return attacker.getAttributeValue(Cataclysm.EntityAttributes.DAGGER_DAMAGE_BOOST) + 1D;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        int quickStab = EnchantmentHelper.getEquipmentLevel(ModEnchantments.QUICK_STAB, attacker);
        int cunning = EnchantmentHelper.getEquipmentLevel(ModEnchantments.CUNNING, attacker);
        int cutting = EnchantmentHelper.getEquipmentLevel(ModEnchantments.CUTTING_EDGE, attacker);
        if (cunning > 0) {
            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, cunning - 1));
            attacker.addStatusEffect(new StatusEffectInstance(ModEffects.GREATER_INVISIBILITY, 20));
        }
        if (!target.canSee(attacker))
            target.damage(attacker.getDamageSources().generic(), (float) (getDamageBonus(attacker) + attackDamage + attacker.getAttributeValue(Cataclysm.EntityAttributes.SNEAK_ATTACK_DAMAGE)));
        else
            target.damage(attacker.getDamageSources().generic(), (float) (getDamageBonus(attacker) + attackDamage));
        if (cutting > 0){
            ((BleedingEntity)target).startBleeding(cutting);
        }
        target.timeUntilRegen = 18 - quickStab;
        return true;
    }
    
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.getHardness(world, pos) != 0.0f) {
            stack.damage(2, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return true;
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return state.isOf(Blocks.COBWEB);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }
}
