package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static final TagKey<Item> DAGGER_BOOST = TagKey.of(RegistryKeys.ITEM, new Identifier(Cataclysm.MODID, "dagger_boost"));
    public static final TagKey<Item> CLIMBING_ARMOR = TagKey.of(RegistryKeys.ITEM, new Identifier(Cataclysm.MODID, "climbing_armor"));
    public static final TagKey<EntityType<?>> CAN_WALK_ON_QUICKSAND = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(Cataclysm.MODID, "can_walk_on_quicksand"));
}
