package net.consular.cataclysm.world.gen;

import net.consular.cataclysm.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class QuicksandGeneration {
    public static void generateQuicksand(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DESERT), GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.QUICKSAND_PLACED);
    }
}
