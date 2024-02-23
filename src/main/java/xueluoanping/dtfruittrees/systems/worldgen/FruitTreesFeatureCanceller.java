package xueluoanping.dtfruittrees.systems.worldgen;

import com.ferreusveritas.dynamictrees.api.registry.Registries;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.ForgeRegistries;
import xueluoanping.dtfruittrees.DTFruitTrees;

import java.util.Set;

public class FruitTreesFeatureCanceller extends FeatureCanceller {
    public FruitTreesFeatureCanceller(ResourceLocation registryName) {
        super(registryName);
    }


    @Override
    public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, Set<String> namespaces) {
        // Note it not in ForgeRegistries.FEATURES
        final ResourceLocation featureName =  ForgeRegistries.FEATURES.getKey(configuredFeature.feature());
        if (featureName == null) {
            return false;
        }
        DTFruitTrees.logger(22,featureName);
        return  namespaces.contains(featureName.toString());
                // && (WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature) + "").startsWith("fruittrees");
    }
}
