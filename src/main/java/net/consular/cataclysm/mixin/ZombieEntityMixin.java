package net.consular.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.consular.cataclysm.registry.ModGamerules;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
    @Inject(method = "canBreakDoors", at = @At("RETURN"))
    public boolean canBreakDoors(CallbackInfoReturnable ci) {
        return (((ZombieEntity)(Object)this).getServer().getGameRules().getBoolean(ModGamerules.CATACLYSM_MODE));
    }
}
