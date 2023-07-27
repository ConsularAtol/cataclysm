package net.consular.cataclysm.entity.render;

import net.consular.cataclysm.entity.WandOfSparkingEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class WandOfSparkingEntityRenderer extends EntityRenderer<WandOfSparkingEntity> {
	public WandOfSparkingEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(WandOfSparkingEntity entity) {
		return null;
	}
}
