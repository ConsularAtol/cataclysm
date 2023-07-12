package net.consular.cataclysm.entity;

import net.consular.cataclysm.registry.ModEntities;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;

public class MagicSwordEntity extends PersistentProjectileEntity {

	public MagicSwordEntity(LivingEntity owner, World world) {
		super(ModEntities.MAGIC_SWORD, owner, world);
		setNoGravity(true);
		setDamage(3D);
	}

	public MagicSwordEntity(World world, double x, double y, double z) {
		super(ModEntities.MAGIC_SWORD, x, y, z, world);
		setNoGravity(true);
		setDamage(3D);
	}

	@SuppressWarnings("all")
	public MagicSwordEntity(EntityType type, World world) {
		super(type, world);
		setNoGravity(true);
		setDamage(3D);
	}

	@Override
	public void tick() {
		super.tick();
		World world = this.getWorld();

		if(!world.isClient()) {
			for(int count = 0; count < 16; count++) {
				double x = getX() + (world.random.nextInt(3) - 1) / 4D;
				double y = getY() + 0.2F + (world.random.nextInt(3) - 1) / 4D;
				double z = getZ() + (world.random.nextInt(3) - 1) / 4D;
				double deltaX = (world.random.nextInt(3) - 1) * world.random.nextDouble();
				double deltaY = (world.random.nextInt(3) - 1) * world.random.nextDouble();
				double deltaZ = (world.random.nextInt(3) - 1) * world.random.nextDouble();

				PlayerLookup.tracking(this).forEach(player -> ((ServerWorld) world).spawnParticles(player, (ParticleEffect) ParticleTypes.SOUL, true, x, y, z, 1, deltaX, deltaY, deltaZ, 0.05));
			}
		}

		if(age > 30){
			kill();
		}
	}

	@Override
	protected SoundEvent getHitSound() {
		return SoundEvents.BLOCK_SOUL_SAND_BREAK;
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);

		if(entityHitResult.getEntity() instanceof LivingEntity target){
			target.timeUntilRegen = 0;
        }
	}

	@Override
	public void onPlayerCollision(PlayerEntity player) {
		if(!this.getWorld().isClient && (inGround || isNoClip()) && shake <= 0)
			discard();
	}

	@Override
	protected ItemStack asItemStack() {
		return ItemStack.EMPTY;
	}
}
