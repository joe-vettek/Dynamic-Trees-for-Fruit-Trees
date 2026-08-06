package xueluoanping.dtfruitfulfun.mixin;


import com.dtteam.dynamictrees.api.registry.RegistryEntry;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.fruits.FruitfulFun;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;
import xueluoanping.dtfruitfulfun.systems.fruit.FruitTypes;
import xueluoanping.dtfruitfulfun.systems.fruit.NamedFruitTypes;
import xueluoanping.dtfruitfulfun.util.RegisterFinderUtil;

import java.util.Objects;
import java.util.function.Supplier;

/*
 *
 * I know that writing this way is not a perfect solution,
 * but I can only do this because Dynamic Trees cannot read the correct item in the runData environment.
 * */
@Mixin({Fruit.class})
public abstract class MixinFruit extends RegistryEntry<Fruit> {

    @Shadow(remap = false)
    private Supplier<FruitBlock> block;
    private ItemStack itemStack;

    public MixinFruit(Identifier registryName) {
        super(registryName);
    }

    @Inject(at = @At("HEAD"), method = "createBlockDrops", remap = false)
    private void zz$createBlockDrops(CallbackInfoReturnable<LootTable.Builder> cir) {
        if (Objects.equals(System.getProperty("forgegradle.runs.runData"), "true")) {

            if (this.itemStack == null && this.block != null) {
                if ((RegisterFinderUtil.getBlockKey(block.get()).getPath() + "").startsWith(DTFruitfulFun.MOD_ID)) {
                    DTFruitfulFun.logger("Now is runData, so need to mixin the " + block.get() + " drop.");
                    this.itemStack = RegisterFinderUtil
                            .getItem(Identifier.fromNamespaceAndPath(FruitfulFun.ID, RegisterFinderUtil.getBlockKey(block.get()).getPath()))
                            .getDefaultInstance();
                    this.itemStack = this.itemStack.isEmpty() ? null : this.itemStack;
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "lambda$createBlock$0", remap = false, cancellable = true)
    private void zz$lambda$createBlock$0(Identifier id, BlockBehaviour.Properties properties, CallbackInfoReturnable<FruitBlock> cir) {
        Fruit fruit = Fruit.class.cast(this);
        if (fruit instanceof FruitTypes fruitTypes) {
            cir.setReturnValue(FruitTypes.createBlock(id, properties, fruitTypes));
        } else if (fruit instanceof NamedFruitTypes fruitTypes) {
            cir.setReturnValue(NamedFruitTypes.createBlock(id, properties, fruitTypes));
        }
    }
}
