package net.consular.cataclysm.data;

import net.consular.cataclysm.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator){

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ENDERITE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ENDERITE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ENDERITE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ENDERITE_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SCULK_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SCULK_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SCULK_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SCULK_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.STUDDED_LEATHER_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.STUDDED_LEATHER_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.STUDDED_LEATHER_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.STUDDED_LEATHER_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SHULK_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SHULK_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SHULK_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SHULK_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.BAT_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.BAT_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.BAT_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.BAT_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.LUSH_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.LUSH_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.LUSH_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.LUSH_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.CRIMSON_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.CRIMSON_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.CRIMSON_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.CRIMSON_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.CHORUS_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.CHORUS_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.CHORUS_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.CHORUS_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.WARPED_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.WARPED_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.WARPED_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.WARPED_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.HIDE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.HIDE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.HIDE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.HIDE_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.GILDED_BLACKSTONE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.GILDED_BLACKSTONE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.GILDED_BLACKSTONE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.GILDED_BLACKSTONE_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.PHASE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.PHASE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.PHASE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.PHASE_BOOTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SAPPHIRE_HOOD);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SAPPHIRE_CLOAK);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SAPPHIRE_PANTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.SAPPHIRE_SHOES);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.QUARTZ_HOOD);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.QUARTZ_CLOAK);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.QUARTZ_PANTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.QUARTZ_SHOES);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.AMETHYST_HOOD);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.AMETHYST_CLOAK);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.AMETHYST_PANTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.AMETHYST_SHOES);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ECHO_HOOD);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ECHO_CLOAK);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ECHO_PANTS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ECHO_SHOES);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ANCIENT_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ANCIENT_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ANCIENT_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ANCIENT_BOOTS);
    }
}
