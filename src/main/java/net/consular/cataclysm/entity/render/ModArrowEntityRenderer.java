package net.consular.cataclysm.entity.render;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.entity.ModArrowEntity;
import net.consular.cataclysm.util.ArrowType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(value=EnvType.CLIENT)
public class ModArrowEntityRenderer
extends ProjectileEntityRenderer<ModArrowEntity> {

    public static final Identifier IRON_TEXTURE = new Identifier(Cataclysm.MODID, "textures/entity/projectiles/iron_arrow.png");
    public static final Identifier DIAMOND_TEXTURE = new Identifier(Cataclysm.MODID, "textures/entity/projectiles/diamond_arrow.png");
    public static final Identifier NETHERITE_TEXTURE = new Identifier(Cataclysm.MODID, "textures/entity/projectiles/netherite_arrow.png");
    public static final Identifier ENDERITE_TEXTURE = new Identifier(Cataclysm.MODID, "textures/entity/projectiles/enderite_arrow.png");
    public static final Identifier SCULK_TEXTURE = new Identifier(Cataclysm.MODID, "textures/entity/projectiles/sculk_arrow.png");

    public ModArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ModArrowEntity arrowEntity) {
        switch(ArrowType.getTypeFromString(arrowEntity.getArrowType())){
            case IRON:
                return IRON_TEXTURE;
            case DIAMOND:
                return DIAMOND_TEXTURE;
            case NETHERITE:
                return NETHERITE_TEXTURE;
            case ENDERITE:
                return ENDERITE_TEXTURE;
            case SCULK:
                return SCULK_TEXTURE;
            default:
                return null;
        }
    }
}
