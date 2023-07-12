package net.consular.cataclysm.item;

import net.consular.cataclysm.entity.ModArrowEntity;
import net.consular.cataclysm.util.ArrowType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModArrowItem extends ArrowItem{

    ArrowType arrowType;

    public ModArrowItem(Settings settings, ArrowType type) {
        super(settings);
        arrowType = type;
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        ModArrowEntity arrowEntity = new ModArrowEntity(world, shooter, arrowType);
        arrowEntity.initFromStack(stack);
        return arrowEntity;
    }
}
