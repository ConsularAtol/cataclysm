package net.consular.cataclysm.entity.render;

import net.consular.cataclysm.entity.MagicProjectileEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class MagicProjectileEntityRenderer extends EntityRenderer<MagicProjectileEntity> {
	public MagicProjectileEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(MagicProjectileEntity entity) {
		return null;
	}
}
