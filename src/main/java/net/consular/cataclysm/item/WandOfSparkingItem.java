package net.consular.cataclysm.item;

import net.consular.cataclysm.entity.WandOfSparkingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WandOfSparkingItem extends MagicItem{
    
    public WandOfSparkingItem(Settings settings) {
        super(settings);
    }

    @Override
    public void cast(World world, PlayerEntity player, Hand hand){
        WandOfSparkingEntity spark = new WandOfSparkingEntity(player, world);
		spark.setVelocity(player, player.getPitch(), player.getYaw(), player.getRoll(), 4.5F, 0F);
		world.spawnEntity(spark);
    }

    @Override
    public int getManaCost() {
        return 2;
    }
}
