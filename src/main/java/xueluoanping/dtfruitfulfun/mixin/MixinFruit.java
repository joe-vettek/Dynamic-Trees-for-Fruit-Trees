package xueluoanping.dtfruitfulfun.mixin;


import com.ferreusveritas.dynamictrees.api.registry.RegistryEntry;
import com.ferreusveritas.dynamictrees.block.FruitBlock;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.fruits.FruitfulFun;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;
import xueluoanping.dtfruitfulfun.util.RegisterFinderUtil;

import java.util.Objects;
import java.util.function.Supplier;

/*
 *
 * I know that writing this way is not a perfect solution,
 * but I can only do this because Dynamic Trees cannot read the correct item in the runData environment.
 * */
@Mixin({Fruit.class})
public class MixinFruit extends RegistryEntry<Fruit> {

    @Shadow(remap = false)
    private Supplier<FruitBlock> block;
    private ItemStack itemStack;

    public MixinFruit(ResourceLocation registryName) {
        super(registryName);
    }

    @Inject(at = @At("HEAD"), method = "createBlockDrops", remap = false)
    private void zz$createBlockDrops(CallbackInfoReturnable<LootTable.Builder> cir) {
        if (Objects.equals(System.getProperty("forgegradle.runs.runData"), "true")) {

            if (this.itemStack == null && this.block != null) {
                if ((BuiltInRegistries.BLOCK.getKey(block.get()) + "").startsWith(DTFruitfulFun.MOD_ID)) {
                    DTFruitfulFun.logger("Now is runData, so need to mixin the " + block.get() + " drop.");
                    this.itemStack = RegisterFinderUtil
                            .getItem(new ResourceLocation(FruitfulFun.ID, (BuiltInRegistries.BLOCK.getKey(block.get()).getPath())))
                            .getDefaultInstance();
                    this.itemStack = this.itemStack.isEmpty() ? null : this.itemStack;
                }
            }
        }
    }

}