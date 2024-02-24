package xueluoanping.dtfruitfulfun.systems.leaves;


import com.ferreusveritas.dynamictrees.block.FruitBlock;
import com.ferreusveritas.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import snownee.fruits.block.FruitLeavesBlock;

import javax.annotation.Nonnull;

public class DynamicFruitLeavesBlock extends DynamicLeavesBlock implements BonemealableBlock {

    public static final IntegerProperty AGE = FruitLeavesBlock.AGE;

    public DynamicFruitLeavesBlock(LeavesProperties leavesProperties, Properties properties) {
        super(leavesProperties, properties);
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(AGE));
    }


    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
        super.randomTick(state, world, pos, rand);
        int nowAge = state.getValue(AGE);
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

    }


    public boolean canGrow(BlockState state) {
        return (Integer) state.getValue(AGE) > 0;
    }

    public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }


    public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state) {
        if (state.getValue(AGE) >= 2) {
            world.setBlockAndUpdate(pos, state.setValue(AGE, 0));
        } else {
            world.setBlockAndUpdate(pos, state.cycle(AGE));
        }

    }

    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity p_152434_) {
        super.stepOn(p_152431_, p_152432_, p_152433_, p_152434_);
    }



    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, float fallDistance) {
        super.fallOn(level, blockState, pos, entity, fallDistance);
        BlockPos newPos = pos.immutable();
        dropFruilt(level, newPos, level.getBlockState(newPos));
        newPos = pos.east();
        dropFruilt(level, newPos, level.getBlockState(newPos));
        newPos = pos.north();
        dropFruilt(level, newPos, level.getBlockState(newPos));
        newPos = pos.west();
        dropFruilt(level, newPos, level.getBlockState(newPos));
        newPos = pos.south();
        dropFruilt(level, newPos, level.getBlockState(newPos));
    }

    private void dropFruilt(Level level, BlockPos newPos, BlockState blockState) {
        // Todo
    }


    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState stateIn, Direction facing, BlockState facingState, @Nonnull LevelAccessor worldIn, @Nonnull BlockPos currentPos, BlockPos facingPos) {
        if (worldIn.getBlockState(currentPos.below()).getBlock() instanceof FruitBlock) {
            stateIn = stateIn.setValue(AGE, 2);
        }
        // else if (stateIn.getValue(AGE) == 2 && worldIn.isEmptyBlock(currentPos.below())) {
        //     stateIn= stateIn.setValue(AGE, 0);
        // }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }


}

