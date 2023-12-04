package xueluoanping.dtfruittrees.systems.fruit;

import com.ferreusveritas.dynamictrees.blocks.FruitBlock;
import com.ferreusveritas.dynamictrees.compat.seasons.SeasonHelper;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.util.AgeProperties;
import com.ferreusveritas.dynamictrees.util.WorldContext;
import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class PassableFruitBlock extends FruitBlock {
    public PassableFruitBlock(Properties properties, Fruit fruit) {
        super(properties, fruit);
    }


    @Deprecated
    @Override
    public VoxelShape getCollisionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext context) {
        if (context.getEntity() instanceof IFlyingAnimal
                && pState.getBlock() instanceof FruitBlock)
        // && ((FruitBlock) (pState.getBlock())).getAge(pState) == 0)
        {
            return VoxelShapes.empty();
        }
        return super.getCollisionShape(pState, pLevel, pPos, context);
    }

    @Override
    public boolean isPathfindable(BlockState pState, IBlockReader pLevel, BlockPos pPos, PathType pType) {
        if (pType == PathType.AIR) {
            // if (((FruitBlock) pState.getBlock()).getAge(pState) == 0)
            return pState.isCollisionShapeFullBlock(pLevel, pPos);
        } else
            return super.isPathfindable(pState, pLevel, pPos, pType);
    }

    public void doTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!this.isSupported(world, pos, state)) {
            drop(world, pos, state);
            return;
        }

        final int age = getAge(state);
        final Float season = SeasonHelper.getSeasonValue(WorldContext.create(world), pos);
        if (age == 0 && season == null) {
            final boolean doGrow = random.nextFloat() < 0.01;
            final boolean eventGrow = ForgeHooks.onCropsGrowPre(world, pos, state, doGrow);
            if ( doGrow||eventGrow) {
                world.setBlock(pos, state.setValue( AgeProperties.getOrCreate(getMaxAge()), age + 1), 2);
                ForgeHooks.onCropsGrowPost(world, pos, state);
            }
        } else
            super.doTick(state, world, pos, random);

    }


}
