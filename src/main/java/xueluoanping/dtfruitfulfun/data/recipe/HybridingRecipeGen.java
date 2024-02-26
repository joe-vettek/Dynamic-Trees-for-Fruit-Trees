package xueluoanping.dtfruitfulfun.data.recipe;

import java.util.Arrays;
import java.util.function.Consumer;


import com.ferreusveritas.dynamictrees.DynamicTrees;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import snownee.kiwi.recipe.ModuleLoadedCondition;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;
import xueluoanping.dtfruitfulfun.data.recipe.builder.HybridingRecipeBuilder;
import xueluoanping.dtfruitfulfun.util.RegisterFinderUtil;


public class HybridingRecipeGen {
    public static void register(Consumer<FinishedRecipe> consumer) {

        Block CHERRY = RegisterFinderUtil.getBlock(DTFruitfulFun.rl("cherry").toString());
        Block MANDARIN = RegisterFinderUtil.getBlock(DTFruitfulFun.rl("mandarin").toString());
        Block LIME = RegisterFinderUtil.getBlock(DTFruitfulFun.rl("lime").toString());
        Block CITRON = RegisterFinderUtil.getBlock(DTFruitfulFun.rl("citron").toString());
        Block POMELO = RegisterFinderUtil.getBlock(DTFruitfulFun.rl("pomelo").toString());
        Block ORANGE = RegisterFinderUtil.getBlock(DTFruitfulFun.rl("orange").toString());
        Block LEMON = RegisterFinderUtil.getBlock(DTFruitfulFun.rl("lemon").toString());
        Block GRAPEFRUIT = RegisterFinderUtil.getBlock(DTFruitfulFun.rl("grapefruit").toString());
        Block REDLOVE = RegisterFinderUtil.getBlock(DTFruitfulFun.rl("redlove").toString());
        Block APPLE = RegisterFinderUtil.getBlock(DynamicTrees.location("apple"));

        HybridingRecipeBuilder
                .create(Arrays.asList(POMELO,LEMON, ORANGE),GRAPEFRUIT)
                .build(consumer);
        HybridingRecipeBuilder
                .create(Arrays.asList(LIME,CITRON),LEMON)
                .build(consumer);
        HybridingRecipeBuilder
                .create(Arrays.asList(LIME,MANDARIN),ORANGE)
                .build(consumer);
        HybridingRecipeBuilder
                .create(Arrays.asList(MANDARIN,CITRON),POMELO)
                .build(consumer);
        HybridingRecipeBuilder
                .create(Arrays.asList(APPLE,CHERRY, Blocks.WITHER_ROSE),REDLOVE)
                .addCondition(new ModuleLoadedCondition(new ResourceLocation("fruittrees:cherry")))
                .build(consumer);
    }

}
