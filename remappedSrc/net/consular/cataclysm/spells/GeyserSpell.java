package net.consular.cataclysm.spells;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class GeyserSpell implements Spell{

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        HitResult rayCastHit = player.raycast(10D, 1, false);
        Entity target;
        if (!world.getBlockState(((BlockHitResult)rayCastHit).getBlockPos()).isAir()){
            BlockPos pos = ((BlockHitResult)rayCastHit).getBlockPos();
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            Box boundingBox = new Box(x - 2, y - 2, z - 2, x + 2, y + 2, z + 2);
            target = world.getClosestEntity(LivingEntity.class, TargetPredicate.DEFAULT, player, pos.getX(), pos.getY(), pos.getZ(), boundingBox);
        } else {
            target = player;
        }
        if (target == null){
            target = player;
        }
        if(target.isOnGround() || target.isTouchingWater()){
            target.setVelocity(0, 1, 0);
            for (int i = 0; i < 1000; i++)
                world.addParticle(ParticleTypes.FALLING_WATER, target.getX(), target.getY(), target.getZ(), 0, 0, 0);
            world.playSoundFromEntity(player, (Entity)target, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.PLAYERS, 1f, 1f);
        }
    }
    
    @Override
    public int getManaCost() {
        return 3;
    }

    @Override
    public String getId() {
        return "geyser";
    }

    @Override
    public void tick() {
        
    }
}
