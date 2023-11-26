package xueluoanping.dtfruittrees.systems.featuregen;

import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.systems.genfeatures.GenFeature;
import net.minecraft.util.ResourceLocation;
import xueluoanping.dtfruittrees.DTFruitTrees;
import xueluoanping.dtfruittrees.systems.featuregen.FeatureGenFallenLeaves;
import xueluoanping.dtfruittrees.systems.featuregen.FeatureGenFruitLeaves;

public class CherryFeatures {
    public static final GenFeature FALLEN_LEAVES = new FeatureGenFallenLeaves(regName("fallen_leaves"));
    public static final GenFeature FRUIT_LEAVES = new FeatureGenFruitLeaves(regName("fruit_leaves"));
    private static ResourceLocation regName(String name) {
        return new ResourceLocation(DTFruitTrees.MOD_ID, name);
    }

    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(FALLEN_LEAVES, FRUIT_LEAVES);
    }
}
