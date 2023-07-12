package net.consular.cataclysm.util;

import com.mojang.blaze3d.systems.RenderSystem;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.registry.ModEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class EventHandler {
    private static final Identifier HUD_ELEMENTS = new Identifier(Cataclysm.MODID, "textures/gui/hud_elements.png");

    @Environment(EnvType.CLIENT)
	public static void clientEvents() {
		final MinecraftClient client = MinecraftClient.getInstance();
		var manaTimer = new Object() {
			int value;
		};

		//-----HUD Render Callback-----//
		 HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            if (client.cameraEntity instanceof PlayerEntity player && !player.isSpectator() && !player.isCreative()) {
                MagicUser user = (MagicUser) player;
                int mana = Math.min(user.getMana(), user.getMaxMana());
                int manaLock = MagicHelper.getManaLock(player);

                if (mana < user.getMaxMana() || player.hasStatusEffect(ModEffects.DRAINED))
                    manaTimer.value = Math.min(manaTimer.value + 1, 40);
                else
                    manaTimer.value = Math.max(manaTimer.value - 1, 0);

                if (manaTimer.value > 0) {
                    user.shouldShowMana(true);
                    int scaledWidth = client.getWindow().getScaledWidth();
                    int scaledHeight = client.getWindow().getScaledHeight();
                    int x = scaledWidth / 2 + 82;
                    int y = scaledHeight - (player.isCreative() ? 34 : 49);
                    float alpha = manaTimer.value > 20 ? 1F : manaTimer.value / 20F;

                    RenderSystem.enableBlend();
                    RenderSystem.setShaderTexture(0, HUD_ELEMENTS);
                    RenderSystem.setShaderColor(1F, 1F, 1F, alpha);

                    DrawContext context = new DrawContext(client, client.getBufferBuilders().getEntityVertexConsumers());

                    for (int i = 0; i < 10; i++)
                        context.drawTexture(HUD_ELEMENTS, x - (i * 8), y, 0, 15, 9, 9);

                    for (int i = 0; i < mana / 2; i++)
                        context.drawTexture(HUD_ELEMENTS, x - (i * 8), y, 0, 0, 8, 8);

                    if (mana % 2 == 1)
                        context.drawTexture(HUD_ELEMENTS, x - ((mana / 2) * 8), y, 8, 0, 8, 8);

                    if (player.hasStatusEffect(ModEffects.DRAINED)){
                        for (int i = 0; i < mana / 2; i++)
                            context.drawTexture(HUD_ELEMENTS, x - (i * 8), y, 16, 0, 8, 8);
                    }

                    if (mana % 2 == 1 && player.hasStatusEffect(ModEffects.DRAINED))
                        context.drawTexture(HUD_ELEMENTS, x - ((mana / 2) * 8), y, 24, 0, 8, 8);

                    int adjustedManaLock = (manaLock / 2) * 8;
                    x -= 72;

                    for (int i = 0; i < manaLock / 2; i++)
                        context.drawTexture(HUD_ELEMENTS, x + (i * 8), y, 40, 0, 8, 8);

                    if (manaLock % 2 == 1)
                        context.drawTexture(HUD_ELEMENTS, x + adjustedManaLock, y, 48, 0, 8, 8);
                } else
                    user.shouldShowMana(false);
            }
        });
	}

}
