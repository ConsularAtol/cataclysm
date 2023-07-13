package net.consular.cataclysm.spells;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WindGustSpell implements Spell{

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        Cataclysm.LOGGER.info("Wind Gust spell casted");
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public Text getName() {
        return Text.translatable("spell.wind_gust");
    }

    @Override
    public String getId() {
        return "wind_gust";
    }
    // This spell will shoot a gust of wind pushing back mobs. This can be used to push mobs off cliffs, into lava, into the ranger, or into the meele person
}
