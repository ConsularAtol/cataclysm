package net.consular.cataclysm.compat.rei.BewitchingTable;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.consular.cataclysm.compat.rei.CataclysmREI;
import net.consular.cataclysm.recipe.BewitchingRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class BewitchingTableDisplay extends BasicDisplay {
    private BewitchingRecipe recipe;

    public BewitchingTableDisplay(BewitchingRecipe recipe) {
        this(
                recipe,
                List.of(
                        EntryIngredients.ofIngredient(recipe.base),
                        EntryIngredients.ofIngredient(recipe.addition)
                )
        );
    }
    
    public BewitchingTableDisplay(BewitchingRecipe recipe, List<EntryIngredient> inputs) {
        this(
                inputs,
                List.of(EntryIngredients.of(recipe.getResultItem(BasicDisplay.registryAccess()))),
                Optional.ofNullable(recipe.getId())
        );
        this.recipe = recipe;
    }
    
    public BewitchingTableDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location) {
        super(inputs, outputs, location);
    }
    
    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CataclysmREI.BEWITCHING;
    }
    
    public static BasicDisplay.Serializer<BewitchingTableDisplay> serializer() {
        return BasicDisplay.Serializer.ofSimple(BewitchingTableDisplay::new);
    }

    public BewitchingRecipe getRecipe() {
        return recipe;
    }
}