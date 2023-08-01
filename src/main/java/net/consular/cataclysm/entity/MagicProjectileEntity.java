package net.consular.cataclysm.entity;

import net.consular.cataclysm.registry.ModEntities;
import net.consular.cataclysm.registry.ModItems;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;

public class MagicProjectileEntity extends PersistentProjectileEntity {

    ParticleEffect particleEffect;
	int lifeSpan;
	int particleCount;
	String id;
    private static final TrackedData<Float> DAMAGE = DataTracker.registerData(MagicProjectileEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Boolean> NO_GRAVITY = DataTracker.registerData(MagicProjectileEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	public MagicProjectileEntity(LivingEntity owner, World world, Float damage, ParticleEffect particleEffect, int lifeSpan, int particleCount, String id) {
		super(ModEntities.MAGIC_PROJECTILE, owner, world);
        this.dataTracker.set(DAMAGE, damage);
		setNoGravity(this.dataTracker.get(NO_GRAVITY));
		setDamage(this.dataTracker.get(DAMAGE));
        this.particleEffect = particleEffect;
		this.lifeSpan = lifeSpan;
		this.particleCount = particleCount;
		this.id = id;
	}

	public MagicProjectileEntity(World world, double x, double y, double z) {
		super(ModEntities.MAGIC_PROJECTILE, x, y, z, world);
		setNoGravity(this.dataTracker.get(NO_GRAVITY));
		setDamage(this.dataTracker.get(DAMAGE));
	}

	@SuppressWarnings("all")
	public MagicProjectileEntity(EntityType type, World world) {
		super(type, world);
		setNoGravity(this.dataTracker.get(NO_GRAVITY));
		setDamage(this.dataTracker.get(DAMAGE));
	}

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DAMAGE, 1f);
        this.dataTracker.startTracking(NO_GRAVITY, true);
    }

	@Override
	public void tick() {
		super.tick();
		World world = this.getWorld();

		if(!world.isClient()) {
			for(int count = 0; count < particleCount; count++) {
				double x = getX() + (world.random.nextInt(3) - 1) / 10D;
				double y = getY() + 0.2F + (world.random.nextInt(3) - 1) / 10D;
				double z = getZ() + (world.random.nextInt(3) - 1) / 10D;
				double deltaX = (world.random.nextInt(3) - 1) * world.random.nextDouble();
				double deltaY = (world.random.nextInt(3) - 1) * world.random.nextDouble();
				double deltaZ = (world.random.nextInt(3) - 1) * world.random.nextDouble();
                
				PlayerLookup.tracking(this).forEach(player -> ((ServerWorld) world).spawnParticles(player, particleEffect, true, x, y, z, 1, deltaX, deltaY, deltaZ, 1));
			}
		}

		if(age > lifeSpan)
			kill();
	}

	@Override
	protected SoundEvent getHitSound() {
		return SoundEvents.ENTITY_ARROW_HIT_PLAYER;
	}

	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {
		kill();
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		if (this.id == "ice_spike"){
			System.out.println("Fuck");
			((LivingEntity)entityHitResult.getEntity()).setFrozenTicks(entityHitResult.getEntity().getFrozenTicks() + 200);
		}
		super.onEntityHit(entityHitResult);
	}

	@Override
	public void onPlayerCollision(PlayerEntity player) {
		if(!this.getWorld().isClient && (inGround || isNoClip()) && shake <= 0 && player != this.getOwner())
			discard();
	}

	@Override
	protected ItemStack asItemStack() {
		return ItemStack.EMPTY;
	}
}
