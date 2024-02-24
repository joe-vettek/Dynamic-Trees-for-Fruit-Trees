package xueluoanping.dtfruittrees.mixin;


import com.ferreusveritas.dynamictrees.block.FruitBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.fruits.Hooks;

@Mixin({Bee.class})
public abstract class MixinBeeEntity extends Animal {


    protected MixinBeeEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    @Inject(at = {@At("HEAD")}, method = {"isFlowerValid"}, cancellable = true)
    public void fruits_isFlowers(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // if (Hooks.hybridization )
        {
            // DTFruitTrees.logger(pos);
            BlockState state = level().getBlockState(pos);

            if (level().isLoaded(pos) && state.getBlock() instanceof FruitBlock) {
                // if (((FruitBlock) state.getBlock()).getAge(state) == 0)
                // DTFruitTrees.logger(state);
                cir.setReturnValue(true);
            }

        }
    }
}
