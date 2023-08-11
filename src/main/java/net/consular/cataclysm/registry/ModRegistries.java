package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.entity.HornetEntity;
import net.consular.cataclysm.event.SneakAttackHandler;
import net.consular.cataclysm.util.LootTableModifiers;
import net.consular.cataclysm.world.gen.ModEntitySpawn;
import net.consular.cataclysm.world.gen.ModWorldGeneration;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.block.Blocks;
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
        SneakAttackHandler.initialize();
        ModGamerules.registerGamerules();
        ModParticles.registerParticles();

        registerAttributes();
        registerMobAttributes();
        registerFlattenables();
    }

    private static void registerAttributes(){
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "mana_regen"), net.consular.cataclysm.Cataclysm.EntityAttributes.MANA_REGEN);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "max_mana"), net.consular.cataclysm.Cataclysm.EntityAttributes.MANA_MAX);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "arrow_damage"), net.consular.cataclysm.Cataclysm.EntityAttributes.ARROW_DAMAGE);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "dagger_damage_boost"), net.consular.cataclysm.Cataclysm.EntityAttributes.DAGGER_DAMAGE_BOOST);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "sneak_attack_damage"), net.consular.cataclysm.Cataclysm.EntityAttributes.SNEAK_ATTACK_DAMAGE);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "unarmed_damage"), net.consular.cataclysm.Cataclysm.EntityAttributes.UNARMED_DAMAGE);
        Registry.register(Registries.ATTRIBUTE, new Identifier(Cataclysm.MODID, "mana_lock"), net.consular.cataclysm.Cataclysm.EntityAttributes.MANA_LOCK);
    }

    private static void registerMobAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.HORNET, HornetEntity.setAttributes());
    }

    private static void registerFlattenables(){
        FlattenableBlockRegistry.register(Blocks.MYCELIUM, ModBlocks.MYCELIUM_PATH.getDefaultState());
    }
}
