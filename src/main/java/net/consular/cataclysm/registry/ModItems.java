package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.item.BezoarItem;
import net.consular.cataclysm.item.BrassKnucklesItem;
import net.consular.cataclysm.item.DaggerItem;
import net.consular.cataclysm.item.EffectSwordItem;
import net.consular.cataclysm.item.GloveItem;
import net.consular.cataclysm.item.HeartNecklaceItem;
import net.consular.cataclysm.item.LuckyHorseshoeItem;
import net.consular.cataclysm.item.MagicMirrorItem;
import net.consular.cataclysm.item.ManaCloakItem;
import net.consular.cataclysm.item.ManaPotionItem;
import net.consular.cataclysm.item.ModArmorItem;
import net.consular.cataclysm.item.ModArrowItem;
import net.consular.cataclysm.item.ModBowItem;
import net.consular.cataclysm.item.ModCrossbowItem;
import net.consular.cataclysm.item.ModHoeItem;
import net.consular.cataclysm.item.ModSmithingTemplateItem;
import net.consular.cataclysm.item.ModTippedArrowItem;
import net.consular.cataclysm.item.MonocleItem;
import net.consular.cataclysm.item.ScrollItem;
import net.consular.cataclysm.item.SpellTomeItem;
import net.consular.cataclysm.item.WandOfSparkingItem;
import net.consular.cataclysm.material.ModArmorMaterials;
import net.consular.cataclysm.material.ModToolMaterials;
import net.consular.cataclysm.spells.BlankSpell;
import net.consular.cataclysm.spells.GeyserSpell;
import net.consular.cataclysm.spells.SonicBoomSpell;
import net.consular.cataclysm.util.ArrowType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModItems {

    public static final Item WAND_OF_SPARKING = registerItem("wand_of_sparking", new WandOfSparkingItem(new Item.Settings().maxCount(1).maxDamage(212)));
    
    public static final Item LESSER_MANA_POTION = registerItem("lesser_mana_potion", new ManaPotionItem(new Item.Settings().maxCount(16), 3));

    public static final Item MANA_POTION = registerItem("mana_potion", new ManaPotionItem(new Item.Settings().maxCount(16), 8));

    public static final Item GREATER_MANA_POTION = registerItem("greater_mana_potion", new ManaPotionItem(new Item.Settings().maxCount(16), 12));

    public static final Item HEART_NECKLACE = registerItem("heart_necklace", new HeartNecklaceItem(new Item.Settings().maxCount(1)));

    public static final Item BEZOAR = registerItem("bezoar", new BezoarItem(new Item.Settings().maxCount(1)));

    public static final Item BRASS_KNUCKLES = registerItem("brass_knuckles", new BrassKnucklesItem(new Item.Settings().maxCount(1)));

    public static final Item MONOCLE = registerItem("monocle", new MonocleItem(new Item.Settings().maxCount(1)));

    public static final Item LUCKY_HORSESHOE = registerItem("lucky_horseshoe", new LuckyHorseshoeItem(new Item.Settings().maxCount(1)));

    public static final Item WOODEN_DAGGER = registerItem("wooden_dagger", new DaggerItem(ToolMaterials.WOOD, 2, -1.5f, new Item.Settings().maxCount(1)));

    public static final Item STONE_DAGGER = registerItem("stone_dagger", new DaggerItem(ToolMaterials.STONE, 2, -1.5f, new Item.Settings().maxCount(1)));

    public static final Item GOLDEN_DAGGER = registerItem("golden_dagger", new DaggerItem(ToolMaterials.GOLD, 2, -1.5f, new Item.Settings().maxCount(1)));

    public static final Item IRON_DAGGER = registerItem("iron_dagger", new DaggerItem(ToolMaterials.IRON, 2, -1.5f, new Item.Settings().maxCount(1)));

    public static final Item DIAMOND_DAGGER = registerItem("diamond_dagger", new DaggerItem(ToolMaterials.DIAMOND, 2, -1.5f, new Item.Settings().maxCount(1)));

    public static final Item NETHERITE_DAGGER = registerItem("netherite_dagger", new DaggerItem(ToolMaterials.NETHERITE, 2, -1.5f, new Item.Settings().maxCount(1).fireproof()));

    public static final Item ENDERITE_DAGGER = registerItem("enderite_dagger", new DaggerItem(ModToolMaterials.ENDERITE, 2, -1.5f, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_SWORD = registerItem("enderite_sword", new SwordItem(ModToolMaterials.ENDERITE, 3, -2.4f, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_PICKAXE = registerItem("enderite_pickaxe", new PickaxeItem(ModToolMaterials.ENDERITE, 1, -2.8f, new Item.Settings().maxCount(1)));
    
    public static final Item ENDERITE_AXE = registerItem("enderite_axe", new AxeItem(ModToolMaterials.ENDERITE, 5, -3f, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_SHOVEL = registerItem("enderite_shovel", new ShovelItem(ModToolMaterials.ENDERITE, 1.5f, -3f, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_HOE = registerItem("enderite_hoe", new ModHoeItem(ModToolMaterials.ENDERITE, -5, 0f, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_HELMET = registerItem("enderite_helmet", new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_CHESTPLATE = registerItem("enderite_chestplate", new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_LEGGINGS = registerItem("enderite_leggings", new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_BOOTS = registerItem("enderite_boots", new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_SCRAP = registerItem("enderite_scrap", new Item(new Item.Settings()));

    public static final Item ENDERITE_INGOT = registerItem("enderite_ingot", new Item(new Item.Settings()));

    public static final Item ENDERITE_UPGRADE_SMITHING_TEMPLATE = registerItem("enderite_upgrade_smithing_template", (Item)ModSmithingTemplateItem.createEnderiteUpgrade());

    public static final Item MAGIC_MIRROR = registerItem("magic_mirror", new MagicMirrorItem(new Item.Settings().maxCount(1)));

    public static final Item IRON_BOW = registerItem("iron_bow", new ModBowItem(new Item.Settings().maxCount(1).maxDamage(384 * 2), 0.5f, 4.0f));

    public static final Item DIAMOND_BOW = registerItem("diamond_bow", new ModBowItem(new Item.Settings().maxCount(1).maxDamage(384 * 3), 0.4f, 5.0f));

    public static final Item NETHERITE_BOW = registerItem("netherite_bow", new ModBowItem(new Item.Settings().maxCount(1).fireproof().maxDamage(384 * 4), 0.3f, 5.0f));

    public static final Item ENDERITE_BOW = registerItem("enderite_bow", new ModBowItem(new Item.Settings().maxCount(1).maxDamage(384 * 5), 0.2f, 5.0f));

    public static final Item IRON_CROSSBOW = registerItem("iron_crossbow", new ModCrossbowItem(new Item.Settings().maxCount(1).maxDamage(0), 0.5f, 4.0f));
    
    public static final Item IRON_ARROW = registerItem("iron_arrow", new ModArrowItem(new Item.Settings(), ArrowType.IRON));

    public static final Item TIPPED_IRON_ARROW = registerItem("tipped_iron_arrow", new ModTippedArrowItem(new Item.Settings(), ArrowType.IRON));

    public static final Item DIAMOND_ARROW = registerItem("diamond_arrow", new ModArrowItem(new Item.Settings(), ArrowType.DIAMOND));

    public static final Item TIPPED_DIAMOND_ARROW = registerItem("tipped_diamond_arrow", new ModTippedArrowItem(new Item.Settings(), ArrowType.DIAMOND));

    public static final Item NETHERITE_ARROW = registerItem("netherite_arrow", new ModArrowItem(new Item.Settings().fireproof(), ArrowType.NETHERITE));

    public static final Item TIPPED_NETHERITE_ARROW = registerItem("tipped_netherite_arrow", new ModTippedArrowItem(new Item.Settings().fireproof(), ArrowType.NETHERITE));

    public static final Item ENDERITE_ARROW = registerItem("enderite_arrow", new ModArrowItem(new Item.Settings(), ArrowType.ENDERITE));

    public static final Item TIPPED_ENDERITE_ARROW = registerItem("tipped_enderite_arrow", new ModTippedArrowItem(new Item.Settings(), ArrowType.ENDERITE));

    public static final Item SCULK_ARROW = registerItem("sculk_arrow", new ModArrowItem(new Item.Settings(), ArrowType.SCULK));
    
    public static final Item TIPPED_SCULK_ARROW = registerItem("tipped_sculk_arrow", new ModTippedArrowItem(new Item.Settings(), ArrowType.SCULK));

    public static final Item SCULK_HEART = registerItem("sculk_heart", new Item(new Item.Settings()));

    public static final Item STUDDED_LEATHER_HELMET = registerItem("studded_leather_helmet", new ModArmorItem(ModArmorMaterials.STUDDED_LEATHER, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)));

    public static final Item STUDDED_LEATHER_CHESTPLATE = registerItem("studded_leather_chestplate", new ModArmorItem(ModArmorMaterials.STUDDED_LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)));

    public static final Item STUDDED_LEATHER_LEGGINGS = registerItem("studded_leather_leggings", new ModArmorItem(ModArmorMaterials.STUDDED_LEATHER, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)));

    public static final Item STUDDED_LEATHER_BOOTS = registerItem("studded_leather_boots", new ModArmorItem(ModArmorMaterials.STUDDED_LEATHER, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)));

    public static final Item WOODEN_HELMET = registerItem("wooden_helmet", new ModArmorItem(ModArmorMaterials.WOOD, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)));

    public static final Item WOODEN_CHESTPLATE = registerItem("wooden_chestplate", new ModArmorItem(ModArmorMaterials.WOOD, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)));

    public static final Item WOODEN_LEGGINGS = registerItem("wooden_leggings", new ModArmorItem(ModArmorMaterials.WOOD, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)));

    public static final Item WOODEN_BOOTS = registerItem("wooden_boots", new ModArmorItem(ModArmorMaterials.WOOD, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)));

    public static final Item LUSH_HELMET = registerItem("lush_helmet", new ModArmorItem(ModArmorMaterials.LUSH, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)));

    public static final Item LUSH_CHESTPLATE = registerItem("lush_chestplate", new ModArmorItem(ModArmorMaterials.LUSH, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)));

    public static final Item LUSH_LEGGINGS = registerItem("lush_leggings", new ModArmorItem(ModArmorMaterials.LUSH, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)));

    public static final Item LUSH_BOOTS = registerItem("lush_boots", new ModArmorItem(ModArmorMaterials.LUSH, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)));

    public static final Item STINGER = registerItem("stinger", new Item(new Item.Settings()));

    public static final Item RUBY = registerItem("ruby", new Item(new Item.Settings()));
    
    public static final Item SAPPHIRE = registerItem("sapphire", new Item(new Item.Settings()));

    public static final Item SPORE_SWORD = registerItem("spore_sword", new EffectSwordItem(ModToolMaterials.SPORE, 3, -2.4f, new Item.Settings().maxCount(1), StatusEffects.POISON, 100));

    public static final Item LEATHER_GLOVE = registerItem("leather_glove", new GloveItem(ModToolMaterials.LEATHER, new Item.Settings().maxCount(1)));

    public static final Item IRON_GLOVE = registerItem("iron_glove", new GloveItem(ToolMaterials.IRON, new Item.Settings().maxCount(1)));

    public static final Item GOLDEN_GLOVE = registerItem("golden_glove", new GloveItem(ToolMaterials.GOLD, new Item.Settings().maxCount(1)));

    public static final Item DIAMOND_GLOVE = registerItem("diamond_glove", new GloveItem(ToolMaterials.DIAMOND, new Item.Settings().maxCount(1)));

    public static final Item NETHERITE_GLOVE = registerItem("netherite_glove", new GloveItem(ToolMaterials.NETHERITE, new Item.Settings().maxCount(1)));

    public static final Item ENDERITE_GLOVE = registerItem("enderite_glove", new GloveItem(ModToolMaterials.ENDERITE, new Item.Settings().maxCount(1)));

    public static final Item BLANK_SPELL_TOME = registerItem("blank_spell_tome", new SpellTomeItem(new Item.Settings(), new BlankSpell()));

    public static final Item SONIC_BOOM_TOME = registerItem("sonic_boom_tome", new SpellTomeItem(new Item.Settings().maxCount(1), new SonicBoomSpell()));

    public static final Item GEYSER_TOME = registerItem("geyser_tome", new SpellTomeItem(new Item.Settings(), new GeyserSpell()));

    public static final Item SONIC_BOOM_SCROLL = registerItem("sonic_boom_scroll", new ScrollItem(new Item.Settings().maxCount(1), new SonicBoomSpell()));

    public static final Item BAT_WING = registerItem("bat_wing", new Item(new Item.Settings()));

    public static final Item BAT_HELMET = registerItem("bat_helmet", new ModArmorItem(ModArmorMaterials.BAT, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)));

    public static final Item BAT_CHESTPLATE = registerItem("bat_chestplate", new ModArmorItem(ModArmorMaterials.BAT, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)));

    public static final Item BAT_LEGGINGS = registerItem("bat_leggings", new ModArmorItem(ModArmorMaterials.BAT, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)));

    public static final Item BAT_BOOTS = registerItem("bat_boots", new ModArmorItem(ModArmorMaterials.BAT, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)));

    public static final Item SHULK_HELMET = registerItem("shulk_helmet", new ModArmorItem(ModArmorMaterials.SHULK, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)));

    public static final Item SHULK_CHESTPLATE = registerItem("shulk_chestplate", new ModArmorItem(ModArmorMaterials.SHULK, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)));

    public static final Item SHULK_LEGGINGS = registerItem("shulk_leggings", new ModArmorItem(ModArmorMaterials.SHULK, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)));

    public static final Item SHULK_BOOTS = registerItem("shulk_boots", new ModArmorItem(ModArmorMaterials.SHULK, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)));

    public static final Item SAPPHIRE_CLOAK = registerItem("sapphire_cloak", new ModArmorItem(ModArmorMaterials.CLOAK, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Cataclysm.MODID, name), item);
    }

    public static void registerItems(){
        Cataclysm.LOGGER.info("Registering items");
    }
}
