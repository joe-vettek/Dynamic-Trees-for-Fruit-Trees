package xueluoanping.dtfruittrees.data.recipe;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.function.Consumer;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.ResourceLocation;
import snownee.kiwi.crafting.ModuleLoadedCondition;
import xueluoanping.dtfruittrees.data.recipe.builder.HybridingRecipeBuilder;
import xueluoanping.dtfruittrees.util.RegisterFinderUtil;


public class HybridingRecipeGen {
    public static void register(Consumer<IFinishedRecipe> consumer) {

        Block CHERRY = RegisterFinderUtil.getBlock("dtfruittrees:cherry_leaves");
        Block MANDARIN = RegisterFinderUtil.getBlock("dtfruittrees:mandarin_leaves");
        Block LIME = RegisterFinderUtil.getBlock("dtfruittrees:lime_leaves");
        Block CITRON = RegisterFinderUtil.getBlock("dtfruittrees:citron_leaves");
        Block POMELO = RegisterFinderUtil.getBlock("dtfruittrees:pomelo_leaves");
        Block ORANGE = RegisterFinderUtil.getBlock("dtfruittrees:orange_leaves");
        Block LEMON = RegisterFinderUtil.getBlock("dtfruittrees:lemon_leaves");
        Block GRAPEFRUIT = RegisterFinderUtil.getBlock("dtfruittrees:grapefruit_leaves");
        Block REDLOVE = RegisterFinderUtil.getBlock("dtfruittrees:redlove_leaves");
        Block APPLE = RegisterFinderUtil.getBlock("dtfruittrees:oak_leaves");

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
