package net.consular.cataclysm;

import net.consular.cataclysm.data.ModLootTableGenerator;
import net.consular.cataclysm.data.ModModelProvider;
import net.consular.cataclysm.data.ModRecipeGenerator;
import net.consular.cataclysm.data.ModWorldGenerator;
import net.consular.cataclysm.world.feature.ModConfiguredFeatures;
import net.consular.cataclysm.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class CataclysmDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModLootTableGenerator::new);
		pack.addProvider(ModRecipeGenerator::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, (Registerable<ConfiguredFeature<?, ?>> context) -> ModConfiguredFeatures.bootstrap(context));
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
