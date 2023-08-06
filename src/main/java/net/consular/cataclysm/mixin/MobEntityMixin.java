package net.consular.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.consular.cataclysm.registry.ModGamerules;
import net.consular.cataclysm.util.PlayerOwnerHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Targeter;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity implements Targeter, PlayerOwnerHandler{
    PlayerEntity owner;

    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "canPickUpLoot", at = @At("RETURN"))
    public boolean canPickupLoot(CallbackInfoReturnable ci) {
        return (((MobEntity)(Object)this).getServer().getGameRules().getBoolean(ModGamerules.CATACLYSM_MODE));
    }

    @ModifyVariable(method = "setTarget", at = @At("HEAD"))
    private LivingEntity modifyTarget(LivingEntity target) {
        if (this.getWorld().isClient() || !(target instanceof PlayerEntity)) {
            return target;
        }

        boolean shouldIgnore = target == owner;
        if (this.owner != null){
            if (this.owner.getAttacking() != null){
                return this.owner.getAttacking();
            } else
            if (this.owner.getAttacker() != null){
                return this.owner.getAttacker();
            }
        }
        return shouldIgnore ? null : target;
    }

    @Override
    public void setPlayerOwner(PlayerEntity player){
        this.owner = player;
    }

    @Overwrite
    public void updateEnchantments(Random random, LocalDifficulty localDifficulty) {
        float f = localDifficulty.getClampedLocalDifficulty();
        if ((((MobEntity)(Object)this).getServer().getGameRules().getBoolean(ModGamerules.CATACLYSM_MODE))){
            f += 10;
        }
        this.enchantMainHandItem(random, f);
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (equipmentSlot.getType() != EquipmentSlot.Type.ARMOR) continue;
            this.enchantEquipment(random, f, equipmentSlot);
        }
    }

    @Overwrite
    public void enchantMainHandItem(Random random, float power) {
        if (!this.getMainHandStack().isEmpty() && random.nextFloat() < 0.25f * power) {
            this.equipStack(EquipmentSlot.MAINHAND, EnchantmentHelper.enchant(random, this.getMainHandStack(), (int)(5.0f + power * (float)random.nextInt(18)), false));
        }
    }

    @Overwrite
    public void enchantEquipment(Random random, float power, EquipmentSlot slot) {
        ItemStack itemStack = this.getEquippedStack(slot);
        if (!itemStack.isEmpty() && random.nextFloat() < 0.5f * power) {
            this.equipStack(slot, EnchantmentHelper.enchant(random, itemStack, (int)(5.0f + power * (float)random.nextInt(18)), false));
        }
    }
}
