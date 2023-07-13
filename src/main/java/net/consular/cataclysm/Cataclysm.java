package net.consular.cataclysm;

import net.consular.cataclysm.registry.ModRegistries;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cataclysm implements ModInitializer {
	
	public static final String MODID = "cataclysm";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		DataTrackers.MANA.getId();

		ModRegistries.registerAll();
	}

	public static class DataTrackers {
		public static final TrackedData<Integer> MANA = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
		public static final TrackedData<Boolean> SHOW_MANA = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	}

	public static class EntityAttributes {
		public static final EntityAttribute MANA_REGEN = new ClampedEntityAttribute("attribute.name.generic." + Cataclysm.MODID + ".mana_regen", 1D, 0D, 1024D).setTracked(true);
		public static final EntityAttribute MANA_MAX = new ClampedEntityAttribute("attribute.name.generic." + Cataclysm.MODID + ".max_mana", 10D, 0D, 1024D).setTracked(true);
		public static final EntityAttribute DAGGER_DAMAGE_BOOST = new ClampedEntityAttribute("attribute.name.generic." + Cataclysm.MODID + ".dagger_damage_boost", 0D, 0D, 20D).setTracked(true);
		public static final EntityAttribute UNARMED_DAMAGE = new ClampedEntityAttribute("attribute.name.generic." + Cataclysm.MODID + ".unarmed_damage", 1D, 0D, 20D).setTracked(true);
		public static final EntityAttribute MANA_LOCK = new ClampedEntityAttribute("attribute.name.generic." + Cataclysm.MODID + ".mana_lock", 0D, 0D, 20D).setTracked(true);
	}

}
