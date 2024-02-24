package xueluoanping.dtfruittrees.data;

import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;


import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import xueluoanping.dtfruittrees.data.recipe.HybridingRecipeGen;


public class RecipeDataProvider extends RecipeProvider {
	public RecipeDataProvider(PackOutput generator) {
		super(generator);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		HybridingRecipeGen.register(consumer);
	}

}
