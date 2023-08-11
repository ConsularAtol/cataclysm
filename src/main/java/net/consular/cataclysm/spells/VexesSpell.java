package net.consular.cataclysm.spells;

import net.consular.cataclysm.registry.ModItems;
import net.consular.cataclysm.util.PlayerOwnerHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class VexesSpell implements Spell {

    @Override
    public void cast(World world, PlayerEntity player, Hand hand) {
        Hand targetHand;
        if (player.getStackInHand(Hand.MAIN_HAND).getItem() == ModItems.VEXES_TOME || player.getStackInHand(Hand.MAIN_HAND).getItem() == ModItems.VEXES_SCROLL){
            targetHand = Hand.OFF_HAND;
        } else {
            targetHand = Hand.MAIN_HAND;
        }
        for (int i = 0; i < 3; i++){
            VexEntity vex = new VexEntity(EntityType.VEX, world);
            if (player.getStackInHand(hand).hasCustomName()){
                vex.setCustomName(player.getStackInHand(hand).getName());
            }
            vex.equipStack(EquipmentSlot.MAINHAND, player.getStackInHand(targetHand));
            vex.setPosition(player.getPos());
            vex.setLifeTicks(1000);
            ((PlayerOwnerHandler)vex).setPlayerOwner(player);
            world.spawnEntity(vex);
        }
    }

    @Override
    public int getManaCost() {
        return 10;
    }

    @Override
    public int getCooldown() {
        return 1000;
    }

    @Override
    public String getId() {
        return "vexes";
    }
    
    @Override
    public void tick() {
        
    }
}
