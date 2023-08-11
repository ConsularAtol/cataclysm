package net.consular.cataclysm.util;

import java.util.Collections;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.WidgetWithBounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class TextWidget extends WidgetWithBounds {
    private static final int SLOT_WIDTH = 18;
    private static final int SLOT_HEIGHT = 18;

    private final Point position;
    private final Text text;
    public TextWidget(Point position, Text text) {
        this.position = position;
        this.text = text;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        Text textLabel = text;
        int labelWidth = textRenderer.getWidth(textLabel);
        int labelX = position.x - 1 + SLOT_WIDTH - labelWidth;
        int labelY = position.y + 2 + SLOT_HEIGHT - textRenderer.fontHeight - 2;
        MatrixStack matrices = context.getMatrices();
        
        matrices.push();
        matrices.translate(0, 0, 200); // Increase the z-coordinate to bring the label forward
        
        RenderSystem.disableDepthTest(); // Disable depth testing to ensure the label is always rendered on top
        context.drawTextWithShadow(textRenderer, textLabel, labelX, labelY, 16777215);
        RenderSystem.enableDepthTest();
        
        matrices.pop();
    }
    
    @Override
    public List<Widget> children() {
        return Collections.emptyList();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, SLOT_WIDTH, SLOT_HEIGHT);
    }
}