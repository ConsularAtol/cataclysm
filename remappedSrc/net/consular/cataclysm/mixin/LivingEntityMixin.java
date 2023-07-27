package net.consular.cataclysm.mixin;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.consular.cataclysm.registry.ModEffects;
import net.consular.cataclysm.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.event.GameEvent;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    protected final Random random = Random.create();
    
    @Inject(method = "onDamaged", at = @At("HEAD"))
    public void onDamaged(DamageSource damageSource, CallbackInfo cir){
        LivingEntity entity = (LivingEntity)(Object)this;
        if (wearingPhase(entity)){
            double d = entity.getX();
            double e = entity.getY();
            double f = entity.getZ();
            double g = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
            double h = e;
            double j = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
            entity.setPos(g, h, j);
            Vec3d vec3d = entity.getPos();
            entity.getWorld().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
            SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
            entity.getWorld().playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0f, 1.0f);
            entity.playSound(soundEvent, 1.0f, 1.0f);
        }
        if (!canSee(damageSource.getAttacker())){
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            for(int i = 0; i < 10; i++)
                entity.getWorld().addParticle(ParticleTypes.CRIT, entity.getParticleX(1.0), entity.getRandomBodyY(), entity.getParticleZ(1.0), d, e, f);
            entity.getWorld().playSoundFromEntity((PlayerEntity)damageSource.getAttacker(), entity, SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 1f, 1f);
        }
    }

    @Overwrite
    public boolean canSee(Entity entity) {
        if (entity == null){
            return false;
        }

        System.out.println(entity);
        
        if (entity.getWorld() != ((LivingEntity)(Object)this).getWorld()) {
            return false;
        }

        Vec3d entityPosition = entity.getPos();
        Vec3d selfPosition = ((LivingEntity)(Object)this).getCameraPosVec(1.0F);

        double dotProduct = entityPosition.subtract(selfPosition).normalize().dotProduct(((LivingEntity)(Object)this).getRotationVector());
        if (dotProduct <= 0 && entity.isSneaking()) {
            return false; // Target entity is behind or to the side of the entity
        }

        double distance = entityPosition.distanceTo(selfPosition);
        if (distance > 128.0) {
            return false; // Target entity is too far away
        }

        if (entity.isInvisible()){
            return false; // Target is invisible
        }

        HitResult result = ((LivingEntity)(Object)this).getWorld().raycast(
            new RaycastContext(selfPosition, entityPosition, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, ((LivingEntity)(Object)this))
        );

        return result.getType() == HitResult.Type.MISS;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void greaterInvisibility(CallbackInfo ci) {
        if (((LivingEntity)(Object)this).hasStatusEffect(ModEffects.GREATER_INVISIBILITY)){
            ((LivingEntity)(Object)this).setInvisible(true);
        }
    }

    public boolean wearingPhase(LivingEntity entity){
        return entity.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.PHASE_HELMET) && 
        entity.getEquippedStack(EquipmentSlot.CHEST).isOf(ModItems.PHASE_CHESTPLATE) &&
        entity.getEquippedStack(EquipmentSlot.LEGS).isOf(ModItems.PHASE_LEGGINGS) &&
        entity.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.PHASE_BOOTS);
    }
}
