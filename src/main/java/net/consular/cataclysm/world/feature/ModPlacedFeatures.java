package net.consular.cataclysm.world.feature;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.registry.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> RUBY_ORE_PLACED = registerKey("ruby_ore_placed");
    public static final RegistryKey<PlacedFeature> SAPPHIRE_ORE_PLACED = registerKey("sapphire_ore_placed");
    public static final RegistryKey<PlacedFeature> ENDERITE_ORE_PLACED = registerKey("enderite_ore_placed");
    public static final RegistryKey<PlacedFeature> HUGE_GREEN_MUSHROOM_PLACED_KEY = registerKey("huge_green_mushroom_placed");
    public static final RegistryKey<PlacedFeature> HUGE_PURPLE_MUSHROOM_PLACED_KEY = registerKey("huge_purple_mushroom_placed");
    public static final RegistryKey<PlacedFeature> QUICKSAND_PLACED = registerKey("quicksand");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry.Reference<ConfiguredFeature<?, ?>> registryEntry9 = configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.QUICKSAND);
        PlacedFeatures.register(context, QUICKSAND_PLACED, registryEntry9, SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, RUBY_ORE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RUBY_ORE),
                ModOrePlacement.modifiersWithCount(16, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));

        register(context, SAPPHIRE_ORE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SAPPHIRE_ORE),
                ModOrePlacement.modifiersWithCount(16, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));

        register(context, ENDERITE_ORE_PLACED, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ENDERITE_ORE),
                ModOrePlacement.modifiersWithCount(16, // Veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));
        register(context, HUGE_GREEN_MUSHROOM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HUGE_GREEN_MUSHROOM),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(1, 0.01f, 0), ModBlocks.GREEN_MUSHROOM));
        
        register(context, HUGE_PURPLE_MUSHROOM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HUGE_PURPLE_MUSHROOM),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(1, 0.01f, 0), ModBlocks.PURPLE_MUSHROOM));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Cataclysm.MODID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    //private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
    //    register(context, key, configuration, List.of(modifiers));
    //}
}
