package xueluoanping.dtfruittrees.systems.fruit;


import com.ferreusveritas.dynamictrees.block.FruitBlock;
import com.ferreusveritas.dynamictrees.compat.season.SeasonHelper;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.util.AgeProperties;

import com.ferreusveritas.dynamictrees.util.LevelContext;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class PassableFruitBlock extends FruitBlock {
    public PassableFruitBlock(Properties properties, Fruit fruit) {
        super(properties, fruit);
    }


    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext context) {

        if (context instanceof EntityCollisionContext entityCollisionContext) {
            if (((EntityCollisionContext) context).getEntity() instanceof FlyingAnimal
                    && pState.getBlock() instanceof FruitBlock)
            // && ((FruitBlock) (pState.getBlock())).getAge(pState) == 0)
            {
                return  Shapes.empty();
            }
        }

        return super.getCollisionShape(pState, pLevel, pPos, context);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        if (pType == PathComputationType.AIR) {
            // if (((FruitBlock) pState.getBlock()).getAge(pState) == 0)
            return pState.isCollisionShapeFullBlock(pLevel, pPos);
        } else
            return super.isPathfindable(pState, pLevel, pPos, pType);
    }


    @Override
    public void doTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!this.isSupported(level, pos, state)) {
            drop(level, pos, state);
            return;
        }

        final int age = getAge(state);
        final Float season = SeasonHelper.getSeasonValue(LevelContext.create(level), pos);
        if (age == 0 && season == null) {
            final boolean doGrow = random.nextFloat() < 0.01;
            final boolean eventGrow = ForgeHooks.onCropsGrowPre(level, pos, state, doGrow);
            if (doGrow || eventGrow) {
                level.setBlock(pos, state.setValue(AgeProperties.getOrCreate(getMaxAge()), age + 1), 2);
                ForgeHooks.onCropsGrowPost(level, pos, state);
            }
        } else
            super.doTick(state, level, pos, random);

    }


}
