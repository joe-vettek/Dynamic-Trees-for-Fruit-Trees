package xueluoanping.dtfruittrees;

import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;


import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xueluoanping.dtfruittrees.systems.featuregen.CherryFeatures;
import xueluoanping.dtfruittrees.systems.fruit.FruitTypes;
import xueluoanping.dtfruittrees.systems.leaves.CherryLeavesProperties;
import xueluoanping.dtfruittrees.systems.leaves.FruitsLeavesProperties;
import xueluoanping.dtfruittrees.systems.worldgen.FruitTreesFeatureCanceller;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DTFruitTreesRegistries {

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(final TypeRegistryEvent<LeavesProperties> event) {
        DTFruitTrees.LOGGER.debug("registerLeavesPropertiesTypes");
        event.registerType(new ResourceLocation(DTFruitTrees.MOD_ID, "cherry"), CherryLeavesProperties.TYPE);
        event.registerType(new ResourceLocation(DTFruitTrees.MOD_ID, "fruittrees"), FruitsLeavesProperties.TYPE);
    }

    @SubscribeEvent
    public static void registerFruitTypes(final TypeRegistryEvent<Fruit> event) {
        DTFruitTrees.LOGGER.debug("registerFruitTypes");
        event.registerType(new ResourceLocation(DTFruitTrees.MOD_ID, "fly_passable"), FruitTypes.TYPE);

    }



    public static final FeatureCanceller FRUIT_TREES_CANCELLER = new FruitTreesFeatureCanceller(DTFruitTrees.rl("fruittrees"));
    @SubscribeEvent
    public static void onFeatureCancellerRegistry(final RegistryEvent<FeatureCanceller> event) {
        event.getRegistry().registerAll(FRUIT_TREES_CANCELLER);
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GenFeature> event) {
        CherryFeatures.register(event.getRegistry());
    }


}
