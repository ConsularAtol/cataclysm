package net.consular.cataclysm.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.compat.rei.BewitchingTable.BewitchingTableCategory;
import net.consular.cataclysm.compat.rei.BewitchingTable.BewitchingTableDisplay;
import net.consular.cataclysm.compat.rei.FletchingTable.FletchingTableCategory;
import net.consular.cataclysm.compat.rei.FletchingTable.FletchingTableDisplay;
import net.consular.cataclysm.recipe.BewitchingRecipe;
import net.consular.cataclysm.recipe.FletchingRecipe;
import net.consular.cataclysm.registry.ModBlocks;
import net.consular.cataclysm.registry.ModRecipeTypes;
import net.consular.cataclysm.screen.BewitchingScreen;
import net.consular.cataclysm.screen.FletchingScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;

@Environment(EnvType.CLIENT)
public class CataclysmREI implements REIClientPlugin {

    public static final CategoryIdentifier<FletchingTableDisplay> FLETCHING = CategoryIdentifier.of(Cataclysm.MODID, "fletching");
    public static final CategoryIdentifier<BewitchingTableDisplay> BEWITCHING = CategoryIdentifier.of(Cataclysm.MODID, "bewitching");

    public static Rectangle centeredIntoRecipeBase(Point origin, int width, int height) {
        return centeredInto(new Rectangle(origin.x, origin.y, 150, 68), width, height);
    }

    public static Rectangle centeredInto(Rectangle origin, int width, int height) {
        return new Rectangle(origin.x + (origin.width - width) / 2, origin.y + (origin.height - height) / 2, width, height);
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(
                new FletchingTableCategory());
        registry.addWorkstations(FLETCHING, EntryStacks.of(Blocks.FLETCHING_TABLE));
        registry.add(
                new BewitchingTableCategory());
        registry.addWorkstations(BEWITCHING, EntryStacks.of(ModBlocks.BEWITCHING_TABLE));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(FletchingRecipe.class, ModRecipeTypes.FLETCHING, FletchingTableDisplay::new);
        registry.registerRecipeFiller(BewitchingRecipe.class, ModRecipeTypes.BEWITCHING, BewitchingTableDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(new Rectangle(89, 25, 24, 17), FletchingScreen.class, FLETCHING);
        registry.registerContainerClickArea(new Rectangle(89, 25, 24, 17), BewitchingScreen.class, BEWITCHING);
    }

}
