package net.consular.cataclysm.compat.rei.BewitchingTable;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.WidgetWithBounds;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.consular.cataclysm.compat.rei.CataclysmREI;
import net.consular.cataclysm.item.ScrollItem;
import net.consular.cataclysm.registry.ModBlocks;
import net.consular.cataclysm.util.TextWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import com.google.common.collect.Lists;

@Environment(EnvType.CLIENT)
public class BewitchingTableCategory implements DisplayCategory<BewitchingTableDisplay> {

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.BEWITCHING_TABLE);
    }

    @Override
    public Text getTitle() {
        return Text.translatable("container.bewitching");
    }

    @Override
    public CategoryIdentifier<? extends BewitchingTableDisplay> getCategoryIdentifier() {
        return CataclysmREI.BEWITCHING;
    }

    @Override
    public List<Widget> setupDisplay(BewitchingTableDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 31, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        Point origin = bounds.getLocation();
        Rectangle bgBounds = CataclysmREI.centeredIntoRecipeBase(origin, 120, 59);
        Point slotLoc = new Point(bgBounds.x + 110, bgBounds.y + 35);
        ScrollItem scroll = (ScrollItem)display.getRecipe().getAddition().getItem();
        int cost = scroll.getSpell().getManaCost();
        WidgetWithBounds text = new TextWidget(slotLoc, Text.translatable("container.bewitching.cost", cost).formatted(Formatting.GREEN));
        widgets.add(Widgets.createRecipeBase(bounds));
        int offsetX = 5;
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 27 + offsetX, startPoint.y + 4)));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61 + offsetX, startPoint.y + 5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 4 - 22 + offsetX, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 4 + offsetX, startPoint.y + 5)).entries(display.getInputEntries().get(1)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 61 + offsetX, startPoint.y + 5)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        widgets.add(text);

        return widgets;
    }
}
