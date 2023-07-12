package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    private static final RegistryKey<ItemGroup> CATACLYSM = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Cataclysm.MODID, "cataclysm"));

    private static final RegistryKey<ItemGroup> ACCESSORIES = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Cataclysm.MODID, "accessories"));

    private static final RegistryKey<ItemGroup> MAGIC = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Cataclysm.MODID, "magic"));

    private static final RegistryKey<ItemGroup> MELEE = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Cataclysm.MODID, "melee"));

    private static final RegistryKey<ItemGroup> ROGUE = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Cataclysm.MODID, "rogue"));

    private static final RegistryKey<ItemGroup> MONK = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Cataclysm.MODID, "monk"));

    private static final RegistryKey<ItemGroup> RANGED = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Cataclysm.MODID, "ranged"));

    private static void register(){
        Registry.register(Registries.ITEM_GROUP, CATACLYSM, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.ENDERITE_ORE))
            .displayName(Text.translatable("itemGroup.cataclysm.cataclysm"))
            .build());
        
        Registry.register(Registries.ITEM_GROUP, ACCESSORIES, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.HEART_NECKLACE))
            .displayName(Text.translatable("itemGroup.cataclysm.accessories"))
            .build());

        Registry.register(Registries.ITEM_GROUP, MAGIC, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.BLANK_SPELL_TOME))
            .displayName(Text.translatable("itemGroup.cataclysm.magic"))
            .build());

        Registry.register(Registries.ITEM_GROUP, MELEE, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ENDERITE_SWORD))
            .displayName(Text.translatable("itemGroup.cataclysm.melee"))
            .build());

        Registry.register(Registries.ITEM_GROUP, ROGUE, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.DIAMOND_DAGGER))
            .displayName(Text.translatable("itemGroup.cataclysm.rogue"))
            .build());

        Registry.register(Registries.ITEM_GROUP, MONK, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.WOODEN_CHESTPLATE))
            .displayName(Text.translatable("itemGroup.cataclysm.monk"))
            .build());

        Registry.register(Registries.ITEM_GROUP, RANGED, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.IRON_BOW))
            .displayName(Text.translatable("itemGroup.cataclysm.ranged"))
            .build());
    }

    public static void registerItemGroups(){
        register();
        ItemGroupEvents.modifyEntriesEvent(CATACLYSM).register(content -> {
            content.add(ModBlocks.ENDERITE_ORE);
            content.add(ModBlocks.ENDERITE_ORE);
            content.add(ModItems.ENDERITE_SCRAP);
            content.add(ModItems.ENDERITE_INGOT);
            content.add(ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE);
            content.add(ModItems.MAGIC_MIRROR);
            content.add(ModItems.SCULK_HEART);
            content.add(ModItems.STINGER);
            content.add(ModItems.RUBY);
            content.add(ModBlocks.RUBY_ORE);
            content.add(ModBlocks.DEEPSLATE_RUBY_ORE);
            content.add(ModBlocks.RUBY_BLOCK);
            content.add(ModItems.SAPPHIRE);
            content.add(ModBlocks.SAPPHIRE_ORE);
            content.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
            content.add(ModBlocks.SAPPHIRE_BLOCK);
            content.add(ModItems.ENDERITE_PICKAXE);
            content.add(ModItems.ENDERITE_AXE);
            content.add(ModItems.ENDERITE_SHOVEL);
            content.add(ModItems.ENDERITE_HOE);
            content.add(ModBlocks.BEWITCHING_TABLE);
            content.add(ModItems.BAT_WING);
        });
        ItemGroupEvents.modifyEntriesEvent(ACCESSORIES).register(content -> {
            content.add(ModItems.HEART_NECKLACE);
            content.add(ModItems.BRASS_KNUCKLES);
            content.add(ModItems.MANA_CLOAK);
            content.add(ModItems.MONOCLE);
            content.add(ModItems.LUCKY_HORSESHOE);
            content.add(ModItems.BEZOAR);
        });
        ItemGroupEvents.modifyEntriesEvent(MAGIC).register(content -> {
            content.add(ModItems.WAND_OF_SPARKING);
            content.add(ModItems.LESSER_MANA_POTION);
            content.add(ModItems.MANA_POTION);
            content.add(ModItems.GREATER_MANA_POTION);
            content.add(ModItems.BLANK_SPELL_TOME);
            content.add(ModItems.SONIC_BOOM_TOME);
            content.add(ModItems.SONIC_BOOM_SCROLL);
        });
        ItemGroupEvents.modifyEntriesEvent(MELEE).register(content -> {
            content.add(ModItems.ENDERITE_SWORD);
            content.add(ModItems.SPORE_SWORD);
            content.add(ModItems.ENDERITE_HELMET);
            content.add(ModItems.ENDERITE_CHESTPLATE);
            content.add(ModItems.ENDERITE_LEGGINGS);
            content.add(ModItems.ENDERITE_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(ROGUE).register(content -> {
            content.add(ModItems.WOODEN_DAGGER);
            content.add(ModItems.STONE_DAGGER);
            content.add(ModItems.IRON_DAGGER);
            content.add(ModItems.GOLDEN_DAGGER);
            content.add(ModItems.DIAMOND_DAGGER);
            content.add(ModItems.NETHERITE_DAGGER);
            content.add(ModItems.ENDERITE_DAGGER);
            content.add(ModItems.STUDDED_LEATHER_HELMET);
            content.add(ModItems.STUDDED_LEATHER_CHESTPLATE);
            content.add(ModItems.STUDDED_LEATHER_LEGGINGS);
            content.add(ModItems.STUDDED_LEATHER_BOOTS);
            content.add(ModItems.LUSH_HELMET);
            content.add(ModItems.LUSH_CHESTPLATE);
            content.add(ModItems.LUSH_LEGGINGS);
            content.add(ModItems.LUSH_BOOTS);
            content.add(ModItems.BAT_HELMET);
            content.add(ModItems.BAT_CHESTPLATE);
            content.add(ModItems.BAT_LEGGINGS);
            content.add(ModItems.BAT_BOOTS);
            content.add(ModItems.SHULK_HELMET);
            content.add(ModItems.SHULK_CHESTPLATE);
            content.add(ModItems.SHULK_LEGGINGS);
            content.add(ModItems.SHULK_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(MONK).register(content -> {
            content.add(ModItems.LEATHER_GLOVE);
            content.add(ModItems.IRON_GLOVE);
            content.add(ModItems.GOLDEN_GLOVE);
            content.add(ModItems.DIAMOND_GLOVE);
            content.add(ModItems.NETHERITE_GLOVE);
            content.add(ModItems.ENDERITE_GLOVE);
            content.add(ModItems.WOODEN_HELMET);
            content.add(ModItems.WOODEN_CHESTPLATE);
            content.add(ModItems.WOODEN_LEGGINGS);
            content.add(ModItems.WOODEN_BOOTS);
            content.add(ModItems.LUSH_HELMET);
            content.add(ModItems.LUSH_CHESTPLATE);
            content.add(ModItems.LUSH_LEGGINGS);
            content.add(ModItems.LUSH_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(RANGED).register(content -> {
            content.add(ModItems.IRON_BOW);
            content.add(ModItems.DIAMOND_BOW);
            content.add(ModItems.NETHERITE_BOW);
            content.add(ModItems.ENDERITE_BOW);
            content.add(ModItems.IRON_ARROW);
            content.add(ModItems.DIAMOND_ARROW);
            content.add(ModItems.NETHERITE_ARROW);
            content.add(ModItems.ENDERITE_ARROW);
            content.add(ModItems.SCULK_ARROW);
        });
    }
}
