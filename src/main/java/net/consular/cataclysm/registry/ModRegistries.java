package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.entity.HornetEntity;
import net.consular.cataclysm.util.LootTableModifiers;
import net.consular.cataclysm.world.gen.ModEntitySpawn;
import net.consular.cataclysm.world.gen.ModWorldGeneration;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRegistries {

    public static void registerAll(){
        ModItems.registerItems();
        ModEntities.registerAllEntities();
        ModEffects.registerEffects();
        ModBlocks.registerModBlocks();
        ModSounds.registerSounds();
        ModWorldGeneration.generateModWorldGen();
        ModEntitySpawn.addEntitySpawn();
        LootTableModifiers.modifyLootTables();
        ModScreenHandlers.registerAllScreenHandlers();
        ModRecipeTypes.registerAllRecipeTypes();
        ModRecipeSerializer.registerSerializer();
        ModEnchantmentTargets.registerEnchantmentTargets();
        ModEnchantments.registerModEnchantments();
        ModItemGroups.registerItemGroups();

        registerAttributes();
        registerMobAttributes();
    }

    private static void registerAttributes(){
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "mana_regen"), net.consular.cataclysm.Cataclysm.EntityAttributes.MANA_REGEN);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "max_mana"), net.consular.cataclysm.Cataclysm.EntityAttributes.MANA_MAX);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "dagger_damage_boost"), net.consular.cataclysm.Cataclysm.EntityAttributes.DAGGER_DAMAGE_BOOST);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "unarmed_damage"), net.consular.cataclysm.Cataclysm.EntityAttributes.UNARMED_DAMAGE);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "mana_lock"), net.consular.cataclysm.Cataclysm.EntityAttributes.MANA_LOCK);
    }

    private static void registerMobAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.HORNET, HornetEntity.setAttributes());
    }
}
