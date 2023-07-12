package net.consular.cataclysm.entity.render;

import net.consular.cataclysm.entity.MagicSwordEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class MagicSwordEntityRenderer extends EntityRenderer<MagicSwordEntity> {
	public MagicSwordEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(MagicSwordEntity entity) {
		return null;
	}
}
