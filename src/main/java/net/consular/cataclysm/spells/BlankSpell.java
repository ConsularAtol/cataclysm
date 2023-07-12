package net.consular.cataclysm.spells;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BlankSpell implements Spell{

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        Cataclysm.LOGGER.info("Blank spell casted");
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public Text getName() {
        return Text.translatable("spell.blank");
    }

    @Override
    public String getId() {
        return "blank";
    }
    
}
