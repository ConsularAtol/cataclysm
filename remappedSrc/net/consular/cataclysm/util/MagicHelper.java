package net.consular.cataclysm.util;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import static net.consular.cataclysm.Cataclysm.EntityAttributes.*;

import org.jetbrains.annotations.Nullable;

public class MagicHelper {
	public static double getManaRegen(PlayerEntity player) {
		@Nullable final EntityAttributeInstance manaRegen = player.getAttributeInstance(MANA_REGEN);
		return manaRegen != null ? manaRegen.getValue() : 1D;
	}

	public static int getManaLock(PlayerEntity player) {
		@Nullable final EntityAttributeInstance manaLock = player.getAttributeInstance(MANA_LOCK);
		return (int) (manaLock != null ? manaLock.getValue() : 0D);
	}
}
