package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.block.BewitchingTableBlock;
import net.consular.cataclysm.block.MoltenMagmaBlock;
import net.consular.cataclysm.block.QuickSandBlock;
import net.consular.cataclysm.world.feature.ModConfiguredFeatures;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DirtPathBlock;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.MushroomBlock;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.BlockView;

public class ModBlocks {

    public static final Block ENDERITE_ORE = registerBlock("enderite_ore",
    new ExperienceDroppingBlock(FabricBlockSettings.create().requiresTool().strength(30.0f, 1200.0f), UniformIntProvider.create(0, 2)));

    public static final Block RUBY_ORE = registerBlock("ruby_ore",
    new ExperienceDroppingBlock(FabricBlockSettings.create().requiresTool().strength(3.0f, 3.0f), UniformIntProvider.create(3, 7)));

    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
    new ExperienceDroppingBlock(FabricBlockSettings.create().luminance(3).requiresTool().strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(3, 7)));

    public static final Block MOLTEN_MAGMA = registerBlockWithoutItem("molten_magma",
    new MoltenMagmaBlock(FabricBlockSettings.copyOf(Blocks.MAGMA_BLOCK)));

    public static final Block RUBY_BLOCK = registerBlock("ruby_block", 
    new Block(FabricBlockSettings.create().requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)));

    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
    new ExperienceDroppingBlock(FabricBlockSettings.create().requiresTool().strength(3.0f, 3.0f), UniformIntProvider.create(3, 7)));

    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
    new ExperienceDroppingBlock(FabricBlockSettings.create().requiresTool().strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(3, 7)));

    public static final Block SAPPHIRE_BLOCK = registerBlock("sapphire_block", 
    new Block(FabricBlockSettings.create().requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)));

    public static final Block BEWITCHING_TABLE = registerBlock("bewitching_table", 
    new BewitchingTableBlock(FabricBlockSettings.create().strength(2.5f).sounds(BlockSoundGroup.WOOD)));

        public static final Block GREEN_MUSHROOM_BLOCK = registerBlock("green_mushroom_block", 
    new MushroomBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM_BLOCK)));

    public static final Block PURPLE_MUSHROOM_BLOCK = registerBlock("purple_mushroom_block", 
    new MushroomBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM_BLOCK)));

    public static final Block GREEN_MUSHROOM = registerBlock("green_mushroom",
    new MushroomPlantBlock(FabricBlockSettings.create().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).luminance(state -> 1), ModConfiguredFeatures.HUGE_GREEN_MUSHROOM));

    public static final Block PURPLE_MUSHROOM = registerBlock("purple_mushroom",
    new MushroomPlantBlock(FabricBlockSettings.create().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).luminance(state -> 1), ModConfiguredFeatures.HUGE_PURPLE_MUSHROOM));

    public static final Block POTTED_GREEN_MUSHROOM = registerBlockWithoutItem("potted_green_mushroom", 
    new FlowerPotBlock(GREEN_MUSHROOM, FabricBlockSettings.create().breakInstantly().nonOpaque()));

    public static final Block POTTED_PURPLE_MUSHROOM = registerBlockWithoutItem("potted_purple_mushroom", 
    new FlowerPotBlock(PURPLE_MUSHROOM, FabricBlockSettings.create().breakInstantly().nonOpaque()));

    public static final Block MYCELIUM_PATH = registerBlock("mycelium_path",
    new DirtPathBlock(FabricBlockSettings.copyOf(Blocks.DIRT_PATH)));

    public static final Block MUSHROOM_LOG = registerBlock("mushroom_log",
    new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));

    public static final Block MUSHROOM_WOOD = registerBlock("mushroom_wood",
    new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));

    public static final Block STRIPPED_MUSHROOM_LOG = registerBlock("stripped_mushroom_log",
    new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));

    public static final Block STRIPPED_MUSHROOM_WOOD = registerBlock("stripped_mushroom_wood",
    new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));

    public static final Block MUSHROOM_PLANKS = registerBlock("mushroom_planks",
    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    public static final Block MUSHROOM_SLAB = registerBlock("mushroom_slab",
    new Block(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));

    public static final Block QUICK_SAND = registerBlockWithoutItem("quick_sand",
    new QuickSandBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(0.25f).sounds(BlockSoundGroup.POWDER_SNOW).dynamicBounds().solidBlock(ModBlocks::never)));

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Cataclysm.MODID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(Cataclysm.MODID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Cataclysm.MODID, name), 
            new BlockItem(block, new FabricItemSettings()));
    }
    
    public static void registerModBlocks(){
        Cataclysm.LOGGER.info("Registering blocks");;
    }
}
