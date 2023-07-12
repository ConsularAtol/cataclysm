package net.consular.cataclysm.util;

import net.consular.cataclysm.registry.ModItems;
import net.minecraft.item.ItemStack;

public enum ArrowType {
    IRON,
    DIAMOND,
    NETHERITE,
    ENDERITE,
    SCULK;

    public static double getDamage(ArrowType type){
        switch(type){
            case IRON:
                return 3.0;
            case DIAMOND:
                return 4.0;
            case NETHERITE:
                return 5.0;
            case ENDERITE:
                return 6.0;
            case SCULK:
                return 7.0;
            default:
                return 0;
        }
    }

    public static ItemStack getStack(ArrowType type){
        switch(type){
            case IRON:
                return new ItemStack(ModItems.IRON_ARROW);
            case DIAMOND:
                return new ItemStack(ModItems.DIAMOND_ARROW);
            case NETHERITE:
                return new ItemStack(ModItems.NETHERITE_ARROW);
            case ENDERITE:
                return new ItemStack(ModItems.ENDERITE_ARROW);
            case SCULK:
                return new ItemStack(ModItems.SCULK_ARROW);
            default:
                return null;
        }
    }

    public static ItemStack getTippedStack(ArrowType type){
        switch(type){
            case IRON:
                return new ItemStack(ModItems.TIPPED_IRON_ARROW);
            case DIAMOND:
                return new ItemStack(ModItems.TIPPED_DIAMOND_ARROW);
            case NETHERITE:
                return new ItemStack(ModItems.TIPPED_NETHERITE_ARROW);
            case ENDERITE:
                return new ItemStack(ModItems.TIPPED_ENDERITE_ARROW);
            case SCULK:
                return new ItemStack(ModItems.TIPPED_SCULK_ARROW);
            default:
                return null;
        }
    }

    public static ArrowType getTypeFromStack(ItemStack itemStack){
        if (itemStack.isOf(ModItems.IRON_ARROW))
            return IRON;
        if (itemStack.isOf(ModItems.DIAMOND_ARROW))
            return DIAMOND;
        if (itemStack.isOf(ModItems.NETHERITE_ARROW))
            return NETHERITE;
        if (itemStack.isOf(ModItems.ENDERITE_ARROW))
            return ENDERITE;
        if (itemStack.isOf(ModItems.SCULK_ARROW))
            return SCULK;
        return IRON;
    }

    public static ArrowType getTypeFromString(String string){
        switch(string){
            case "IRON":
                return IRON;
            case "DIAMOND":
                return DIAMOND;
            case "NETHERITE":
                return NETHERITE;
            case "ENDERITE":
                return ENDERITE;
            case "SCULK":
                return SCULK;
            default:
                return IRON;
        }
    }
}
