package xueluoanping.dtfruitfulfun.systems.worldgen;


import com.dtteam.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;

public class FruitTreesFeatureCanceller extends FeatureCanceller {
    public FruitTreesFeatureCanceller(Identifier registryName) {
        super(registryName);
    }

    @Override
    public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        // Note it not in ForgeRegistries.FEATURES
        final Identifier featureName =  BuiltInRegistries.FEATURE.getKey(configuredFeature.feature());
        if (featureName == null) {
            return false;
        }
        DTFruitfulFun.logger(22,featureName);
        return   false;
        // && (WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature) + "").startsWith("fruittrees");
    }



}
