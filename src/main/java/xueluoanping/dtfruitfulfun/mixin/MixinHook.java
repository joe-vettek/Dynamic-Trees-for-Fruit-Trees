package xueluoanping.dtfruitfulfun.mixin;


import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.block.FruitBlock;
import com.ferreusveritas.dynamictrees.block.leaves.DynamicLeavesBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.fruits.Hooks;
import snownee.fruits.block.FruitLeavesBlock;
import snownee.fruits.cherry.block.CherryLeavesBlock;
import xueluoanping.dtfruitfulfun.ModConstants;

import java.util.function.Predicate;

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

    @Inject(at = @At("HEAD"), method = "wrapPollinationPredicate", cancellable = true, remap = false)
    private static void zz$canPollinate(Predicate<BlockState> original, CallbackInfoReturnable<Predicate<BlockState>> cir) {
        {
            // DTFruitTrees.logger(state);
            // if (((FruitBlock) state.getBlock()).getAge(state) == 0)
            {
                cir.setReturnValue(state -> {
                    if (state.getBlock() instanceof FruitBlock)
                        return true;
                    if (state.getBlock() instanceof DynamicLeavesBlock && state.getBlock()== ModConstants.CHERRY_LEAVES_V.get())
                        return true;
                    else if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
                        return false;
                    } else {
                        Block patt3432$temp = state.getBlock();
                        if (patt3432$temp instanceof FruitLeavesBlock) {
                            FruitLeavesBlock block = (FruitLeavesBlock)patt3432$temp;
                            if (block instanceof CherryLeavesBlock) {
                                return block.notPlacedByPlayer(state);
                            } else if (!block.canGrow(state)) {
                                return false;
                            } else {
                                return state.getValue(FruitLeavesBlock.AGE) == 2;
                            }
                        } else {
                            return (!(state.getBlock() instanceof LeavesBlock)
                                    || !state.hasProperty(LeavesBlock.PERSISTENT)
                                    || !((Boolean) state.getValue(LeavesBlock.PERSISTENT)))
                                    && original.test(state);
                        }
                    }
                });
            }
        }
    }
}
