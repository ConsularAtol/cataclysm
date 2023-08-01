package net.consular.cataclysm.spells;

import net.consular.cataclysm.entity.MagicProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class IceSpikeSpell implements Spell{

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        MagicProjectileEntity spike = new MagicProjectileEntity(player, world, 2.7f, new DustParticleEffect(Vec3d.unpackRgb(0x00fbff).toVector3f(), 1.0f), 40, 32, getId());
		spike.setVelocity(player, player.getPitch(), player.getYaw(), player.getRoll(), 4.5F, 0F);
		world.spawnEntity(spike);
        if (!world.isClient){
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
            ServerWorld serverWorld = serverPlayer.server.getWorld(serverPlayer.getSpawnPointDimension());
            serverWorld.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 0.4f, 1f);
        }
    }
    
    @Override
    public int getManaCost() {
        return 8;
    }

    @Override
    public int getCooldown() {
        return 25;
    }

    @Override
    public String getId() {
        return "ice_spike";
    }

    @Override
    public void tick() {
        
    }
}
