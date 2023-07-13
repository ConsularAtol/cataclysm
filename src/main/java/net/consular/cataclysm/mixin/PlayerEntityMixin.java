package net.consular.cataclysm.mixin;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.item.GloveItem;
import net.consular.cataclysm.registry.ModEffects;
import net.consular.cataclysm.registry.ModEnchantments;
import net.consular.cataclysm.util.MagicHelper;
import net.consular.cataclysm.util.MagicUser;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import static net.consular.cataclysm.Cataclysm.DataTrackers.*;
import static net.consular.cataclysm.Cataclysm.EntityAttributes.*;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements MagicUser {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Unique private int MAX_MANA = 20;
	@Unique private long lastCastTime = 0;

	@Inject(method = "createPlayerAttributes", at = @At("RETURN"))
	private static void createPlayerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
		info.getReturnValue().add(MANA_REGEN).add(MANA_LOCK).add(DAGGER_DAMAGE_BOOST).add(UNARMED_DAMAGE).add(MANA_MAX);
	}

	@Inject(method = "attack", at = @At("HEAD"))
	private void attack(Entity target, CallbackInfo info){
		if(this.getMainHandStack().getItem() == Items.AIR || this.getMainHandStack().getItem() instanceof GloveItem){
			Float f = (float)this.getAttributeValue(Cataclysm.EntityAttributes.UNARMED_DAMAGE);
			boolean bl3 = this.fallDistance > 0.0f && !this.isOnGround() && !this.isClimbing() && !this.isTouchingWater() && !this.hasStatusEffect(StatusEffects.BLINDNESS) && !this.hasVehicle() && target instanceof LivingEntity;
			Vec3d vec3d = target.getVelocity();
			int bludgeoning = EnchantmentHelper.getEquipmentLevel(ModEnchantments.BLUDGEONING, this);
			if (bl3) {
                f *= 1.5f;
            }
			if (bl3) {
				this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, this.getSoundCategory(), 1.0f, 1.0f);
				this.addCritParticles(target);
			}
			target.setVelocity(vec3d);
			((LivingEntity)target).takeKnockback(0.4f, MathHelper.sin(this.getYaw() * ((float)Math.PI / 180)), -MathHelper.cos(this.getYaw() * ((float)Math.PI / 180)));
			target.damage(this.getDamageSources().generic(), f + bludgeoning);
			if (this.getMainHandStack().getItem() instanceof GloveItem){
				ItemStack stack = this.getMainHandStack();
				stack.damage(1, this, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
			}
		}
	}

	public void addCritParticles(Entity target) {
    }

	@Inject(method = "tick", at = @At("TAIL"))
	public void tick(CallbackInfo info) {
		if(!this.getWorld().isClient()) {
			if(getMana() > getMaxMana())
				setMana(getMana());

			if(this.getWorld().getTime() >= lastCastTime + 20) {
				int manaCooldown = (int) Math.round(20 * MagicHelper.getManaRegen((PlayerEntity) (Object) this));

				if(manaCooldown != 0 && getMana() < getMaxMana() && this.getWorld().getTime() % manaCooldown == 0 && !this.hasStatusEffect(ModEffects.DRAINED))
						addMana(1);
				if(manaCooldown != 0 && this.getWorld().getTime() % 30 == 0 && this.hasStatusEffect(ModEffects.DRAINED))
						addMana(-1);
			}
		}
	}

    @Override
	public int getMana() {
		return dataTracker.get(MANA);
	}

	@Override
	public int getMaxMana() {
		MAX_MANA = (int)this.getAttributeValue(Cataclysm.EntityAttributes.MANA_MAX);
		return MAX_MANA;
	}

	@Override
	public void setMana(int amount) {
		dataTracker.set(MANA, MathHelper.clamp(amount, 0, getMaxMana()));
	}

	public void setManaLock(int amount) {
		setMana(getMana());
	}

	@Override
	public boolean isManaVisible() {
		return dataTracker.get(SHOW_MANA);
	}

	@Override
	public void shouldShowMana(boolean shouldShowMana) {
		dataTracker.set(SHOW_MANA, shouldShowMana);
	}


	@Override
	public void addMana(int amount) {
		setMana(Math.min(getMana() + amount, getMaxMana()));
	}

	@Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
	public void readNbt(NbtCompound tag, CallbackInfo info) {
		NbtCompound rootTag = tag.getCompound(Cataclysm.MODID);

		dataTracker.set(MANA, rootTag.getInt("Mana"));
		dataTracker.set(SHOW_MANA, rootTag.getBoolean("ShowMana"));
	}

	@Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
	public void writeNbt(NbtCompound tag, CallbackInfo info) {
		NbtCompound rootTag = new NbtCompound();

		tag.put(Cataclysm.MODID, rootTag);
		rootTag.putInt("Mana", dataTracker.get(MANA));
		rootTag.putBoolean("ShowMana", dataTracker.get(SHOW_MANA));
	}

	@Inject(method = "initDataTracker", at = @At("HEAD"))
	public void initTracker(CallbackInfo info) {
		dataTracker.startTracking(MANA, MAX_MANA);
		dataTracker.startTracking(SHOW_MANA, false);
	}
}
