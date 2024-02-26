package xueluoanping.dtfruitfulfun.mixin;



import com.google.common.collect.ImmutableList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.ai.behavior.GiveGiftToHero;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import xueluoanping.dtfruitfulfun.ModConstants;

import java.util.List;


@Mixin(GiveGiftToHero.class)
public class MixinGiveGiftToHeroMixin {
    @Inject(at = @At("HEAD"), method = "getItemToThrow", cancellable = true)
    private void getItemToThrow(Villager villager, CallbackInfoReturnable<List<ItemStack>> ci) {
        // FFCommonConfig.appleSaplingFromHeroOfTheVillage &&
        if ( villager.isBaby()) {
            ci.setReturnValue(ImmutableList.of(ModConstants.APPLE_OAK_SEED.get().getDefaultInstance()));
        }
    }
}
