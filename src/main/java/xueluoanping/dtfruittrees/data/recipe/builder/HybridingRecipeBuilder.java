package xueluoanping.dtfruittrees.data.recipe.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

import snownee.fruits.hybridization.Hybridization;

import snownee.kiwi.recipe.ModuleLoadedCondition;
import xueluoanping.dtfruittrees.DTFruitTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HybridingRecipeBuilder {
    private final List<Block> ingredients;
    private final Block result;
    private List<ICondition> currentConditions = new ArrayList<>();

    public HybridingRecipeBuilder(List<Block> ingredients, Block result) {
        this.ingredients = ingredients;
        this.result = result;
        currentConditions.add(new ModuleLoadedCondition(new ResourceLocation("fruittrees:hybridization")));
    }

    public static HybridingRecipeBuilder create(List<Block> ingredients, Block result) {
        return new HybridingRecipeBuilder(ingredients, result);
    }

    public void build(Consumer<FinishedRecipe> consumerIn) {
        consumerIn.accept(new Result(new ResourceLocation(DTFruitTrees.MOD_ID,"hybriding/"+ this.result.getRegistryName().getPath()), this.currentConditions, this.ingredients, this.result));
    }

    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        consumerIn.accept(new Result(id, this.currentConditions, this.ingredients, this.result));
    }

    public HybridingRecipeBuilder addCondition(ICondition condition) {
        currentConditions.add(condition);
        return this;
    }

    public static class Result implements FinishedRecipe {
        private final List<Block> ingredients;
        private final Block result;
        private final ResourceLocation id;
        private final List<ICondition> currentConditions;

        public Result(ResourceLocation id, List<ICondition> currentConditions, List<Block> ingredients, Block result) {
            this.ingredients = ingredients;
            this.result = result;
            this.id = id;
            this.currentConditions = currentConditions;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray conds = new JsonArray();
            for (ICondition c : currentConditions) {
                if (!(c instanceof ModuleLoadedCondition))
                    conds.add(CraftingHelper.serialize(c));
                else {
                    JsonObject temp = new JsonObject();
                    temp.addProperty("type", c.getID().toString());
                   ModuleLoadedCondition.Serializer.INSTANCE.write(temp, (ModuleLoadedCondition) c);
                    conds.add(temp);
                }
            }
            json.add("conditions", conds);

            JsonArray arrayIngredients = new JsonArray();


            this.ingredients.forEach(block -> {
                JsonObject fluid = new JsonObject();
                fluid.addProperty("block", block.getRegistryName().toString());
                arrayIngredients.add(fluid);
            });
            json.add("ingredients", arrayIngredients);

            // JsonObject arrayFluids = new JsonObject();
            JsonObject fluid = new JsonObject();
            fluid.addProperty("block", this.result.getRegistryName().toString());
            // arrayFluids.add(fluid);
            json.add("result", fluid);
        }

        @Override
        public JsonObject serializeRecipe() {
            return FinishedRecipe.super.serializeRecipe();
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return Hybridization.SERIALIZER;
        }


        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
