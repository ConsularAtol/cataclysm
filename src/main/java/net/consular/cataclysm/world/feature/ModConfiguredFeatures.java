package net.consular.cataclysm.world.feature;

import java.util.List;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ENDERITE_ORE = registerKey("enderite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE = registerKey("ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SAPPHIRE_ORE = registerKey("sapphire_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_GREEN_MUSHROOM = registerKey("huge_green_mushroom");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_MUSHROOM = registerKey("huge_purple_mushroom");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, HUGE_GREEN_MUSHROOM, Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of((BlockState)ModBlocks.GREEN_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of((BlockState)((BlockState)Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false)).with(MushroomBlock.DOWN, false)), 2));
        register(context, HUGE_PURPLE_MUSHROOM, Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of((BlockState)ModBlocks.PURPLE_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of((BlockState)((BlockState)Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false)).with(MushroomBlock.DOWN, false)), 2));
        
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
 
        List<OreFeatureConfig.Target> overworldRubyOres =   
             List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.RUBY_ORE.getDefaultState()),
                     OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_RUBY_ORE.getDefaultState()));
         
         register(context, RUBY_ORE, Feature.ORE, new OreFeatureConfig(overworldRubyOres, 3));
        
        List<OreFeatureConfig.Target> overworldSapphireOres =   
             List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.SAPPHIRE_ORE.getDefaultState()),
                     OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.getDefaultState()));
         
         register(context, SAPPHIRE_ORE, Feature.ORE, new OreFeatureConfig(overworldSapphireOres, 3));

        List<OreFeatureConfig.Target> enderiteOres =   
             List.of(OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE), ModBlocks.ENDERITE_ORE.getDefaultState()));
         
         register(context, ENDERITE_ORE, Feature.ORE, new OreFeatureConfig(enderiteOres, 3));
     }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Cataclysm.MODID, name));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
    
}
