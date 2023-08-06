package net.consular.cataclysm.compat.rei.FletchingTable;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.consular.cataclysm.compat.rei.CataclysmREI;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;

import java.util.List;

import com.google.common.collect.Lists;

@Environment(EnvType.CLIENT)
public class FletchingTableCategory implements DisplayCategory<FletchingTableDisplay> {

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(Blocks.FLETCHING_TABLE);
    }

    @Override
    public Text getTitle() {
        return Text.translatable("container.fletching");
    }

    @Override
    public CategoryIdentifier<? extends FletchingTableDisplay> getCategoryIdentifier() {
        return CataclysmREI.FLETCHING;
    }

    @Override
    public List<Widget> setupDisplay(FletchingTableDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 31, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        int offsetX = 5;
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 27 + offsetX, startPoint.y + 4)));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61 + offsetX, startPoint.y + 5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 4 - 22 + offsetX, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 4 + offsetX, startPoint.y + 5)).entries(display.getInputEntries().get(1)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 61 + offsetX, startPoint.y + 5)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        return widgets;
    }
}
