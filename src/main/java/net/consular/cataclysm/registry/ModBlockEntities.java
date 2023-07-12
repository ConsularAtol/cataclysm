package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.block.entity.BewitchingTableBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<BewitchingTableBlockEntity> BEWITCHING_TABLE;

    public static void registerBlockEntities() {
        BEWITCHING_TABLE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Cataclysm.MODID, "bewitching_table"),
                FabricBlockEntityTypeBuilder.create(BewitchingTableBlockEntity::new,
                        ModBlocks.BEWITCHING_TABLE).build(null));
    }
}
