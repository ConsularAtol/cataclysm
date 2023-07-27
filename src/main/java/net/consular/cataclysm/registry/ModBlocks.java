package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.block.BewitchingTableBlock;
import net.consular.cataclysm.block.MoltenMagmaBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block ENDERITE_ORE = registerBlock("enderite_ore",
    new ExperienceDroppingBlock(FabricBlockSettings.create().requiresTool().strength(30.0f, 1200.0f), UniformIntProvider.create(0, 2)));

    public static final Block RUBY_ORE = registerBlock("ruby_ore",
    new ExperienceDroppingBlock(FabricBlockSettings.create().requiresTool().strength(3.0f, 3.0f), UniformIntProvider.create(3, 7)));

    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
    new ExperienceDroppingBlock(FabricBlockSettings.create().luminance(3).requiresTool().strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(3, 7)));

    public static final Block MOLTEN_MAGMA = registerBlock("molten_magma",
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

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
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
