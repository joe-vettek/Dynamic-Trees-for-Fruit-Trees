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
import com.ferreusveritas.dynamictrees.systems.nodemappers.FindEndsNode;
import com.ferreusveritas.dynamictrees.util.BlockStates;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import com.ferreusveritas.dynamictrees.worldgen.deserialisation.JsonMath;
import net.minecraft.block.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;
import snownee.fruits.cherry.CherryModule;
import xueluoanping.dtfruittrees.DTFruitTrees;

import java.util.List;
import java.util.Random;

public class FeatureGenFallenLeaves extends GenFeature {

    public static final ConfigurationProperty<String> CARPET = ConfigurationProperty.property("carpet", String.class);

    public BlockState getBasicLeafBlock(GenFeatureConfiguration configuration) {
        // return CherryModule.CHERRY_CARPET.defaultBlockState();
// DTFruitTrees.LOGGER.debug(configuration.getString());
       return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(configuration.get(CARPET))).defaultBlockState();
    }

    public FeatureGenFallenLeaves(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(CARPET);
    }
    @Override
    protected GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(CARPET, "air");
    }

    @Override
    protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
        final IWorld world = context.world();
        final FindEndsNode endFinder = new FindEndsNode();
        TreeHelper.startAnalysisFromRoot(world, context.pos(), new MapSignal(endFinder));
        final List<BlockPos> endPoints = endFinder.getEnds();

        if (endPoints.isEmpty()) {
            return false;
        }


        final Random random = context.random();
        endPoints.forEach(pos -> {
            int x = pos.getX() + random.nextInt(5) - 2;
            int z = pos.getZ() + random.nextInt(5) - 2;

            final int darkThreshold = 4;

            for (int i = 0; i < 32; i++) {

                BlockPos offPos = new BlockPos(x, pos.getY() - 1 - i, z);

                if (!world.isEmptyBlock(offPos)) {
                    Block block = world.getBlockState(offPos).getBlock();

                    if (block instanceof BranchBlock || block instanceof MushroomBlock || block instanceof LeavesBlock) {// Skip past Mushrooms and branches on the way down
                        continue;
                    }
                    else if (block != Blocks.AIR) {// Convert grass or dirt to podzol
                        testAir(world, offPos,configuration);
                        testAir(world, offPos.east(1),configuration);
                        testAir(world, offPos.east(1).north(1),configuration);
                        testAir(world, offPos.east(1).south(1),configuration);
                        testAir(world, offPos.west(1),configuration);
                        testAir(world, offPos.west(1).north(1),configuration);
                        testAir(world, offPos.west(1).south(1),configuration);
                        testAir(world, offPos.south(1),configuration);
                        testAir(world, offPos.north(1),configuration);
                    }
                    break;
                }
            }
        });
        // final BlockPos pos = endPoints.get(random.nextInt(endPoints.size()));



        return true;
    }

    @Override
    protected boolean postGrow(GenFeatureConfiguration configuration, PostGrowContext context) {


        final IWorld world = context.world();
        final FindEndsNode endFinder = new FindEndsNode();
        TreeHelper.startAnalysisFromRoot(world, context.pos(), new MapSignal(endFinder));
        final List<BlockPos> endPoints = endFinder.getEnds();

        if (endPoints.isEmpty()) {
            return false;
        }


        final Random random = context.random();
        final BlockPos pos = endPoints.get(random.nextInt(endPoints.size()));


        int x = pos.getX() + random.nextInt(5) - 2;
        int z = pos.getZ() + random.nextInt(5) - 2;

        final int darkThreshold = 4;

        for (int i = 0; i < 32; i++) {

            BlockPos offPos = new BlockPos(x, pos.getY() - 1 - i, z);

            if (!world.isEmptyBlock(offPos)) {
                Block block = world.getBlockState(offPos).getBlock();

                if (block instanceof BranchBlock || block instanceof MushroomBlock || block instanceof LeavesBlock) {// Skip past Mushrooms and branches on the way down
                    continue;
                }
                // else if (block instanceof FlowerBlock || block instanceof TallGrassBlock || block instanceof DoublePlantBlock) {//Kill Plants
                //     if (world.getLightEmission(offPos) <= darkThreshold) {
                //         world.setBlock(pos,BlockStates.AIR,Constants.BlockFlags.BLOCK_UPDATE|Constants.BlockFlags.UPDATE_NEIGHBORS);
                //     }
                //     continue;
                // }
                else if (block != Blocks.AIR) {// Convert grass or dirt to podzol
                    testAir(world, offPos,configuration);
                    testAir(world, offPos.east(1),configuration);
                    testAir(world, offPos.east(1).north(1),configuration);
                    testAir(world, offPos.east(1).south(1),configuration);
                    testAir(world, offPos.west(1),configuration);
                    testAir(world, offPos.west(1).north(1),configuration);
                    testAir(world, offPos.west(1).south(1),configuration);
                    testAir(world, offPos.south(1),configuration);
                    testAir(world, offPos.north(1),configuration);
                }
                break;
            }
        }
        return true;
    }

    private void testAir(IWorld world, BlockPos pos,GenFeatureConfiguration configuration) {
        if (world.getBlockState(pos).getBlock() != Blocks.AIR) {
            pos = pos.above(1);
            if (world.getBlockState(pos).getBlock() instanceof BranchBlock
                    || world.getBlockState(pos.below(1)).getBlock() instanceof BranchBlock) {
            } else {
                if (world.getBlockState(pos).getBlock() == Blocks.AIR)
                    if (world.getBlockState(pos.below(1)).isCollisionShapeFullBlock(world, pos.below(1))) {
                        // random generate , maybe not
                        if (world.getRandom().nextInt(5) < 3)
                            world.setBlock(pos, getBasicLeafBlock(configuration), Constants.BlockFlags.BLOCK_UPDATE | Constants.BlockFlags.UPDATE_NEIGHBORS);
                    }
            }
        }

    }


}
