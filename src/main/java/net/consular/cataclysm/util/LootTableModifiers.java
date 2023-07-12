package net.consular.cataclysm.util;

import net.consular.cataclysm.registry.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableModifiers {
    private static final Identifier DUNGEON_CHEST_ID
            = new Identifier("minecraft", "chests/simple_dungeon");

    private static final Identifier ANCIENT_CITY_CHEST_ID
            = new Identifier("minecraft", "chests/ancient_city");

    private static final Identifier WARDEN_LOOT_ID
            = new Identifier("minecraft", "entities/warden");

    private static final Identifier BAT_LOOT_ID
            = new Identifier("minecraft", "entities/bat");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(DUNGEON_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f)) // Drops 10% of the time
                        .with(ItemEntry.builder(ModItems.MAGIC_MIRROR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(DUNGEON_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1, 5))
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) // Drops 40% of the time
                        .with(ItemEntry.builder(ModItems.LESSER_MANA_POTION))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(ANCIENT_CITY_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1, 2))
                        .conditionally(RandomChanceLootCondition.builder(0.05f)) // Drops 5% of the time
                        .with(ItemEntry.builder(ModItems.SONIC_BOOM_SCROLL))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(ANCIENT_CITY_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1, 1))
                        .conditionally(RandomChanceLootCondition.builder(0.25f)) // Drops 25% of the time
                        .with(ItemEntry.builder(ModItems.BLANK_SPELL_TOME))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(WARDEN_LOOT_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1, 1))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // Drops 100% of the time
                        .with(ItemEntry.builder(ModItems.SCULK_HEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if(BAT_LOOT_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1, 2))
                        .conditionally(RandomChanceLootCondition.builder(0.1f)) // Drops 10% of the time
                        .with(ItemEntry.builder(ModItems.BAT_WING))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
