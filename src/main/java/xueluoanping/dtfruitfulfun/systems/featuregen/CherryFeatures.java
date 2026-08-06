package xueluoanping.dtfruitfulfun.systems.featuregen;


import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import net.minecraft.resources.Identifier;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;


public class CherryFeatures {
    public static final GenFeature FALLEN_LEAVES = new FeatureGenFallenLeaves(regName("fallen_leaves"));

    private static Identifier regName(String name) {
        return DTFruitfulFun.rl(name);
    }

    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(FALLEN_LEAVES);
    }
}
