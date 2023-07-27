package net.consular.cataclysm.entity;

import net.consular.cataclysm.registry.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class StingerEntity extends PersistentProjectileEntity{

    protected int timeInAir;
	protected boolean inAir;

    public StingerEntity(EntityType<? extends StingerEntity> entityType, World world) {
		super(entityType, world);
		this.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
	}

	public StingerEntity(World world, LivingEntity owner) {
		super(ModEntities.STINGER, owner, world);
	}

	protected StingerEntity(EntityType<? extends StingerEntity> type, double x, double y, double z, World world) {
		this(type, world);
	}

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity target = entityHitResult.getEntity();
        ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, MathHelper.nextBetween(random, 20, 200)));
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }
    
}
