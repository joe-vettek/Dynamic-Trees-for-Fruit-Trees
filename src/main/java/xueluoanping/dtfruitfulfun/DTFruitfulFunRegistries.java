package xueluoanping.dtfruitfulfun;

import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;


import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.event.RegistryEvent;
import com.dtteam.dynamictrees.event.TypeRegistryEvent;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import xueluoanping.dtfruitfulfun.systems.featuregen.CherryFeatures;
import xueluoanping.dtfruitfulfun.systems.fruit.FruitTypes;
import xueluoanping.dtfruitfulfun.systems.fruit.NamedFruitTypes;
import xueluoanping.dtfruitfulfun.systems.leaves.CherryLeavesProperties;
import xueluoanping.dtfruitfulfun.systems.leaves.FruitsLeavesProperties;
import xueluoanping.dtfruitfulfun.systems.leaves.NamedVanillaCherryLeaveProperties;
import xueluoanping.dtfruitfulfun.systems.worldgen.FruitTreesFeatureCanceller;

@EventBusSubscriber
public class DTFruitfulFunRegistries {

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(final TypeRegistryEvent<LeavesProperties> event) {
        DTFruitfulFun.LOGGER.debug("registerLeavesPropertiesTypes");
        event.registerType(DTFruitfulFun.rl("cherry"), CherryLeavesProperties.TYPE);
        event.registerType(DTFruitfulFun.rl("fruittrees"), FruitsLeavesProperties.TYPE);
        event.registerType(DTFruitfulFun.rl("named_cherry_leaves"), NamedVanillaCherryLeaveProperties.TYPE);


    }

    @SubscribeEvent
    public static void registerFruitTypes(final TypeRegistryEvent<Fruit> event) {
        DTFruitfulFun.LOGGER.debug("registerFruitTypes");
        event.registerType(DTFruitfulFun.rl("fly_passable"), FruitTypes.TYPE);
        event.registerType(DTFruitfulFun.rl("named_fruit"), NamedFruitTypes.TYPE);

    }


    public static final FeatureCanceller FRUIT_TREES_CANCELLER = new FruitTreesFeatureCanceller(DTFruitfulFun.rl("fruittrees"));

    @SubscribeEvent
    public static void onFeatureCancellerRegistry(final RegistryEvent<FeatureCanceller> event) {
        event.getRegistry().registerAll(FRUIT_TREES_CANCELLER);
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry(final RegistryEvent<GenFeature> event) {
        CherryFeatures.register(event.getRegistry());
    }


}
