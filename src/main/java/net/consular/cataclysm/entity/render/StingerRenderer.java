package net.consular.cataclysm.entity.render;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.entity.StingerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class StingerRenderer extends ProjectileEntityRenderer<StingerEntity> {
    public static final Identifier TEXTURE = new Identifier(Cataclysm.MODID, "textures/entity/projectiles/stinger.png");

    public StingerRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(StingerEntity arrowEntity) {
        return TEXTURE;
    }
}