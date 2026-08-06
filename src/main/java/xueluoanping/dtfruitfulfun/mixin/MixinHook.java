package xueluoanping.dtfruitfulfun.mixin;


import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.fruits.block.FruitLeavesBlock;
import snownee.fruits.cherry.block.CherryLeavesBlock;
import xueluoanping.dtfruitfulfun.ModConstants;

@Mixin(value = {Bee.class}, priority = 1200)
public class MixinHook {

    @Inject(at = @At("HEAD"), method = "attractsBees", cancellable = true, remap = false)
    private static void zz$canPollinate(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        {
            if (state.getBlock() instanceof FruitBlock)
                cir.setReturnValue(true);
            if (state.getBlock() instanceof DynamicLeavesBlock && state.getBlock() == ModConstants.CHERRY_LEAVES_V.get())
                cir.setReturnValue(true);
            else {
                Block stateBlock = state.getBlock();
                if (stateBlock instanceof FruitLeavesBlock block) {
                    if (block instanceof CherryLeavesBlock) {
                        cir.setReturnValue(block.notPlacedByPlayer(state));
                    } else if (!block.canGrow(state)) {
                        cir.setReturnValue(false);
                    } else {
                        cir.setReturnValue(state.getValue(FruitLeavesBlock.AGE) == 2);
                    }
                }
            }
        }
    }
}
