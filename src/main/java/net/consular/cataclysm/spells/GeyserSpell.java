package net.consular.cataclysm.spells;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GeyserSpell implements Spell{

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        Cataclysm.LOGGER.info("Geyser spell casted");
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public Text getName() {
        return Text.translatable("spell.geyser");
    }

    @Override
    public String getId() {
        return "geyser";
    }
    //This spell will launch entitys into the air by having water shoot up from beneath it. If the player does not right click on a mob with the spell it will launch themselves. man I love tf2 rocket jumping.
}
