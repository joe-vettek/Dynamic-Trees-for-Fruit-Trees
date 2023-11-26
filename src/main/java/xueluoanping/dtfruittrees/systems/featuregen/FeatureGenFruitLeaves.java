package xueluoanping.dtfruittrees.systems.featuregen;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.configurations.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.api.network.MapSignal;
import com.ferreusveritas.dynamictrees.blocks.branches.BranchBlock;
import com.ferreusveritas.dynamictrees.blocks.rootyblocks.RootyBlock;
import com.ferreusveritas.dynamictrees.init.DTConfigs;
import com.ferreusveritas.dynamictrees.systems.genfeatures.GenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeatures.GenFeatureConfiguration;
import com.ferreusveritas.dynamictrees.systems.genfeatures.context.PostGenerationContext;
import com.ferreusveritas.dynamictrees.systems.genfeatures.context.PostGrowContext;
import com.ferreusveritas.dynamictrees.systems.nodemappers.DestroyerNode;
import com.ferreusveritas.dynamictrees.systems.nodemappers.FindEndsNode;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;
import xueluoanping.dtfruittrees.DTFruitTrees;
import xueluoanping.dtfruittrees.systems.leaves.DynamicCherryLeavesBlock;
import xueluoanping.dtfruittrees.systems.leaves.DynamicFruitLeavesBlock;
import xueluoanping.dtfruittrees.systems.nodemappers.ALLAboveNode;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FeatureGenFruitLeaves extends GenFeature {
    public static final ConfigurationProperty<String> FRUIT_ITEM = ConfigurationProperty.property("fruit_item", String.class);

    public FeatureGenFruitLeaves(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(FRUIT_ITEM);
    }

    @Override
    protected GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(FRUIT_ITEM, "air");
    }

    @Override
    protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
        final Species species = context.species();
        final FindEndsNode destroyer = new FindEndsNode();
        final IWorld world = context.world();
        TreeHelper.startAnalysisFromRoot(world, context.pos(), new MapSignal(destroyer));
        // final List<BlockPos> leavesPoints;
        destroyer.getEnds().forEach(pos -> {
            int[][] spreadList=new int[][]{{45,60},{75,80},{60,45}};
            Arrays.stream(spreadList).collect(Collectors.toList()).forEach(ints -> {
                 RayTraceResult result = CoordUtils.branchRayTrace(world, context.species(), context.pos(), pos, ints[0],ints[1], 3 + world.getRandom().nextInt(5), SafeChunkBounds.ANY);
                if (result != null) {
                    BlockPos endPos = new BlockPos(result.getLocation());
                    BlockState blockState = world.getBlockState(endPos);
                    if (species.getFamily().isCompatibleGenericLeaves(species, blockState, world, endPos)) {
                        if (blockState.hasProperty(DynamicFruitLeavesBlock.AGE) && blockState.getValue(DynamicFruitLeavesBlock.AGE) ==0)
                            world.setBlock(endPos, blockState.setValue(DynamicFruitLeavesBlock.AGE, 2), Constants.BlockFlags.BLOCK_UPDATE | Constants.BlockFlags.UPDATE_NEIGHBORS);
                        // DTFruitTrees.LOGGER.debug("ssda,+" +world.getBlockState(endPos));
                    }
                }
            });

            // DTFruitTrees.LOGGER.debug("ssda,+" + world.getBlockState(pos));
        });

        return true;
    }

    @Override
    protected boolean postGrow(GenFeatureConfiguration configuration, PostGrowContext context) {
        // No Fertility, No Fruit
        if (context.world().getBlockState(context.pos()).getValue(RootyBlock.FERTILITY) < 2) {
            return false;
        }

        final IWorld world = context.world();
        final FindEndsNode endFinder = new FindEndsNode();
        TreeHelper.startAnalysisFromRoot(world, context.pos(), new MapSignal(endFinder));
        final List<BlockPos> endPoints = endFinder.getEnds();

        if (endPoints.isEmpty()) {
            return false;
        }


        endPoints.forEach(pos -> {
            final RayTraceResult result = CoordUtils.branchRayTrace(world, context.species(), context.pos(), pos, 45, 60, 3 + world.getRandom().nextInt(5), SafeChunkBounds.ANY);
            final Species species = context.species();
            if (result != null) {
                BlockPos endPos = new BlockPos(result.getLocation());
                BlockState blockState = world.getBlockState(endPos);
                if (species.getFamily().isCompatibleGenericLeaves(species, blockState, world, endPos)) {
                    if (blockState.hasProperty(DynamicFruitLeavesBlock.AGE) && blockState.getValue(DynamicFruitLeavesBlock.AGE) < 2)
                        world.setBlock(endPos, blockState.setValue(DynamicFruitLeavesBlock.AGE, 2), Constants.BlockFlags.BLOCK_UPDATE | Constants.BlockFlags.UPDATE_NEIGHBORS);
                    // DTFruitTrees.LOGGER.debug(world.getBlockState(endPos));
                }
            }
        });

        return true;
    }

    public Item getItemFromRegistry(String itemName) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));
    }

}
