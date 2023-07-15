package net.consular.cataclysm.spells;

import net.consular.cataclysm.entity.MagicProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SonicBoomSpell implements Spell {

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        MagicProjectileEntity boom = new MagicProjectileEntity(player, world, 4.1f, ParticleTypes.SONIC_BOOM);
		boom.setVelocity(player, player.getPitch(), player.getYaw(), player.getRoll(), 4.5F, 0F);
		world.spawnEntity(boom);
        if (!world.isClient){
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
            ServerWorld serverWorld = serverPlayer.server.getWorld(serverPlayer.getSpawnPointDimension());
            serverWorld.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 0.4f, 1f);
        }
    }

    @Override
    public int getManaCost() {
        return 10;
    }

    @Override
    public Text getName() {
        return Text.translatable("spell.sonic_boom");
    }

    @Override
    public String getId() {
        return "sonic_boom";
    }
    
}
