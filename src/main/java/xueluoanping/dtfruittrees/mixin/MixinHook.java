package xueluoanping.dtfruittrees.mixin;

import com.ferreusveritas.dynamictrees.blocks.FruitBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.fruits.Hook;
import snownee.fruits.block.FruitLeavesBlock;
import snownee.fruits.hybridization.Hybridization;
import xueluoanping.dtfruittrees.DTFruitTrees;
import xueluoanping.dtfruittrees.systems.leaves.DynamicFruitLeavesBlock;

@Mixin({Hook.class})
public class MixinHook {

    @Inject(at = @At("HEAD"), method = "canPollinate", cancellable = true,remap = false)
    private static void zz$canPollinate(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (Hybridization.INSTANCE != null && state.getBlock() instanceof FruitBlock)
        {
            // DTFruitTrees.logger(state);
            // if (((FruitBlock) state.getBlock()).getAge(state) == 0)
            {
                cir.setReturnValue(true);
            }
        }
    }
}
