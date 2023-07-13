package net.consular.cataclysm.item;

import net.consular.cataclysm.util.ModArmorMaterial;
import net.minecraft.item.DyeableItem;

public class DyeableModArmorItem extends ModArmorItem implements DyeableItem{

    public DyeableModArmorItem(ModArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }
    
}
