package xueluoanping.dtfruitfulfun.mixin;


import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xueluoanping.dtfruitfulfun.ModConstants;

@Mixin(targets = "net.minecraft.world.entity.animal.bee.Bee$ValidateFlowerGoal")
public abstract class MixinBeeEntity {

    @Shadow
    @Dynamic
    @Final
    Bee this$0;

    @Inject(at = {@At("HEAD")}, method = {"isFlower"}, cancellable = true)
    public void fruits_isFlowers(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // if (Hooks.hybridization )
        {
            // DTFruitTrees.logger(pos);
            BlockState state = this$0.level().getBlockState(pos);
            if (this$0.level().isLoaded(pos)) {
                if (state.getBlock() instanceof FruitBlock) {
                    // if (((FruitBlock) state.getBlock()).getAge(state) == 0)
                    // DTFruitTrees.logger(state);
                    cir.setReturnValue(true);
                }
                if (state.getBlock() instanceof DynamicLeavesBlock && state.getBlock() == ModConstants.CHERRY_LEAVES_V.get())
                    cir.setReturnValue(true);
            }


        }
    }
}
