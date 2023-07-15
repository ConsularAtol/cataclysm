package net.consular.cataclysm.material;

import java.util.function.Supplier;

import net.consular.cataclysm.registry.ModItems;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

public enum ModToolMaterials implements ToolMaterial {

    ENDERITE(5, 2501, 10.0f, 5.0f, 18, () -> Ingredient.ofItems(ModItems.ENDERITE_INGOT)),
    SCULK(6, 2812, 11.0f, 6.0f, 23, () -> Ingredient.ofItems(Items.ECHO_SHARD)),
    SPORE(2, 250, 6.0f, 2.0f, 14, () -> Ingredient.ofItems(ModItems.STINGER)),
    LEATHER(0, 59, 2.0f, 0.0f, 14, () -> Ingredient.ofItems(Items.LEATHER));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<Ingredient>(repairIngredient);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
