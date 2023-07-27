package net.consular.cataclysm.event;

import net.consular.cataclysm.registry.ModEffects;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public interface SneakAttackHandler {
    public static void initialize() {
        // Event handler for when a player attacks an entity
        AttackEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, Entity target, EntityHitResult hitResult) -> {
            if (target instanceof LivingEntity) {
                player.removeStatusEffect(StatusEffects.INVISIBILITY);
                player.removeStatusEffect(ModEffects.GREATER_INVISIBILITY);
            }
            return ActionResult.PASS;
        });
    }
}
