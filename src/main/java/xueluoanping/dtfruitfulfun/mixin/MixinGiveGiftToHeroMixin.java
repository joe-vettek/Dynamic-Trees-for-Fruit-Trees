package xueluoanping.dtfruitfulfun.mixin;

import java.util.List;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.ai.behavior.GiveGiftToHero;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import snownee.fruits.CoreModule;
import snownee.fruits.FFCommonConfig;

@Mixin(GiveGiftToHero.class)
public class MixinGiveGiftToHeroMixin {
    @Inject(at = @At("HEAD"), method = "getItemToThrow", cancellable = true)
    private void getItemToThrow(Villager villager, CallbackInfoReturnable<List<ItemStack>> ci) {
        // FFCommonConfig.appleSaplingFromHeroOfTheVillage &&
        if ( villager.isBaby()) {
            ci.setReturnValue(List.of(BuiltInRegistries.ITEM.get(new ResourceLocation(DynamicTrees.MOD_ID, "apple_oak_seed")).getDefaultInstance()));
        }
    }
}
