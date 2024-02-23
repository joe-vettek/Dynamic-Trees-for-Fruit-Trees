package xueluoanping.dtfruittrees.mixin;


import com.ferreusveritas.dynamictrees.block.FruitBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import snownee.fruits.Hooks;
import snownee.fruits.hybridization.Hybridization;
import xueluoanping.dtfruittrees.DTFruitTrees;
import xueluoanping.dtfruittrees.systems.leaves.DynamicFruitLeavesBlock;

@Mixin({snownee.fruits.Hooks.class})
public class MixinHook {

    @Inject(at = @At("HEAD"), method = "canPollinate", cancellable = true, remap = false)
    private static void zz$canPollinate(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (Hooks.hybridization && state.getBlock() instanceof FruitBlock) {
            // DTFruitTrees.logger(state);
            // if (((FruitBlock) state.getBlock()).getAge(state) == 0)
            {
                cir.setReturnValue(true);
            }
        }
    }
}
