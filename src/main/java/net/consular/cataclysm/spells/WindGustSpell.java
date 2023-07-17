package net.consular.cataclysm.spells;

import java.util.function.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class WindGustSpell implements Spell {

    private static final double BOX_WIDTH = 15.0;

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        double halfWidth = BOX_WIDTH / 2.0;
        Box boundingBox = new Box(player.getX() - halfWidth, player.getY() - 5.0, player.getZ() - halfWidth, player.getX() + halfWidth, player.getY() + 5.0, player.getZ() + halfWidth);

        Predicate<Entity> entityPredicate = entity -> true;
        for (int i = -100; i < 100; i++) {
            double angle = i * (Math.PI / 50.0);
            double dx = Math.cos(angle);
            double dz = Math.sin(angle);
            world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, player.getX(), player.getY(), player.getZ(), dx, 0, dz);
            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PHANTOM_FLAP, SoundCategory.PLAYERS, 1f, 1f);
        }
        for (Entity entity : world.getEntitiesByClass(Entity.class, boundingBox, entityPredicate)) {
            if (entity != player) {
                double dx = entity.getX() - player.getX();
                double dz = entity.getZ() - player.getZ();
                double distance = Math.sqrt(dx * dx + dz * dz);
                double factor = 2 / distance;

                entity.addVelocity(dx * factor, 0.5, dz * factor);
            }
        }
    }

    @Override
    public int getManaCost() {
        return 5;
    }

    @Override
    public Text getName() {
        return Text.translatable("spell.wind_gust");
    }

    @Override
    public String getId() {
        return "wind_gust";
    }
}
