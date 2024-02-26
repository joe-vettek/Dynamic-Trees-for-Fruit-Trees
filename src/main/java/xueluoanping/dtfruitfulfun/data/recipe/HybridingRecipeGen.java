package xueluoanping.dtfruitfulfun.data.recipe;

import java.util.Arrays;
import java.util.function.Consumer;


import com.ferreusveritas.dynamictrees.DynamicTrees;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import snownee.fruits.FruitfulFun;
import snownee.kiwi.recipe.ModuleLoadedCondition;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;
import xueluoanping.dtfruitfulfun.data.recipe.builder.HybridingRecipeBuilder;
import xueluoanping.dtfruitfulfun.util.RegisterFinderUtil;
import static xueluoanping.dtfruitfulfun.ModConstants.*;


public class HybridingRecipeGen {
    public static void register(Consumer<FinishedRecipe> consumer) {


        HybridingRecipeBuilder
                .create(Arrays.asList(POMELO.get(),LEMON.get(), ORANGE.get()),GRAPEFRUIT.get())
                .build(consumer);
        HybridingRecipeBuilder
                .create(Arrays.asList(LIME.get(),CITRON.get()),LEMON.get())
                .build(consumer);
        HybridingRecipeBuilder
                .create(Arrays.asList(LIME.get(),TANGERINE.get()),ORANGE.get())
                .build(consumer);
        HybridingRecipeBuilder
                .create(Arrays.asList(TANGERINE.get(),CITRON.get()),POMELO.get())
                .build(consumer);
        HybridingRecipeBuilder
                .create(Arrays.asList(APPLE.get(),CHERRY.get(), Blocks.WITHER_ROSE),REDLOVE.get())
                .addCondition(new ModuleLoadedCondition(new ResourceLocation(FruitfulFun.ID,"bee")))
                .build(consumer);
    }

}
