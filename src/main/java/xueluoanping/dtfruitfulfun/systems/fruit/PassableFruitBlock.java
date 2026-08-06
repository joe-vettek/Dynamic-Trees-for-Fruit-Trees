package xueluoanping.dtfruitfulfun.systems.fruit;


import com.dtteam.dynamictrees.api.worldgen.LevelContext;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import com.dtteam.dynamictrees.systems.season.SeasonHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class PassableFruitBlock extends NamedFruitBlock {
    public PassableFruitBlock(Identifier id, Properties properties, Fruit fruit) {
        super(id, properties, fruit);
    }


    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext context) {

        if (context instanceof EntityCollisionContext entityCollisionContext) {
            if (((EntityCollisionContext) context).getEntity() instanceof FlyingAnimal
                    && pState.getBlock() instanceof FruitBlock)
            // && ((FruitBlock) (pState.getBlock())).getAge(pState) == 0)
            {
                return Shapes.empty();
            }
        }

        return super.getCollisionShape(pState, pLevel, pPos, context);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        if (pathComputationType == PathComputationType.AIR) {
            return state.isSolidRender();
        }
        return super.isPathfindable(state, pathComputationType);
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
            final boolean eventGrow = CommonHooks.canCropGrow(level, pos, state, doGrow);
            if (doGrow || eventGrow) {
                level.setBlock(pos, state.setValue(
                        this.fruit.getAgeProperty(), Math.min(state.getValue(this.fruit.getAgeProperty()), age + 1)), Block.UPDATE_CLIENTS);
                CommonHooks.fireCropGrowPost(level, pos, state);
            }
        } else
            super.doTick(state, level, pos, random);

    }


}
