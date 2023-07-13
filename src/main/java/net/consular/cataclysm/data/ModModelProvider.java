package net.consular.cataclysm.data;

import net.consular.cataclysm.item.ModArmorItem;
import net.consular.cataclysm.material.ModArmorMaterials;
import net.consular.cataclysm.registry.ModItems;
import net.consular.cataclysm.util.ModArmorMaterial;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;
import software.bernie.geckolib.event.GeoRenderEvent.Armor;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ENDERITE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ENDERITE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ENDERITE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem)ModItems.ENDERITE_BOOTS);
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
    }
}
