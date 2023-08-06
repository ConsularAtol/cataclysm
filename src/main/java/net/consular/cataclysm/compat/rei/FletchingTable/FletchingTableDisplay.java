package net.consular.cataclysm.compat.rei.FletchingTable;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.consular.cataclysm.compat.rei.CataclysmREI;
import net.consular.cataclysm.recipe.FletchingRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class FletchingTableDisplay extends BasicDisplay {

    public FletchingTableDisplay(FletchingRecipe recipe) {
        this(
                recipe,
                List.of(
                        EntryIngredients.ofIngredient(recipe.base),
                        EntryIngredients.ofIngredient(recipe.addition)
                )
        );
    }
    
    public FletchingTableDisplay(FletchingRecipe recipe, List<EntryIngredient> inputs) {
        this(
                inputs,
                List.of(EntryIngredients.of(recipe.getResultItem(BasicDisplay.registryAccess()))),
                Optional.ofNullable(recipe.getId())
        );
    }
    
    public FletchingTableDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location) {
        super(inputs, outputs, location);
    }
    
    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CataclysmREI.FLETCHING;
    }
    
    public static BasicDisplay.Serializer<FletchingTableDisplay> serializer() {
        return BasicDisplay.Serializer.ofSimple(FletchingTableDisplay::new);
    }
}