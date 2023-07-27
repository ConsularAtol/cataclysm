package net.consular.cataclysm.spells;

import java.util.ArrayList;
import java.util.List;

import net.consular.cataclysm.entity.ModFallingBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagnesisSpell implements Spell{

    ModFallingBlockEntity fallingBlock;
    List<ModFallingBlockEntity> oldFallingBlocks = new ArrayList<>();
    PlayerEntity player;

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        this.player = player;
        BlockHitResult hitResult = (BlockHitResult)player.raycast(10, 1, false);
        BlockPos pos = hitResult.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (fallingBlock != null){
            oldFallingBlocks.add(fallingBlock);
            fallingBlock.addVelocity(player.getRotationVector());
        }
        fallingBlock = new ModFallingBlockEntity(world, pos.getX(), pos.getY(), pos.getY(), state);
        fallingBlock.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        world.removeBlock(pos, true);
        world.spawnEntity(fallingBlock);
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public String getId() {
        return "magnesis";
    }

    public void dropBlock(){
        if (oldFallingBlocks != null)
            oldFallingBlocks.add(fallingBlock);
        fallingBlock = null;
        if (oldFallingBlocks != null)
            for (int i = 0; i < oldFallingBlocks.size(); i++){
                    if (oldFallingBlocks.get(i) != null)
                        oldFallingBlocks.get(i).setNoGravity(false);
                }
    }
    
    @Override
    public void tick() {
        if (fallingBlock != null && player != null){
            BlockHitResult hitResult = (BlockHitResult)player.raycast(4, 1, false);
            BlockPos holdPos = hitResult.getBlockPos();
            fallingBlock.setPos(holdPos.getX() + 0.5, holdPos.getY(), holdPos.getZ() + 0.5);
            fallingBlock.setNoGravity(true);
            if (oldFallingBlocks != null)
                for (int i = 0; i < oldFallingBlocks.size(); i++){
                    if (oldFallingBlocks.get(i) != null)
                        oldFallingBlocks.get(i).setNoGravity(false);
                }
            fallingBlock.setVelocity(0, 0, 0);
        }
    }
}
