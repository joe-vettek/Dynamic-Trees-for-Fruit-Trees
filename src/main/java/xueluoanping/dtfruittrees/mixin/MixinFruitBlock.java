package xueluoanping.dtfruittrees.mixin;

import com.ferreusveritas.dynamictrees.blocks.FruitBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import net.minecraft.block.BlockState;
import net.minecraft.loot.LootTable;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xueluoanping.dtfruittrees.DTFruitTrees;
import xueluoanping.dtfruittrees.util.RegisterFinderUtil;

import java.util.Objects;

@Mixin({FruitBlock.class})
public class MixinFruitBlock {
    // @Inject(at = @At("HEAD"), method = "isPathfindable", cancellable = true)
    // private void zz$isPathfindable(BlockState pState, IBlockReader pLevel, BlockPos pPos, PathType pType, CallbackInfoReturnable<Boolean> cir) {
    //     if (pType == PathType.AIR) {
    //         // if (((FruitBlock) pState.getBlock()).getAge(pState) == 0)
    //             cir.setReturnValue(pState.isCollisionShapeFullBlock(pLevel, pPos));
    //     }
    // }
}
