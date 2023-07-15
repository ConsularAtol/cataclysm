package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static final TagKey<Item> DAGGER_BOOST = TagKey.of(RegistryKeys.ITEM, new Identifier(Cataclysm.MODID, "dagger_boost"));
    public static final TagKey<Item> CLIMBING_ARMOR = TagKey.of(RegistryKeys.ITEM, new Identifier(Cataclysm.MODID, "climbing_armor"));
}
