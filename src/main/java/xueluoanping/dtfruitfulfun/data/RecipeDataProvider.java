package xueluoanping.dtfruitfulfun.data;

import java.util.function.Consumer;


import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import xueluoanping.dtfruitfulfun.data.recipe.HybridingRecipeGen;


public class RecipeDataProvider extends RecipeProvider {
	public RecipeDataProvider(PackOutput generator) {
		super(generator);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		HybridingRecipeGen.register(consumer);
	}

}
