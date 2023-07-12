package net.consular.cataclysm.entity.render;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.entity.HornetEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HornetRenderer extends GeoEntityRenderer<HornetEntity> {
    public HornetRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new Identifier(Cataclysm.MODID, "hornet")));

		this.shadowRadius = 0.25f;
    }
}
