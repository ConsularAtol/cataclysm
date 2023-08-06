package net.consular.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.consular.cataclysm.registry.ModGamerules;
import net.consular.cataclysm.registry.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
    @Inject(method = "canBreakDoors", at = @At("RETURN"))
    public boolean canBreakDoors(CallbackInfoReturnable ci) {
        return (((ZombieEntity)(Object)this).getServer().getGameRules().getBoolean(ModGamerules.CATACLYSM_MODE));
    }

    @Overwrite
    public void initEquipment(Random random, LocalDifficulty localDifficulty) {
        float f = random.nextFloat();
        float f2 = ((ZombieEntity)(Object)this).getWorld().getDifficulty() == Difficulty.HARD ? 0.05f : 0.01f;
        if (((ZombieEntity)(Object)this).getServer().getGameRules().getBoolean(ModGamerules.CATACLYSM_MODE)){
            f2 = 0.25f;
        }
        if (f < f2) {
            int i = random.nextInt(3);
            if (i == 0) {
                ((ZombieEntity)(Object)this).equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } if (i == 1) {
                ((ZombieEntity)(Object)this).equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
            } if (i == 2) {
                ((ZombieEntity)(Object)this).equipStack(EquipmentSlot.MAINHAND, new ItemStack(ModItems.IRON_DAGGER));
            }
            
        }
    }
}
