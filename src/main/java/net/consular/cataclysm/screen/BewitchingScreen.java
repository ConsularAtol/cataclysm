package net.consular.cataclysm.screen;


import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.item.ScrollItem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BewitchingScreen extends HandledScreen<BewitchingScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(Cataclysm.MODID, "textures/gui/bewitching_table_gui.png");
        
    private final PlayerEntity player;

    public BewitchingScreen(BewitchingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.player = inventory.player;
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = this.x;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        super.drawForeground(context, mouseX, mouseY);
        int i = 0;
        if (this.handler.getSlot(1).getStack().getItem() instanceof ScrollItem ){
            ScrollItem scroll = (ScrollItem)this.handler.getSlot(1).getStack().getItem();
            i = scroll.getSpell().getManaCost();
        }
        if (i > 0) {
            Text text;
            int j = 8453920;
            if (!((BewitchingScreenHandler)this.handler).getSlot(2).hasStack()) {
                text = null;
            } else {
                text = Text.translatable("container.bewitching.cost", i);
                if (!((BewitchingScreenHandler)this.handler).canTakeOutput(player, true)) {
                    j = 0xFF6060;
                }
            }
            if (text != null) {
                int k = this.backgroundWidth - 8 - this.textRenderer.getWidth(text) - 2;
                context.fill(k - 2, 67, this.backgroundWidth - 8, 79, 0x4F000000);
                context.drawTextWithShadow(this.textRenderer, text, k, 69, j);
            }
        }
    }
}
