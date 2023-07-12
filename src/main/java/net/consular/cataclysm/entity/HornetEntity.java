package net.consular.cataclysm.entity;

import java.util.EnumSet;

import javax.annotation.Nullable;

import net.consular.cataclysm.util.EffectImmuneEntity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HornetEntity extends HostileEntity implements GeoEntity, Flutterer, EffectImmuneEntity{

    private static final TrackedData<Integer> STINGER_COOLDOWN = DataTracker.registerData(HornetEntity.class, TrackedDataHandlerRegistry.INTEGER);


    public HornetEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
        this.lookControl = new HornetLookControl(this);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public StatusEffect[] effectImmunities() {
        return new StatusEffect[]{StatusEffects.POISON};
    }

    @Override
    public void tick() {
        super.tick();
        for (StatusEffect effect : effectImmunities()){
            removeStatusEffect(effect);
        }
        if(getCooldown() > 0)
            this.setCooldown(getCooldown() - 1);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(STINGER_COOLDOWN, 100);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "flying", 5, state -> state.setAndContinue(RawAnimation.begin().thenLoop("animation.hornet.flying"))));
    }

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("StingerCooldown", this.getCooldown());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setCooldown(nbt.getInt("StingerCooldown"));
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        if (world.getBlockState(pos).isAir()) {
            return 10.0f;
        }
        return 0.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BEE_LOOP_AGGRESSIVE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BEE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BEE_DEATH;
    }

    private int getCooldown() {
        return this.dataTracker.get(STINGER_COOLDOWN);
    }

    private void setCooldown(int amount) {
        this.dataTracker.set(STINGER_COOLDOWN, amount);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world){

            @Override
            public boolean isValidPosition(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }

            @Override
            public void tick() {
                super.tick();
            }
        };
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.ARTHROPOD;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new ShootStingerGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(3, new HornetWanderAroundGoal());
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return FlyingEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 1.6f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48f);
    }

    @Override
    public boolean isInAir() {
        return !this.isOnGround();
    }

    class HornetWanderAroundGoal
    extends Goal {
        HornetWanderAroundGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return HornetEntity.this.navigation.isIdle() && HornetEntity.this.random.nextInt(10) == 0;
        }

        @Override
        public boolean shouldContinue() {
            return HornetEntity.this.navigation.isFollowingPath();
        }

        @Override
        public void start() {
            Vec3d vec3d = this.getRandomLocation();
            if (vec3d != null) {
                HornetEntity.this.navigation.startMovingAlong(HornetEntity.this.navigation.findPathTo(BlockPos.ofFloored(vec3d), 1), 1.0);
            }
        }


        @Nullable
        private Vec3d getRandomLocation() {
            Vec3d vec3d2;
            vec3d2 = HornetEntity.this.getRotationVec(0.0f);
            Vec3d vec3d3 = AboveGroundTargeting.find(HornetEntity.this, 8, 7, vec3d2.x, vec3d2.z, 1.5707964f, 3, 1);
            if (vec3d3 != null) {
                return vec3d3;
            }
            return NoPenaltySolidTargeting.find(HornetEntity.this, 8, 4, -2, vec3d2.x, vec3d2.z, 1.5707963705062866);
        }
    }

    class HornetLookControl
    extends LookControl {
        HornetLookControl(MobEntity entity) {
            super(entity);
        }

        @Override
        public void tick() {
            super.tick();
            return;
        }

        @Override
        protected boolean shouldStayHorizontal() {
            return true;
        }
    }

    static class ShootStingerGoal
    extends Goal {
        private final HornetEntity hornet;
        private boolean stingerFired;
        private int targetNotVisibleTicks;

        public ShootStingerGoal(HornetEntity hornet) {
            this.hornet = hornet;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = this.hornet.getTarget();
            return livingEntity != null && livingEntity.isAlive() && this.hornet.canTarget(livingEntity) && this.hornet.getCooldown() <= 0;
        }

        @Override
        public void start() {
            stingerFired = false;
        }

        @Override
        public void stop() {
            this.targetNotVisibleTicks = 0;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.hornet.getTarget();
            if (livingEntity == null) {
                return;
            }
            boolean bl = this.hornet.getVisibilityCache().canSee(livingEntity);
            this.targetNotVisibleTicks = bl ? 0 : ++this.targetNotVisibleTicks;
            double d = this.hornet.squaredDistanceTo(livingEntity);
            if (d < this.getFollowRange() * this.getFollowRange() && bl && !stingerFired) {
                this.hornet.setCooldown(100);
                stingerFired = true;
                StingerEntity persistentProjectileEntity = new StingerEntity(this.hornet.getWorld(), this.hornet);
                double d2 = livingEntity.getX() - this.hornet.getX();
                double e = livingEntity.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
                double f = livingEntity.getZ() - this.hornet.getZ();
                double g = Math.sqrt(d2 * d2 + f * f);                
                persistentProjectileEntity.setVelocity(d2, e + g * (double)0.2f, f, 1.6f, 14 - this.hornet.getWorld().getDifficulty().getId() * 4);
                this.hornet.getWorld().spawnEntity(persistentProjectileEntity);
                this.hornet.getLookControl().lookAt(livingEntity, 10.0f, 10.0f);
            } else if (this.targetNotVisibleTicks < 5) {
                this.hornet.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0);
            }
            super.tick();
        }

        private double getFollowRange() {
            return this.hornet.getAttributeValue(EntityAttributes.GENERIC_FOLLOW_RANGE);
        }
    }

    
    
}
