package net.consular.cataclysm.item;

import java.util.Optional;

import net.consular.cataclysm.registry.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MagicMirrorItem extends Item{

    public MagicMirrorItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient()) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
            ServerWorld serverWorld = serverPlayer.server.getWorld(serverPlayer.getSpawnPointDimension());
            if (serverWorld != null && (serverWorld == serverPlayer.getWorld())) {
                    BlockPos spawnpoint = serverPlayer.getSpawnPointPosition();
                    if (spawnpoint != null) {
                        Optional<Vec3d> optionalSpawnVec = PlayerEntity.findRespawnPosition(serverWorld, spawnpoint, serverPlayer.getSpawnAngle(), false, false);

                        //Player spawn
                        BlockPos finalSpawnpoint = spawnpoint;
                        optionalSpawnVec.ifPresentOrElse(spawnVec -> {
                            serverPlayer.teleport(serverWorld, spawnVec.getX(), spawnVec.getY(), spawnVec.getZ(), serverPlayer.getSpawnAngle(), 0.5F);
                            serverWorld.playSound(null, finalSpawnpoint, ModSounds.MAGIC_MIRROR_USE, SoundCategory.PLAYERS, 1f, 1f);
                        }, () -> {
                            player.sendMessage(Text.translatable("magic_mirror.fail"));
                            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.BLOCKS, 1f, 1f);

                        });
                    }

                    // World Spawn
                    else {
                        spawnpoint = serverWorld.getSpawnPos();
                        serverPlayer.teleport(serverWorld, spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ(), serverPlayer.getSpawnAngle(), 0.5F);
                        while (!serverWorld.isSpaceEmpty(serverPlayer)) {
                            serverPlayer.teleport(serverPlayer.getX(), serverPlayer.getY() + 1.0D, serverPlayer.getZ());
                        }
                        serverWorld.playSound(null, spawnpoint, ModSounds.MAGIC_MIRROR_USE, SoundCategory.PLAYERS, 0.4f, 1f);
                    }
                } else {
                    player.sendMessage(Text.translatable("magic_mirror.fail"));
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.BLOCKS, 1f, 1f);
                }
            }
        if (player != null) {
            player.getItemCooldownManager().set(this, 100);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return TypedActionResult.success(itemStack);
    }
}
