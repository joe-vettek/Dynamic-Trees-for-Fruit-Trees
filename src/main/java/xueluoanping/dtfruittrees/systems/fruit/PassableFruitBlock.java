package xueluoanping.dtfruittrees.systems.fruit;

import com.ferreusveritas.dynamictrees.blocks.FruitBlock;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

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
        }
        else return super.isPathfindable(pState, pLevel, pPos, pType);
    }
}
