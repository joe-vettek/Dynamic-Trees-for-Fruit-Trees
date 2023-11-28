package xueluoanping.dtfruittrees.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.fruits.hybridization.Hybridization;
import xueluoanping.dtfruittrees.DTFruitTrees;
import xueluoanping.dtfruittrees.systems.leaves.DynamicFruitLeavesBlock;

@Mixin({BeeEntity.class})
public abstract class MixinBeeEntity extends AnimalEntity {

    protected MixinBeeEntity(EntityType<? extends AnimalEntity> p_i48568_1_, World p_i48568_2_) {
        super(p_i48568_1_, p_i48568_2_);
    }

    @Inject(at = {@At("HEAD")}, method = {"isFlowerValid"}, cancellable = true)
    public void fruits_isFlowers(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Hybridization.INSTANCE != null) {
            // DTFruitTrees.logger(pos);
            if (this.level.isLoaded(pos) && this.level.getBlockState(pos).getBlock() instanceof DynamicFruitLeavesBlock) {
                cir.setReturnValue(true);
            }

        }
    }
}
