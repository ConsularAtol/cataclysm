package net.consular.cataclysm.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface Spell {

    public void cast(World world, PlayerEntity player, Hand hand);

    public int getManaCost();

    public Text getName();

    public String getId();
}
