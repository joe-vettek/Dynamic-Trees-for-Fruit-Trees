package xueluoanping.dtfruittrees.systems.leaves;

import com.ferreusveritas.dynamictrees.blocks.FruitBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import snownee.fruits.FruitsConfig;
import snownee.fruits.block.FruitLeavesBlock;
import snownee.fruits.cherry.CherryModule;
import xueluoanping.dtfruittrees.DTFruitTrees;
import xueluoanping.dtfruittrees.systems.featuregen.FeatureGenFruitLeaves;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class DynamicFruitLeavesBlock extends DynamicLeavesBlock implements IGrowable {

    public static final IntegerProperty AGE = FruitLeavesBlock.AGE;

    public DynamicFruitLeavesBlock(LeavesProperties leavesProperties, Properties properties) {
        super(leavesProperties, properties);
    }


    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(AGE));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);


    }


    private boolean dropFruilt(World world, BlockPos pos, BlockState state) {
        if (state.is(this)) {
            if (state.getValue(AGE) == 3) {
                world.setBlockAndUpdate(pos, state.setValue(AGE, 0));
                AtomicReference<Item> item = new AtomicReference<>(Items.AIR);
                getProperties(state).getFamily().getCommonSpecies().getGenFeatures().forEach(genFeatureConfiguration -> {
                    if (genFeatureConfiguration.has(FeatureGenFruitLeaves.FRUIT_ITEM)) {
                        item.set(getItemFromRegistry(genFeatureConfiguration.get(FeatureGenFruitLeaves.FRUIT_ITEM)));
                    }
                });
                ItemStack stack = new ItemStack(item.get(), 1);
                ItemEntity dropped = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                world.addFreshEntity(dropped);
                return true;
            }
        }

        // DTFruitTrees.LOGGER.debug(getProperties(state).getFamily().getCommonSpecies().getGenFeatures().get(0));
        return false;
    }

    public Item getItemFromRegistry(String itemName) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));
    }


    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (player.getItemInHand(hand) == ItemStack.EMPTY) {
            dropFruilt(world, pos, state);
        }

        return super.use(state, world, pos, player, hand, blockRayTraceResult);
    }


    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        super.randomTick(state, world, pos, rand);
        int nowAge = state.getValue(AGE);
        // if (nowAge == 0)
        //     return;
        // if (nowAge != 3) {
        //     world.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
        //     // world.setBlockAndUpdate(pos, state.cycle(AGE));
        // } else {
        //     if (rand.nextInt(15) == 0) {
        //         dropFruilt(world, pos, state);
        //     }
        // }
        if (nowAge < 2) {
            if (rand.nextInt(10) == 0)
                if (!world.isEmptyBlock(pos.below())) {
                    // if (world.getBlockState(pos.below()).getBlock() instanceof FruitBlock)
                    //     nowAge = 2;
                    // else
                    nowAge++;
                    world.setBlockAndUpdate(pos, state.setValue(AGE, nowAge));
                }

        } else if (nowAge == 2 && !(world.getBlockState(pos.below()).getBlock() instanceof FruitBlock)) {
            world.setBlockAndUpdate(pos, state.setValue(AGE, 0));
        } else if (nowAge > 2) {
            world.setBlockAndUpdate(pos, state.setValue(AGE, 0));
        }

        // ItemStack stack = new ItemStack(CherryModule.CHERRY.asItem(), 1);
        // ItemEntity dropped = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        // world.addFreshEntity(dropped);
        // world.setBlockEntity();
        // DTFruitTrees.LOGGER.debug(world.getBlockState(pos));
        // DTFruitTrees.LOGGER.debug(world.getBlockState(TreeHelper.findRootNode(world,pos)));
    }


    public boolean canGrow(BlockState state) {
        return (Integer) state.getValue(AGE) > 0;
    }

    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        if (state.getValue(AGE) >= 2) {
            // if (!world.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
            //     return;
            // }
            // switch (FruitsConfig.getDropMode(world)) {
            //     case INDEPENDENT:
            //         break;
            //     case ONE_BY_ONE:
            //         break;
            // }
            // dropFruilt(world, pos, state);
            world.setBlockAndUpdate(pos, state.setValue(AGE, 0));
        } else {
            world.setBlockAndUpdate(pos, state.cycle(AGE));
        }

    }


    @Override
    public void fallOn(World world, BlockPos pos, Entity entity, float fallDistance) {
        super.fallOn(world, pos, entity, fallDistance);
        BlockPos newPos = pos.immutable();
        dropFruilt(world, newPos, world.getBlockState(newPos));
        newPos = pos.east();
        dropFruilt(world, newPos, world.getBlockState(newPos));
        newPos = pos.north();
        dropFruilt(world, newPos, world.getBlockState(newPos));
        newPos = pos.west();
        dropFruilt(world, newPos, world.getBlockState(newPos));
        newPos = pos.south();
        dropFruilt(world, newPos, world.getBlockState(newPos));
    }

    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState stateIn, Direction facing, BlockState facingState, @Nonnull IWorld worldIn, @Nonnull BlockPos currentPos, BlockPos facingPos) {
        if (worldIn.getBlockState(currentPos.below()).getBlock() instanceof FruitBlock) {
            stateIn = stateIn.setValue(AGE, 2);
        }
        // else if (stateIn.getValue(AGE) == 2 && worldIn.isEmptyBlock(currentPos.below())) {
        //     stateIn= stateIn.setValue(AGE, 0);
        // }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}

