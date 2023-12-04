package xueluoanping.dtfruittrees.mixin;


import com.ferreusveritas.dynamictrees.blocks.FruitBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({AbstractBlock.class})
public class MixinAbstractBlock {

    // @Inject(at = @At("HEAD"), method = "getCollisionShape", cancellable = true)
    // private void zz$getCollisionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext context, CallbackInfoReturnable<VoxelShape> cir) {
    //
    //     if (context.getEntity() instanceof IFlyingAnimal
    //             && pState.getBlock() instanceof FruitBlock)
    //             // && ((FruitBlock) (pState.getBlock())).getAge(pState) == 0)
    //     {
    //         cir.setReturnValue(VoxelShapes.empty());
    //     }
    // }
}


