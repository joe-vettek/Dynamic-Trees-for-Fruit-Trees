package xueluoanping.dtfruitfulfun.mixin;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.npc.villager.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.ai.behavior.GiveGiftToHero;
import xueluoanping.dtfruitfulfun.ModConstants;


@Mixin(GiveGiftToHero.class)
public class MixinGiveGiftToHeroMixin {

    @Inject(at = @At("HEAD"), method = "throwGift", cancellable = true)
    private void ff$throwGift(ServerLevel level, Villager villager, LivingEntity target, CallbackInfo ci) {
        // FFCommonConfig.appleSaplingFromHeroOfTheVillage &&
        if (villager.isBaby()) {
            BehaviorUtils.throwItem(villager, ModConstants.APPLE_OAK_SEED.get().getDefaultInstance(), target.position());
            ci.cancel();
        }
    }
}
