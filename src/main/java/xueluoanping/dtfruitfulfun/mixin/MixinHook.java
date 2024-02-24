package xueluoanping.dtfruitfulfun.mixin;


import org.spongepowered.asm.mixin.Mixin;

@Mixin({snownee.fruits.Hooks.class})
public class MixinHook {

    // @Inject(at = @At("HEAD"), method = "canPollinate", cancellable = true, remap = false)
    // private static void zz$canPollinate(BlockState state, CallbackInfoReturnable<Boolean> cir) {
    //     if (Hooks.hybridization && state.getBlock() instanceof FruitBlock) {
    //         // DTFruitTrees.logger(state);
    //         // if (((FruitBlock) state.getBlock()).getAge(state) == 0)
    //         {
    //             cir.setReturnValue(true);
    //         }
    //     }
    // }
}
