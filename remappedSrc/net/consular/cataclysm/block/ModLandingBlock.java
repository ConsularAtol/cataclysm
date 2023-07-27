package net.consular.cataclysm.block;

import net.consular.cataclysm.entity.ModFallingBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ModLandingBlock {
    default public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, ModFallingBlockEntity fallingBlockEntity) {
    }

    default public void onDestroyedOnLanding(World world, BlockPos pos, ModFallingBlockEntity fallingBlockEntity) {
    }

    default public DamageSource getDamageSource(Entity attacker) {
        return attacker.getDamageSources().fallingBlock(attacker);
    }
}

