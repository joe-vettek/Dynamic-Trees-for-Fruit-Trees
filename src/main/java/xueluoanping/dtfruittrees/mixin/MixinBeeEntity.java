package xueluoanping.dtfruittrees.mixin;

import com.ferreusveritas.dynamictrees.blocks.FruitBlock;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import net.minecraft.block.BlockState;
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
            BlockState state = this.level.getBlockState(pos);

            if (this.level.isLoaded(pos) && state.getBlock() instanceof FruitBlock) {
                // if (((FruitBlock) state.getBlock()).getAge(state) == 0)
                // DTFruitTrees.logger(state);
                    cir.setReturnValue(true);
            }

        }
    }
}
