package net.consular.cataclysm.util;

import net.minecraft.item.ArmorMaterial;

public interface ModArmorMaterial extends ArmorMaterial{
    public float getDaggerBoost();

    public float getUnarmedDamage();

    public float getMaxMana();

    public float getArrowDamage();
    
    public float getSneakAttackDamage();
}
