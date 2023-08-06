package net.consular.cataclysm.spells;

import net.consular.cataclysm.entity.MagicProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class VexesSpell implements Spell {

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        VexEntity vex = new VexEntity(EntityType.VEX, world);
        vex.setOwner((MobEntity)player);
    }

    @Override
    public int getManaCost() {
        return 10;
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public String getId() {
        return "vexes";
    }
    
    @Override
    public void tick() {
        
    }
}
