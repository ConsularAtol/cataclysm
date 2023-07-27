package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.entity.HornetEntity;
import net.consular.cataclysm.entity.MagicProjectileEntity;
import net.consular.cataclysm.entity.MagicSwordEntity;
import net.consular.cataclysm.entity.ModArrowEntity;
import net.consular.cataclysm.entity.ModFallingBlockEntity;
import net.consular.cataclysm.entity.StingerEntity;
import net.consular.cataclysm.entity.WandOfSparkingEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntities {
    public static final EntityType<WandOfSparkingEntity> WAND_OF_SPARKING = Registry.register(
	   Registries.ENTITY_TYPE,
	   new Identifier(Cataclysm.MODID, "wand_of_sparking"),
           FabricEntityTypeBuilder.<WandOfSparkingEntity>create(SpawnGroup.MISC, WandOfSparkingEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeBlocks(4).trackedUpdateRate(10).build() 
    );

    public static final EntityType<MagicProjectileEntity> MAGIC_PROJECTILE = Registry.register(
	   Registries.ENTITY_TYPE,
	   new Identifier(Cataclysm.MODID, "magic_projectile"),
           FabricEntityTypeBuilder.<MagicProjectileEntity>create(SpawnGroup.MISC, MagicProjectileEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeBlocks(4).trackedUpdateRate(10).build() 
    );

    public static final EntityType<MagicSwordEntity> MAGIC_SWORD = Registry.register(
	   Registries.ENTITY_TYPE,
	   new Identifier(Cataclysm.MODID, "magic_sword"),
           FabricEntityTypeBuilder.<MagicSwordEntity>create(SpawnGroup.MISC, MagicSwordEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeBlocks(4).trackedUpdateRate(10).build() 
    );

    public static final EntityType<ModArrowEntity> MOD_ARROW = Registry.register(
	   Registries.ENTITY_TYPE,
	   new Identifier(Cataclysm.MODID, "mod_arrow"),
           FabricEntityTypeBuilder.<ModArrowEntity>create(SpawnGroup.MISC, ModArrowEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeBlocks(4).trackedUpdateRate(10).build() 
    );

    public static final EntityType<StingerEntity> STINGER = Registry.register(
	   Registries.ENTITY_TYPE,
	   new Identifier(Cataclysm.MODID, "stinger"),
           FabricEntityTypeBuilder.<StingerEntity>create(SpawnGroup.MISC, StingerEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackRangeBlocks(4).trackedUpdateRate(10).build() 
    );

    public static final EntityType<HornetEntity> HORNET = Registry.register(
	   Registries.ENTITY_TYPE,
	   new Identifier(Cataclysm.MODID, "hornet"),
           FabricEntityTypeBuilder.<HornetEntity>create(SpawnGroup.MONSTER, HornetEntity::new).dimensions(EntityDimensions.fixed(0.5F, 1.25F)).build() 
    );

    public static final EntityType<ModFallingBlockEntity> MOD_FALLING_BLOCK = Registry.register(
	   Registries.ENTITY_TYPE,
	   new Identifier(Cataclysm.MODID, "mod_falling_block"),
           FabricEntityTypeBuilder.<ModFallingBlockEntity>create(SpawnGroup.MISC, ModFallingBlockEntity::new).dimensions(EntityDimensions.fixed(0.5F, 1.25F)).build() 
    );

    public static void registerAllEntities(){
        Cataclysm.LOGGER.info("Registering entites");;
    }
}
    
