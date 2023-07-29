package net.consular.cataclysm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.consular.cataclysm.registry.ModGamerules;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Inject(method = "canPickUpLoot", at = @At("RETURN"))
    public boolean canPickupLoot(CallbackInfoReturnable ci) {
        return (((MobEntity)(Object)this).getServer().getGameRules().getBoolean(ModGamerules.CATACLYSM_MODE));
    }
}
