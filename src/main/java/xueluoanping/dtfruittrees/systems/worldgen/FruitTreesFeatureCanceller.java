package xueluoanping.dtfruittrees.systems.worldgen;

import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class FruitTreesFeatureCanceller extends FeatureCanceller {
    public FruitTreesFeatureCanceller(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.FeatureCancellations featureCancellations) {
        // Note it not in ForgeRegistries.FEATURES
        final ResourceLocation featureName = WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature);
        if (featureName == null) {
            return false;
        }

        return featureCancellations.shouldCancelNamespace(featureName.getNamespace())
                && (WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature) + "").startsWith("fruittrees");
    }
}
