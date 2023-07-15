package net.consular.cataclysm.material;

import net.consular.cataclysm.registry.ModItems;
import net.consular.cataclysm.registry.ModSounds;
import net.consular.cataclysm.util.ModArmorMaterial;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ModArmorMaterial {
    WOOD("wood", 5, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.LEGGINGS, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.HELMET, 1);
    }), 2, SoundEvents.BLOCK_WOOD_PLACE, 0F, 0F, 0F, 0.25F, 0F, 0F, 0F,() -> {
        return Ingredient.fromTag(ItemTags.LOGS);
    }),
    LUSH("lush", 15, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.CHESTPLATE, 4);
        map.put(ArmorItem.Type.HELMET, 2);
    }), 12, SoundEvents.BLOCK_VINE_STEP, 0F, 0F, 0.5F, 0.5F, 0F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.VINE});
    }),
    BAT("bat", 13, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.CHESTPLATE, 4);
        map.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ENTITY_BAT_TAKEOFF, 0F, 0F, 0.375F, 0F, 0F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{ModItems.BAT_WING});
    }),
    HIDE("hide", 7, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.LEGGINGS, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.HELMET, 1);
    }), 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F,() -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.RABBIT_HIDE});
    }),
    GILDED_BLACKSTONE("gilded_blackstone", 20, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.HELMET, 3);
    }), 12, SoundEvents.BLOCK_GILDED_BLACKSTONE_PLACE, 0F, 0F, 0F, 0F, 0F, 0.3F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.GILDED_BLACKSTONE});
    }),
    PHASE("phase", 24, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.HELMET, 3);
    }), 12, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0F, 0F, 0F, 0F, 0F, 0.4F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.ENDER_PEARL});
    }),
    STUDDED_LEATHER("studded_leather", 11, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.LEGGINGS, 3);
        map.put(ArmorItem.Type.CHESTPLATE, 4);
        map.put(ArmorItem.Type.HELMET, 1);
    }), 13, ModSounds.STUDDED_LEATHER_EQUIP, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.IRON_NUGGET});
    }),
    SHULK("shulk", 33, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.CHESTPLATE, 7);
        map.put(ArmorItem.Type.HELMET, 3);
    }), 15, SoundEvents.ENTITY_SHULKER_CLOSE, 1F, 0.1F, 1F, 0F, 0F, 0F, 0.25F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.SHULKER_SHELL});
    }),
    WARPED("warped", 20, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.HELMET, 3);
    }), 6, SoundEvents.BLOCK_WEEPING_VINES_PLACE, 0F, 0F, 0.75F, 0F, 0F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.TWISTING_VINES});
    }),
    CRIMSON("crimson", 20, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.HELMET, 3);
    }), 6, SoundEvents.BLOCK_VINE_PLACE, 0F, 0F, 0F, 0.75F, 0F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.WEEPING_VINES});
    }),
    CHORUS("chorus", 33, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.HELMET, 3);
    }), 12, SoundEvents.BLOCK_CHORUS_FLOWER_GROW, 1F, 0F, 0F, 1F, 0F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.CHORUS_FLOWER});
    }),
    SAPPHIRE("cloak", 5, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 0);
        map.put(ArmorItem.Type.LEGGINGS, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.HELMET, 0);
    }), 15, SoundEvents.BLOCK_WOOL_PLACE, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{ModItems.SAPPHIRE});
    }),
    QUARTZ("cloak", 8, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.LEGGINGS, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.HELMET, 1);
    }), 18, SoundEvents.BLOCK_WOOL_PLACE, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.QUARTZ});
    }),
    AMETHYST("amethyst", 16, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.LEGGINGS, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.HELMET, 1);
    }), 22, SoundEvents.BLOCK_WOOL_PLACE, 0F, 0F, 0F, 0F, 0.4F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.AMETHYST_SHARD});
    }),
    ECHO("echo", 20, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.LEGGINGS, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.HELMET, 1);
    }), 28, SoundEvents.BLOCK_WOOL_PLACE, 0F, 0F, 0F, 0F, 0.6F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.ECHO_SHARD});
    }),
    ENDERITE("enderite", 41, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 7);
        map.put(ArmorItem.Type.CHESTPLATE, 9);
        map.put(ArmorItem.Type.HELMET, 3);
    }), 19, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 4.0F, 0.2F, 0F, 0F, 0F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{ModItems.ENDERITE_INGOT});
    }),
    SCULK("sculk", 49, Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 4);
        map.put(ArmorItem.Type.LEGGINGS, 8);
        map.put(ArmorItem.Type.CHESTPLATE, 9);
        map.put(ArmorItem.Type.HELMET, 4);
    }), 24, SoundEvents.BLOCK_SCULK_SENSOR_CLICKING, 4.0F, 0.3F, 0F, 0F, 0F, 0F, 0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{Items.ECHO_SHARD});
    });

    public static final StringIdentifiable.Codec<ArmorMaterials> CODEC = StringIdentifiable.createCodec(ArmorMaterials::values);
    private static final EnumMap<ArmorItem.Type, Integer> BASE_DURABILITY = Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
        map.put(ArmorItem.Type.BOOTS, 13);
        map.put(ArmorItem.Type.LEGGINGS, 15);
        map.put(ArmorItem.Type.CHESTPLATE, 16);
        map.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final float daggerBoost;
    private final float sneakAttackDamage;
    private final float unarmedDamage;
    private final float maxMana;
    private final float arrowDamage;
    private final Lazy<Ingredient> repairIngredientSupplier;

    private ModArmorMaterials(String name, int durabilityMultiplier, EnumMap protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, float daggerBoost, float unarmedDamage, float maxMana, float arrowDamage, float sneakAttackDamage, Supplier repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.daggerBoost = daggerBoost;
        this.unarmedDamage = unarmedDamage;
        this.maxMana = maxMana;
        this.arrowDamage = arrowDamage;
        this.sneakAttackDamage = sneakAttackDamage;
        this.repairIngredientSupplier = new Lazy(repairIngredientSupplier);
    }

    public int getDurability(ArmorItem.Type type) {
        return (Integer)BASE_DURABILITY.get(type) * this.durabilityMultiplier;
    }

    public int getProtection(ArmorItem.Type type) {
        return (Integer)this.protectionAmounts.get(type);
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredientSupplier.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String asString() {
        return this.name;
    }

    @Override
    public float getDaggerBoost() {
        return this.daggerBoost;
    }

    @Override
    public float getUnarmedDamage() {
        return this.unarmedDamage;
    }

    @Override
    public float getMaxMana() {
        return this.maxMana;
    }

    @Override
    public float getArrowDamage() {
        return this.arrowDamage;
    }

    @Override
    public float getSneakAttackDamage() {
        return this.sneakAttackDamage;
    }
}