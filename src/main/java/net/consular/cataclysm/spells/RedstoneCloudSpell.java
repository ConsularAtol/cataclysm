package net.consular.cataclysm.spells;

import java.util.function.Predicate;

import net.consular.cataclysm.util.BleedingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class RedstoneCloudSpell implements Spell {

    private static final double BOX_WIDTH = 15.0;
    Random random = Random.create();

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        double halfWidth = BOX_WIDTH / 2.0;
        Box boundingBox = new Box(player.getX() - halfWidth, player.getY() - 5.0, player.getZ() - halfWidth, player.getX() + halfWidth, player.getY() + 5.0, player.getZ() + halfWidth);
    
        Predicate<LivingEntity> entityPredicate = entity -> true;
        for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, boundingBox, entityPredicate)) {
            if (entity != player) {
                ((BleedingEntity) entity).startBleeding(3);
                for (int i = 0; i < 100; i++) {
                    double radius = random.nextDouble() * 3.0; // Adjust the radius as needed
                    double theta = random.nextDouble() * Math.PI; // Random angle from 0 to pi
                    double phi = random.nextDouble() * (2.0 * Math.PI); // Random angle from 0 to 2*pi
    
                    double x = entity.getX() + radius * Math.sin(theta) * Math.cos(phi);
                    double y = entity.getY() + 0.7 + radius * Math.cos(theta);
                    double z = entity.getZ() + radius * Math.sin(theta) * Math.sin(phi);
    
                    entity.getWorld().addParticle(DustParticleEffect.DEFAULT, x, y, z, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    @Override
    public int getManaCost() {
        return 10;
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public String getId() {
        return "redstone_cloud";
    }

    @Override
    public void tick() {
        
    }
}
